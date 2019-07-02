/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	single-sign-on
 * 文件名：	ErrorResult.java
 * 模块说明：
 * 修改历史：
 * 2019/5/29 - wuhongyun - 创建。
 */
package com.zcc.las.http;

import com.zcc.las.sdk.ActionResult;

/**
 * 错误返回
 *
 * @author wuhongyun
 */
public class ErrorResult extends ActionResult {
  private String traceId;

  public String getTraceId() {
    return traceId;
  }

  public void setTraceId(String traceId) {
    this.traceId = traceId;
  }
}
