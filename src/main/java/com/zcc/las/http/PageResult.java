/**
 * 版权所有(C)，上海海鼎信息工程股份有限公司，2018，所有权利保留。
 * <p>
 * 项目名：	single-sign-on
 * 文件名：	PageResult.java
 * 模块说明：
 * 修改历史：
 * 2019/5/29 - wuhongyun - 创建。
 */
package com.zcc.las.http;

import com.zcc.las.sdk.ActionResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页返回体
 *
 * @author wuhongyun
 */
public class PageResult<T> extends ActionResult<T> {
  @ApiModelProperty(value = "分页信息")
  private QueryResultPaging paging = new QueryResultPaging();


  public void writePageSize(int pageSize) {
    this.paging.setPageSize(pageSize);
  }

  public void writePage(int page) {
    this.paging.setPage(page);
  }

  public void writePageCount(int pageCount) {
    this.paging.setPageCount(pageCount);
  }

  public void writeRecordCount(long recordCount) {
    this.paging.setRecordCount(recordCount);
  }

  public void writeMore(boolean more) {
    this.paging.setMore(more);
  }

  public void writePaging(int pageSize, int page, int pageCount, int recordCount, boolean more) {
    writePageSize(pageSize);
    writePage(page);
    writePageCount(pageCount);
    writeRecordCount(recordCount);
    writeMore(more);
  }
}
