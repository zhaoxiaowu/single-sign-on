/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	single-sign-on
 * 文件名：	springExceptionHandler.java
 * 模块说明：
 * 修改历史：
 * 2019/5/29 - wuhongyun - 创建。
 */
package com.zcc.las.exception;

import com.zcc.las.http.ErrorResult;
import com.zcc.las.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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

    ErrorResult result = new ErrorResult();

    if (e instanceof LogoutException){
      result.setCode("1001");
    } else {
      result.setCode("500");
    }

    //response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");

    result.setSuccess(false);
    result.setMessage(decodeErrorMessage(e));
    result.setTraceId(MDC.get("trace_id"));
    //response.getWriter().print(JsonUtil.objectToJson(result));

    //使用outputStream输出，printWriter存在问题
    String json = JsonUtil.objectToJson(result);
    response.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
    response.getOutputStream().flush();
  }

  private String decodeErrorMessage(Exception e) {
    return e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
  }

}
