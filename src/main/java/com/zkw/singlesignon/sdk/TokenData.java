package com.zkw.singlesignon.sdk;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuhongyun
 */
public class TokenData {
    //发布时间
    protected static final String KEY_ISSUED_AT = "iat";
    //发行人
    protected static final String KEY_ISSUER = "iss";
    //主题
    protected static final String KEY_SUBJECT = "sub";
    //用户
    protected static final String KEY_AUDIENCE = "aud";
    //过期时间
    protected static final String KEY_EXPIRE = "exp";

    private Map<String,Object> map = new HashMap<>();

    public TokenData(){}

    public  TokenData(Map<String,Object> map) {
        this.map = map;
    }

    public TokenData put(String key ,Object value){
        this.map.put(key, value);
        return this;
    }

    public Object get(String key) {
        return this.map.get(key);
    }

    public static String getKeyIssuedAt() {
        return KEY_ISSUED_AT;
    }

    public static String getKeyExpire() {
        return KEY_EXPIRE;
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
