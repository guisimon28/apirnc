package com.amperus.prospection.adapters.primary;

import java.util.List;

public class RestMyAppPage<T> {
    private final List<T> content;

    private final int currentPage;

    private final int pageSize;

    private final long totalElementsCount;

    private final int pagesCount;

    private RestMyAppPage(Builder<T> builder) {
        content = builder.content;
        currentPage = builder.currentPage;
        pageSize = builder.pageSize;
        totalElementsCount = builder.totalElementsCount;
        pagesCount = builder.pageCount;
    }

    public List<T> getContent() {
        return content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalElementsCount() {
        return totalElementsCount;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public static class Builder<T> {
        private final List<T> content;
        private int currentPage;
        private int pageSize;
        private long totalElementsCount;
        private int pageCount;

        Builder(List<T> content) {
            this.content = content;
        }

        public Builder<T> pageSize(int pageSize) {
            this.pageSize = pageSize;

            return this;
        }

        public Builder<T> currentPage(int currentPage) {
            this.currentPage = currentPage;

            return this;
        }

        public Builder<T> totalElementsCount(long totalElementsCount) {
            this.totalElementsCount = totalElementsCount;

            return this;
        }

        public Builder<T> pageCount(int pageCount) {
            this.pageCount = pageCount;

            return this;
        }

        public RestMyAppPage<T> build() {
            return new RestMyAppPage<>(this);
        }
    }
}