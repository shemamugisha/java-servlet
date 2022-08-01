package com.shema.servlet.helpers;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.io.IOException;

@WebFilter(asyncSupported = true, urlPatterns = { "/*" })
public class Cors implements javax.servlet.Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, java.io.IOException {
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "Content-Type");
        ((HttpServletResponse) response).addHeader("Access-Control-Max-Age", "86400");
        ((HttpServletResponse) response).addHeader("Access-Control-Allow-Credentials", "true");

        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}
