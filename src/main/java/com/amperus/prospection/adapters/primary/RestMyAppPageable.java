package com.amperus.prospection.adapters.primary;


import com.amperus.prospection.businesslogic.models.pagination.MyAppPageable;

public class RestMyAppPageable {
    private int page;

    private int pageSize = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public MyAppPageable toPageable() {
        return new MyAppPageable(page, pageSize);
    }
}