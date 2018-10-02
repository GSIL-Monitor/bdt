package cn.com.infaith.module.util;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
    public class PageBean<T> {
    //数据集合
    private List<T> list;
    //总页数
    private int totalPage = 1;
    //页面大小，即每页显示记录数
    private int pageSize = 10;
    //当前页号
    private int pageIndex = 1;
    //记录总数
    private Long totalCount = 0L;
    private int startRow;
    private int endRow;

    public PageBean() {
        super();
    }

    public PageBean(int pageSize, int pageIndex, Long total) {
        setPageSize(pageSize);
        setTotalCount(total);
        setPageIndex(pageIndex);
        setStartRow((pageIndex - 1) * pageSize);
        setEndRow(pageIndex * pageSize);
    }

    public void setPageIndex(int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        } else if (pageIndex > getTotalPage()) {
            pageIndex = getTotalPage();
        }
        this.pageIndex = pageIndex;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public void setTotalCount(Long totalCount) {
        if (totalCount > 0) {
            this.totalCount = totalCount;
            //计算总页数
            totalPage = this.totalCount.intValue() % pageSize == 0 ? (this.totalCount.intValue() / pageSize) :
                    this.totalCount.intValue() / pageSize + 1;
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageIndex() {

        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotalCount() {
        return totalCount;
    }


}
