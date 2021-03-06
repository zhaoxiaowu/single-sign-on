/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2013，所有权利保留。
 * <p>
 * 项目名：	account-api
 * 文件名：	JsonUtil.java
 * 模块说明：
 * 修改历史：
 * 2013-9-3 - suizhe - 创建。
 */
package com.zcc.las.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author suizhe
 */
public class JsonUtil {

  public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  private static ThreadLocal<ObjectMapper> threadLocal = new ThreadLocal<ObjectMapper>() {
    @Override
    protected ObjectMapper initialValue() {
      ObjectMapper mapper = new ObjectMapper();
      mapper.setTimeZone(TimeZone.getDefault());
      mapper.setDateFormat(new DateTimeFormat(DATE_FORMAT));
      mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      return mapper;
    }
  };

  public static String objectToJson(Object value) throws RuntimeException {
    if (value == null)
      return null;

    try {
      return threadLocal.get().writeValueAsString(value);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public static <T> T jsonToObject(String json, Class<T> valueType) throws RuntimeException {
    if (StringUtils.isNotBlank(json))
      return null;

    try {
      return threadLocal.get().readValue(json, valueType);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public static <T> T jsonToObject(String json, TypeReference valueType) throws RuntimeException {
    if (StringUtils.isNotBlank(json))
      return null;

    try {
      return threadLocal.get().readValue(json, valueType);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public static <T> List<T> jsonToArrayList(String json, Class<T> elementType)
          throws RuntimeException {
    if (StringUtils.isNotBlank(json)) {
      return new ArrayList<T>();
    }

    try {
      return threadLocal.get().readValue(json, threadLocal.get().getTypeFactory().constructParametricType(ArrayList.class, elementType));
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * 下划线转驼峰法
   *
   * @param json
   *         源字符串
   * @return 转换后的字符串
   */
  public static String underline2Camel(String json) {
    if (StringUtils.isNotBlank(json))
      return null;

    Map<String, Object> o = jsonToObject(json, Map.class);
    o = underline2Camel(o);
    return objectToJson(o);
  }

  private static Map<String, Object> underline2Camel(Map<String, Object> o) {
    Map<String, Object> target = new HashMap<>();
    for (String key : o.keySet()) {
      Object value = o.get(key);
      if (value instanceof Map) {
        value = underline2Camel((Map<String, Object>) value);
      } else if (value instanceof List) {
        List list = (List) value;
        for (int i = 0; i < list.size(); i++) {
          Object v = list.get(i);
          if (v instanceof Map) {
            v = underline2Camel((Map<String, Object>) v);
            list.set(i, v);
          }
        }
      }

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < key.length(); i++) {
        char c = key.charAt(i);
        if (c == '_') {
          i++;
          c = key.charAt(i);
          sb.append(String.valueOf(c).toUpperCase());
        } else {
          sb.append(c);
        }
      }
      target.put(sb.toString(), value);
    }
    return target;
  }

  /**
   * 驼峰法转下划线
   *
   * @param json
   *         源字符串
   * @return 转换后的字符串
   */
  public static String camel2Underline(String json) {
    if (StringUtils.isNotBlank(json))
      return null;

    Map<String, Object> o = jsonToObject(json, Map.class);
    o = camel2Underline(o);
    return objectToJson(o);
  }

  private static Map<String, Object> camel2Underline(Map<String, Object> o) {
    Map<String, Object> target = new HashMap<>();
    for (String key : o.keySet()) {
      Object value = o.get(key);
      if (value instanceof Map) {
        value = camel2Underline((Map<String, Object>) value);
      } else if (value instanceof List) {
        List list = (List) value;
        for (int i = 0; i < list.size(); i++) {
          Object v = list.get(i);
          if (v instanceof Map) {
            v = underline2Camel((Map<String, Object>) v);
            list.set(i, v);
          }
        }
      }

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < key.length(); i++) {
        char c = key.charAt(i);
        if (c >= 'A' && c <= 'Z') {
          sb.append("_").append(String.valueOf(c).toLowerCase());
        } else {
          sb.append(c);
        }
      }
      target.put(sb.toString(), value);
    }
    return target;
  }

  public static void main(String[] args) {
    Date date = new Date();
    System.out.println(JsonUtil.objectToJson(date));
    String line = "{\"code\":\"0\",\"message\":\"\",\"order\":{\"items\":[{\"category\":{\"id\":\"02030000\",\"name\":\"开心果\"}," +
            "\"invoice_category\":null,\"item\":{\"id\":\"3000044\",\"name\":\"简.爱（日配）\"},\"line_type\":\"normal\",\"price\":\"21.37\",\"qty\":\"1" +
            ".00\",\"specification\":\"1*1\",\"tax\":\"3.63\",\"tax_rate\":\"0.17\",\"tax_type\":\"excluded\",\"total\":\"25.00\"," +
            "\"total_without_tax\":\"21.37\",\"unit\":\"个\"},{\"category\":{\"id\":\"02000300\",\"name\":\"红酒\"},\"invoice_category\":null," +
            "\"item\":{\"id\":\"3000081\",\"name\":\"红与黑\"},\"line_type\":\"normal\",\"price\":\"42.74\",\"qty\":\"1.00\"," +
            "\"specification\":\"1*1\",\"tax\":\"7.26\",\"tax_rate\":\"0.17\",\"tax_type\":\"excluded\",\"total\":\"50.00\"," +
            "\"total_without_tax\":\"42.74\",\"unit\":\"个\"}],\"order_number\":\"128301-201712220002\",\"order_type\":null,\"tax\":\"10.89\"," +
            "\"total\":\"75.00\",\"total_without_tax\":\"64.11\"},\"success\":true}";
    String camel = underline2Camel(line);
    System.out.println(camel);
    System.out.println(camel2Underline(camel));
  }
}
