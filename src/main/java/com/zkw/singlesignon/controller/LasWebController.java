/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2019，所有权利保留。
 * <p>
 * 项目名：	service-center
 * 文件名：	SasWebController.java
 * 模块说明：
 * 修改历史：
 * 2019/5/14 - zhuchao - 创建。
 */
package com.zkw.singlesignon.controller;

import com.zkw.singlesignon.exception.LogoutException;
import com.zkw.singlesignon.sdk.TokenContext;
import com.zkw.singlesignon.sdk.TokenData;

/**
 * @author zhuchao
 */
public abstract class LasWebController {

  /** 用户ID */
  private static final String USER_ID = "userId";

//  /** 获取当前租户id */
//  protected String getCurrentTenantId() {
//    TokenData tokenData = TokenContext.get();
//    if (tokenData == null)
//      throw new LogoutException();
//    return (String) tokenData.get(ApplicationConstant.TENANT_ID);
//  }

  /** 获取当前用户id */
  protected String getCurrentUserId() {
    TokenData tokenData = TokenContext.get();
    if (tokenData == null){
      throw new LogoutException();
    }
    return (String) tokenData.get(LasWebController.USER_ID);
  }
}
