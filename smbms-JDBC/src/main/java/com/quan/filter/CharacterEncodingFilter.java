package com.quan.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName: CharacterEncodingFilter
 * @Description: 处理字符的过滤器
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/10/28 16:37
 */
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
