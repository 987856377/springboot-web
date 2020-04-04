package com.spring.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description basic 过滤器, 过滤 /basic 的请求
 * @Project springboot-web
 * @Package com.spring.web.filter
 * @Author xuzhenkui
 * @Date 2020/4/4 8:56
 */
//@WebFilter(filterName = "basicFilter", urlPatterns = {"*.do","*.jsp"})
@WebFilter(filterName = "basicFilter", urlPatterns = {"/basic"})
public class BasicRequestFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(BasicRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        logger.info("BasicRequestFilter: " + httpServletRequest.getRequestURL());
//        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
