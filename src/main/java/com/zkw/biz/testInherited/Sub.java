/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	single-sign-on
 * 文件名：	Sub.java
 * 模块说明：
 * 修改历史：
 * 2019/5/27 - wuhongyun - 创建。
 */
package com.zkw.biz.testInherited;

/**
 * @author wuhongyun
 */
@DBTable2
class Sub extends Super{
  private int subPrivateF;
  public int subPublicF;

  private Sub(){
  }
  public Sub(int i){
  }

  private int subPrivateM(){
    return 0;
  }
  public int subPubliceM(){
    return 0;
  }
}
