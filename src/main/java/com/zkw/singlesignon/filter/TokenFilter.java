package com.zkw.singlesignon.filter;

import com.zkw.singlesignon.exception.AuthenticationException;
import com.zkw.singlesignon.sdk.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token验证
 *
 * @author wuhongyun
 */
@Component
public class TokenFilter implements Filter {

    @Autowired
    private TokenService tokenService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.getRequestURI() != null && ignorePath(request.getRequestURI())) {
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        // 验证token
        if(!request.getMethod().equals("OPTIONS")) {
            try {
                tokenService.verify(request);
                // 刷新token
                tokenService.refresh(request,response);
            } catch (AuthenticationException e) {
                logger.error("Token Filetr error",e);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
    //忽略的URl配置
    public boolean ignorePath(String path) {
        return path.contains("/about") || path.contains("/login") || path.contains("swagger") || path.contains("/info") || path.contains("/health")
                || path.endsWith("/v2/api-docs");
    }
}
