package com.amperus.prospection.businesslogic.models.pagination;

public class MyAppPageable {

    private final int page;
    private final int pageSize;
    private final String searchTerm;

    private final MyAppSort sort;

    private MyAppPageable(Builder builder) {
        this.page = builder.page;
        this.pageSize = builder.pageSize;
        this.searchTerm = builder.searchTerm;
        this.sort = builder.sort;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public MyAppSort getSort() {
        return sort;
    }

    public static class Builder {
        private int page;
        private int pageSize;
        private String searchTerm;
        private MyAppSort sort;

        public Builder page(int page) {
            this.page = page;
            return this;
        }

        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder searchTerm(String searchTerm) {
            this.searchTerm = searchTerm;
            return this;
        }

        public Builder sort(MyAppSort sort) {
            this.sort = sort;
            return this;
        }

        public MyAppPageable build() {
            return new MyAppPageable(this);
        }
    }
}