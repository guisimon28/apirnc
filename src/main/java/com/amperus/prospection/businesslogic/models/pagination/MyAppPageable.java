package com.amperus.prospection.businesslogic.models.pagination;

public class MyAppPageable {
    private final int page;
    private final int pageSize;

    public MyAppPageable(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }
}