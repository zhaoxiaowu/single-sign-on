/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	single-sign-on
 * 文件名：	QueryResultPaging.java
 * 模块说明：
 * 修改历史：
 * 2019/5/29 - wuhongyun - 创建。
 */
package com.zkw.singlesignon.http;

/**
 * 分页计算
 *
 * @author wuhongyun
 */
public class QueryResultPaging {
  private int pageSize = 0;
  private int page = 0;
  private int pageCount = 0;
  private long recordCount = 0;
  private Boolean more = false;

  /** 每页最大显示记录数，0表示包含全部结果集。 */
  public int getPageSize() {
    return pageSize;
  }

  /** @see #getPageSize() */
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize < 0 ? 0 : pageSize;
  }

  /** 当前页号，从0计数。 */
  public int getPage() {
    return page;
  }

  /** @see #getPage() */
  public void setPage(int page) {
    this.page = page < 0 ? 0 : page;
  }

  /** 查询结果集总页数，当属性{@link #getMore()}==true时，意味着有可能实际总页数会大于取值。 */
  public int getPageCount() {
    return pageCount;
  }

  /** @see #getPageCount() */
  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }

  /** 查询结果集总记录数，当属性{@link #getMore()}==true时，意味着有可能实际总记录数会大于取值。。 */
  public long getRecordCount() {
    return recordCount;
  }

  /** @see #getRecordCount() */
  public void setRecordCount(long recordCount) {
    this.recordCount = recordCount;
  }

  /** 是否还有更多数据未被发现。 */
  public Boolean getMore() {
    return more;
  }

  /** @see #getMore() */
  public void setMore(Boolean more) {
    this.more = more;
  }
}
