package com.xian.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Properties;

@Service
public class SftpService {

    @Value("${sftp.host}")
    private String sftpHost;

    @Value("${sftp.port}")
    private int sftpPort;

    @Value("${sftp.user}")
    private String sftpUser;

    @Value("${sftp.password}")
    private String sftpPassword;

    @Value("${sftp.directory}")
    private String sftpDirectory;

    // 创建SFTP连接
    private ChannelSftp setupSftpConnection() throws Exception {
        JSch jsch = new JSch();
        Session session = jsch.getSession(sftpUser, sftpHost, sftpPort);
        session.setPassword(sftpPassword);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        session.connect();

        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();

        return channelSftp;
    }

    // 关闭SFTP连接
    private void closeSftpConnection(ChannelSftp channelSftp, Session session) {
        if (channelSftp != null) {
            channelSftp.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }

    // 上传文件
    public String uploadFile(MultipartFile file, String uuid, String extension) throws Exception {
        ChannelSftp channelSftp = null;
        Session session = null;

        try {
            channelSftp = setupSftpConnection();
            session = channelSftp.getSession();

            String remoteFilePath = sftpDirectory + uuid + extension;
            InputStream inputStream = file.getInputStream();
            channelSftp.put(inputStream, remoteFilePath);
            inputStream.close();

            return remoteFilePath;
        } finally {
            closeSftpConnection(channelSftp, session);
        }
    }

    // 删除文件
    public void deleteFile(String filePath) throws Exception {
        ChannelSftp channelSftp = null;
        Session session = null;
        String remoteFilePath = sftpDirectory + filePath;

        try {
            channelSftp = setupSftpConnection();
            session = channelSftp.getSession();
            channelSftp.rm(remoteFilePath);
        } finally {
            closeSftpConnection(channelSftp, session);
        }
    }
}
