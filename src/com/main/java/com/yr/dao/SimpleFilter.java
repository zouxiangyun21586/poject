package com.yr.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleFilter implements Filter {
    static Map<String, String> map = new HashMap<String, String>();

    public void destroy() {
        System.out.println("----销毁----");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String url = httpRequest.getServletPath();
        String contextPath = httpRequest.getContextPath();
        if (url.equals("")) {
            url += "/";
        }
        // 设置不需要过滤的页面
        if (url.endsWith("login.jsp") || url.endsWith("/loginservlet") || url.endsWith(".css") || url.endsWith(".js")
                || url.endsWith(".ttf") || url.endsWith(".woff") || url.endsWith(".icon") || url.endsWith("websocket")
                || url.endsWith("/authservlet") || url.endsWith("demo-1-bg.jpg")) {
            filterChain.doFilter(request, response);
        } else {
            String username = (String) httpRequest.getSession().getAttribute("username");
            if (username == null) {

                httpResponse.sendRedirect(contextPath + "/login.jsp");
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    private boolean isContains(String requestURI, String[] logonList) {
        // TODO Auto-generated method stub
        return false;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("----初始化----");
        new SimpleFilter().getProperties();
    }

    public Map<String, String> getProperties() {
        Properties props = new Properties();
        try {
            InputStream in = getClass().getResourceAsStream("/jdbc.properties");
            props.load(in);
            Enumeration en = props.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String property = props.getProperty(key);
                map.put(key, property);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return map;
    }
}
