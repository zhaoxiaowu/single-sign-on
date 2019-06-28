/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	single-sign-on
 * 文件名：	springExceptionHandler.java
 * 模块说明：
 * 修改历史：
 * 2019/5/29 - wuhongyun - 创建。
 */
package com.zkw.singlesignon.exception;

import com.zkw.singlesignon.http.ErrorResult;
import com.zkw.singlesignon.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.lang.String.format;

/**
 * 全局异常处理
 *
 * @author wuhongyun
 */
@ControllerAdvice
public class SpringExceptionHandler {

  protected Logger log = LoggerFactory.getLogger(this.getClass());
  @ExceptionHandler(value = {Exception.class})
  public void dispatcherException(Exception e, HttpServletResponse response, HttpServletRequest request) throws IOException {

    log.error(format("请求%s失败",request.getRequestURI()),e);


    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    ErrorResult result = new ErrorResult();
    result.setMessage(decodeErrorMessage(e));
    result.setTraceId(MDC.get("trace_id"));
    //response.getWriter().print(JsonUtil.objectToJson(result));
    response.getWriter().print(JsonUtil.objectToJson(result));
  }

  private String decodeErrorMessage(Exception e) {
    return e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
  }

}
