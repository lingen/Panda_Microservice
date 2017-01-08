package org.openpanda.microservice.ddd.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lingen on 2016/12/23.
 */
public class PandaPage<T> implements Serializable {

    private long total;

    private int pageCount;

    private int totalPage;

    private int page;

    private List<T> values;

    public PandaPage(){

    }

    public PandaPage(long total,int pageCount,int totalPage,int page,List<T> values){
        this.total = total;
        this.pageCount = pageCount;
        this.totalPage = totalPage;
        this.page = page;
        this.values = values;
    }

    public static PandaPage createSinglePandaPage(List values){
        PandaPage pandaPage = new PandaPage();
        pandaPage.total = values.size();
        pandaPage.pageCount = 1;
        pandaPage.totalPage = 1;
        pandaPage.values = values;
        return pandaPage;
    }

    //========== GET  SET ============
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public List<T> getValues() {
        return values;
    }
    public void setValues(List<T> values) {
        this.values = values;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    //=====================================


}
