package com.spring.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Description 全局过滤器, 记录所有客户端的 ip, 端口, 请求的 url
 * @Project springboot-web
 * @Package com.spring.web.filter
 * @Author xuzhenkui
 * @Date 2020/4/4 9:04
 */
@WebFilter(filterName = "globalOncePerRequestFilter", urlPatterns = {"/*"})
public class GlobalRequestFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(GlobalRequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logger.info("GlobalOncePerRequestFilter: " + "[Remote IpAddr: " + getIpAddr(httpServletRequest) + "] [Remote Port: " + httpServletRequest.getRemotePort() + "] [Remote User: " + httpServletRequest.getRemoteUser() + "] [Request Url: " + httpServletRequest.getRequestURL() + "]");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
