package com.zkw.singlesignon.controller;

import com.zkw.singlesignon.entity.User;
import com.zkw.singlesignon.sdk.ActionResult;
import com.zkw.singlesignon.sdk.AuthToken;
import com.zkw.singlesignon.sdk.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证
 *
 * @author wuhongyun
 */
@Api("登录验证")
@RestController
@RequestMapping(value="/")
public class LoginController {

    @Value("${login.username}")
    private String username;

    @Value("${login.password}")
    private String password;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthToken authToken;


    @PostMapping(value = "login")
    @ApiOperation(value = "登录验证", notes = "登录验证")
    public ActionResult login(HttpServletRequest request, HttpServletResponse response,@RequestBody User user) throws Exception{
        if(!(username.equals(user.getUsername()) &&  password.equals(user.getPassword()))) {
            //return ActionResult.fail("用户名或密码错误");
            throw new Exception("用户名密码错误");
        }
        // 签发token
        setAuthToken(request, response, user);
        return ActionResult.OK;
    }
    @GetMapping(value = "test")
    @ApiOperation(value = "测试")
    public String test(HttpServletRequest request, HttpServletResponse response){
        return "成功登陆";
    }

    /**
     * 签发token
     */
    private void setAuthToken(HttpServletRequest request, HttpServletResponse response, User user) {
        authToken.getUsername(user.getUsername());
        authToken.getPassword(user.getPassword());
        //签发token
        tokenService.issue(request, response);
    }
}

