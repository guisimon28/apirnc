package com.amperus.prospection.adapters.primary;

import com.amperus.prospection.businesslogic.models.pagination.MyAppSort;
import com.amperus.prospection.businesslogic.models.pagination.MyAppSortDirection;

public class RestMyAppSort {
    private String path;
    private RestMyAppSortDirection direction;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RestMyAppSortDirection getDirection() {
        return direction;
    }

    public void setDirection(RestMyAppSortDirection direction) {
        this.direction = direction;
    }

    public MyAppSort toSort() {
        return new MyAppSort.Builder()
                .path(path)
                .direction(convertDirection())
                .build();
    }

    private MyAppSortDirection convertDirection() {
        return switch (direction) {
            case null -> null;
            case ASC -> MyAppSortDirection.ASC;
            case DSC -> MyAppSortDirection.DSC;
            default -> null;
        };
    }

}