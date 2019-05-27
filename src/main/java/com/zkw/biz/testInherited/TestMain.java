/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	single-sign-on
 * 文件名：	TestMain.java
 * 模块说明：
 * 修改历史：
 * 2019/5/27 - wuhongyun - 创建。
 */
package com.zkw.biz.testInherited;

import java.util.Arrays;

/**
 * @author wuhongyun
 */
public class TestMain {
  public static void main(String[] args) {
    Class<Sub> clazz = Sub.class;
    System.out.println("============================Field===========================");
    System.out.println(Arrays.toString(clazz.getFields()));
    System.out.println("-----------------------------------------------------");
    System.out.println(Arrays.toString(clazz.getDeclaredFields()));  //all + 自身
    System.out.println("============================Method===========================");
    System.out.println(Arrays.toString(clazz.getMethods()));   //public + 继承
    //all + 自身
    System.out.println(Arrays.toString(clazz.getDeclaredMethods()));
    System.out.println("============================Constructor===========================");
    System.out.println(Arrays.toString(clazz.getConstructors()));
    System.out.println(Arrays.toString(clazz.getDeclaredConstructors()));
    System.out.println("============================AnnotatedElement===========================");
    //注解DBTable是否存在于元素上
    System.out.println(clazz.isAnnotationPresent(DBTable.class));
    //如果存在该元素的指定类型的注释DBTable，则返回这些注释，否则返回 null。
    System.out.println(clazz.getAnnotation(DBTable.class));
    //继承
    System.out.println(Arrays.toString(clazz.getAnnotations()));
    System.out.println(Arrays.toString(clazz.getDeclaredAnnotations()));  ////自身
  }
}
