package com.zkw.singlesignon.sdk;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 存储在JWT中的内容
 */
@Component(Token.BEAN_ID)
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AuthToken extends Token {

  private String username;

  private String password;

  public String getUsername(String username) {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword(String password) {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
