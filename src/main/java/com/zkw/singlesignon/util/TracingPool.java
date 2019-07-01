/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	single-sign-on
 * 文件名：	TracingPool.java
 * 模块说明：
 * 修改历史：
 * 2019/7/1 - wuhongyun - 创建。
 */
package com.zkw.singlesignon.util;

/**
 * @author wuhongyun
 */
public class TracingPool {
  private static final ThreadLocal<String> TRACING_THREAD_LOCAL = new ThreadLocal();

  public TracingPool() {
  }

  public static void setTracingId(String tracingId) {
    TRACING_THREAD_LOCAL.set(tracingId);
  }

  public static String getTracingId() {
    return (String)TRACING_THREAD_LOCAL.get();
  }
}
