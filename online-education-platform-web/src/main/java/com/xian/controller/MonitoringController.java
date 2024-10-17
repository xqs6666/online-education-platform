package com.xian.controller;

import com.xian.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/online/api")
public class MonitoringController {

    @Autowired
    private MetricsEndpoint metricsEndpoint;

    @GetMapping("/monitoring")
    public Result<Map<String, String>> getMetricsWithUnits() {
        Map<String, String> importantMetrics = new HashMap<>();
        // 6个重要指标名称及单位
        String[] importantMetricNames = {
                "system.cpu.usage",      // 系统CPU使用率 (%)
                "jvm.memory.used",       // JVM已使用内存 (MB)
                "jvm.memory.max",        // JVM最大内存 (MB)
                "jvm.threads.live",      // 活跃线程数 (个)
                "process.uptime",        // 应用运行时间 (秒)
                "jvm.threads.daemon"     // 守护线程数 (个)
        };

        // 遍历6个重要指标，获取其值
        for (String name : importantMetricNames) {
            MetricsEndpoint.MetricResponse metric = metricsEndpoint.metric(name, null);
            if (metric != null && metric.getMeasurements() != null) {
                metric.getMeasurements().forEach(measurement -> {
                    if (measurement.getValue() != null) { // 确保值不为null
                        double value = measurement.getValue();
                        String unit = "";
                        switch (name) {
                            case "system.cpu.usage":
                                value *= 100; // 将 CPU 使用率转换为百分比
                                unit = "%";
                                break;
                            case "jvm.memory.used":
                            case "jvm.memory.max":
                                value /= (1024 * 1024); // 将内存从字节转换为 MB
                                unit = "MB";
                                break;
                            case "jvm.threads.live":
                            case "jvm.threads.daemon":
                                unit = "个";
                                break;
                            case "process.uptime":
                                unit = "秒";
                                break;
                        }
                        importantMetrics.put(getChineseDescription(name), value + unit);
                    }
                });
            }
        }

        return Result.success(importantMetrics);
    }

    // 获取中文描述的方法
    private String getChineseDescription(String name) {
        switch (name) {
            case "system.cpu.usage":
                return "系统CPU使用率";
            case "jvm.memory.used":
                return "JVM已使用内存";
            case "jvm.memory.max":
                return "JVM最大内存";
            case "jvm.threads.live":
                return "活跃线程数";
            case "process.uptime":
                return "应用运行时间";
            case "jvm.threads.daemon":
                return "守护线程数";
            default:
                return name;
        }
    }


}
