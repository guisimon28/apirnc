package com.amperus.prospection.businesslogic.models.pagination;

public class MyAppSort {
    private final String path;
    private final MyAppSortDirection direction;

    private MyAppSort(Builder builder) {
        this.path = builder.path;
        this.direction = builder.direction;
    }

    public String getPath() {
        return path;
    }

    public MyAppSortDirection getDirection() {
        return direction;
    }

    public static class Builder {
        private String path;
        private MyAppSortDirection direction;

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder direction(MyAppSortDirection direction) {
            this.direction = direction;
            return this;
        }

        public MyAppSort build() {
            return new MyAppSort(this);
        }
    }
}