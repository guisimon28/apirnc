package com.amperus.prospection.adapters.primary;


import com.amperus.prospection.businesslogic.models.pagination.MyAppPageable;

public class RestMyAppPageable {
    private int page = 1;

    private int pageSize = 10;

    private String searchTerm;

    private RestMyAppSort sort;

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

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public RestMyAppSort getSort() {
        return sort;
    }

    public void setSort(RestMyAppSort sort) {
        this.sort = sort;
    }

    public MyAppPageable toPageable() {
        return new MyAppPageable.Builder()
                .page(page)
                .pageSize(pageSize)
                .searchTerm(searchTerm)
                .sort(sort != null ? sort.toSort() : null)
                .build();
    }
}