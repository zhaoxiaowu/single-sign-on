package com.zkw.singlesignon.sdk;

/**
 * 请求结果响应
 *
 * @author wuhongyun
 */
public class ActionResult<T> {
    public static final ActionResult OK = new ActionResult();
    private boolean success =true;
    private String code;
    private String message;
    private T data;

    public ActionResult(){}

    public ActionResult(T data){
        this.data = data;
    }

    public static ActionResult ok(){
        return OK;
    }

    public static ActionResult fail(String message){
        ActionResult r = new ActionResult();
        r.setSuccess(false);
        r.setMessage(message);
        return r;
    }

    public static ActionResult fail(String code, String message){
        ActionResult r = new ActionResult();
        r.setSuccess(false);
        r.setMessage(message);
        r.setCode(code);
        return r;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
