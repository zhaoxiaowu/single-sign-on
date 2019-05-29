/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	single-sign-on
 * 文件名：	Genericity.java
 * 模块说明：
 * 修改历史：
 * 2019/5/27 - wuhongyun - 创建。
 */
package com.zkw.biz.genericity;

/**
 * 泛型 即“参数化类型”   泛型只在编译阶段有效
 * 泛型有三种使用方式，分别为：泛型类、泛型接口、泛型方法
 *
 * @author wuhongyun
 */

//在实例化泛型类时，必须指定T的具体类型
public class Genericity<T> {
  //key这个成员变量的类型为T,T的类型由外部指定
  private T key;

  public Genericity(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
    this.key = key;
  }

  public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
    return key;
  }

  /**
   * 泛型方法的基本介绍
   * @param tClass 传入的泛型实参
   * @return T 返回值为T类型
   * 说明：
   *     1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
   *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
   *     3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
   *     4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
   */
  // 泛型 T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。
  public <T> T genericMethod(Class<T> tClass)throws InstantiationException ,
          IllegalAccessException{
    T instance = tClass.newInstance();
    return instance;
  }


  public static void main(String[] args) {
//泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
//传入的实参类型需与泛型的类型参数类型相同，即为Integer.
    Genericity<Integer> genericInteger = new Genericity<Integer>(123456);

//传入的实参类型需与泛型的类型参数类型相同，即为String.
    Genericity<String> genericString = new Genericity<String>("key_vlaue");
    System.out.println(genericInteger.getKey());
    System.out.println(genericString.getKey());

    //如果不传入泛型类型实参的话，在泛型类中使用泛型的方法或成员变量定义的类型可以为任何的类型。
    Genericity generic = new Genericity("111111");
    Genericity generic1 = new Genericity(4444);
    System.out.println("--------------------------------------------------------");
    System.out.println(generic.getKey());
    System.out.println(generic1.getKey());

    //object 和 泛型的区别
    //1.不需要做强制类型转换
    //2.编译时更安全。如果使用Object类的话，你没法保证返回的类型一定是Foo，也许是其它类型。这时你就会在运行时得到一个类型转换异常（ClassCastException）


    //泛型方法   泛型方法，是在调用方法的时候指明泛型的具体类型 。

    //T... args    泛型方法的可变参数

    // 静态方法 和泛型
    // 如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法）
    // 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
  }
}
