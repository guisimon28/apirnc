package com.amperus.prospection.businesslogic.models.pagination;

import java.util.Collections;
import java.util.List;

public class MyAppPage<T> {
    private static final int MINIMAL_PAGE_COUNT = 1;

    private final List<T> content;
    private final int currentPage;
    private final int pageSize;
    private final long totalElementsCount;
    private final int pageCount;

    private MyAppPage(Builder<T> builder) {
        content = buildContent(builder.content);
        currentPage = builder.currentPage;
        pageSize = buildPageSize(builder.pageSize);
        totalElementsCount = buildTotalElementsCount(builder.totalElementsCount);
        pageCount = buildPageCount();
    }

    private List<T> buildContent(List<T> content) {
        if (content == null) {
            return List.of();
        }

        return Collections.unmodifiableList(content);
    }

    private int buildPageSize(int pageSize) {
        if (pageSize == -1) {
            return content.size();
        }

        return pageSize;
    }

    private long buildTotalElementsCount(long totalElementsCount) {
        if (totalElementsCount == -1) {
            return content.size();
        }

        return totalElementsCount;
    }

    public int buildPageCount() {
        if (totalElementsCount > 0) {
            return (int) Math.ceil(totalElementsCount / (float) pageSize);
        }

        return MINIMAL_PAGE_COUNT;
    }

    public static <T> Builder<T> builder(List<T> content) {
        return new Builder<>(content);
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

    public int getPageCount() {
        return pageCount;
    }

    public static class Builder<T> {
        private final List<T> content;
        private int currentPage;
        private int pageSize = -1;
        private long totalElementsCount = -1;

        private Builder(List<T> content) {
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

        public MyAppPage<T> build() {
            return new MyAppPage<>(this);
        }
    }
}