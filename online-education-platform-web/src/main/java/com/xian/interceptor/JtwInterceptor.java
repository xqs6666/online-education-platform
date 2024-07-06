package com.xian.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class JtwInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在请求处理之前调用
        // 这里可以编写一些逻辑，如身份验证、授权等
        // 如果返回false，则请求将被拒绝，不会继续执行后续的拦截器和目标方法
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在请求处理之后，但在视图渲染之前调用
        // 这里可以编写一些逻辑，如日志记录、性能监控等
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在整个请求处理完成后调用，即视图渲染之后
        // 这里可以编写一些资源清理的逻辑
    }
}