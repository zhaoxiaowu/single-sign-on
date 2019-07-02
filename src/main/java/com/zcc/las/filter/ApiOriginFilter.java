package com.zcc.las.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域请求过滤器
 *
 * @author zhuchao
 */
@Order(0)
@WebFilter(filterName = ApiOriginFilter.BEAN_NAME, urlPatterns = "/*")
@Component
public class ApiOriginFilter implements Filter {
  public static final String BEAN_NAME = "sasWebOriginFilter";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    res.addHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
    res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
    res.addHeader("Access-Control-Allow-Headers", "Content-Type");
    res.addHeader("Access-Control-Allow-Headers", "X-Requested-With");
    res.addHeader("Access-Control-Allow-Headers", "trace_id");
    res.addHeader("Access-Control-Expose-Headers", "trace_id");
    res.addHeader("Access-Control-Allow-Credentials", "true");

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}