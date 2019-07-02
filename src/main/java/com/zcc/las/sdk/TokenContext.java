/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2016，所有权利保留。
 * <p>
 * 项目名：	login-sdk
 * 文件名：	TokenContext.java
 * 模块说明：
 * 修改历史：
 * 2016-10-12 - chenrizhang - 创建。
 */
package com.zcc.las.sdk;

/**
 * 存放Token数据，供线程内共享使用
 *
 * @author chenrizhang
 *
 */
public class TokenContext {

  private static final ThreadLocal<TokenData> CONTEXT = new ThreadLocal<TokenData>();

  public static void set(TokenData data) {
    CONTEXT.set(data);
  }

  public static TokenData get() {
    return CONTEXT.get();
  }

  public static void remove() {
    CONTEXT.remove();
  }
}
