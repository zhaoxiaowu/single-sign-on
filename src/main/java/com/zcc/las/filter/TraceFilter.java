package com.zcc.las.filter;

import com.zcc.las.util.FilterUtils;
import com.zcc.las.util.TracingPool;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
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
import java.util.UUID;

/**
 * trace过滤器
 *
 * @author zhuchao
 */
@Order(1)
@WebFilter(filterName = TraceFilter.BEAN_NAME, urlPatterns = "/*")
@Component
public class TraceFilter implements Filter {
  public static final String BEAN_NAME = "sasWebTraceFilter";

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    String traceId = request.getHeader(FilterUtils.TRACE_ID);
    if (StringUtils.isBlank(traceId)) {
      traceId = UUID.randomUUID().toString();
    }
    MDC.put(FilterUtils.TRACE_ID, traceId);
    response.setHeader(FilterUtils.TRACE_ID, traceId);
    TracingPool.setTracingId(traceId);

    filterChain.doFilter(request, servletResponse);
  }

  @Override
  public void destroy() {

  }

}
