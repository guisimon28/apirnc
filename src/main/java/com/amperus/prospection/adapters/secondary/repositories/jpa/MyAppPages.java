package com.amperus.prospection.adapters.secondary.repositories.jpa;

import com.amperus.prospection.businesslogic.models.pagination.MyAppPage;
import com.amperus.prospection.businesslogic.models.pagination.MyAppPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;

public final class MyAppPages {

    private MyAppPages() {
    }

    public static Pageable from(MyAppPageable pagination) {
        return from(pagination, Sort.unsorted());
    }

    public static Pageable from(MyAppPageable pagination, Sort sort) {
        return PageRequest.of(pagination.getPage() - 1, pagination.getPageSize(), sort);
    }

    public static <S, T> MyAppPage<T> from(Page<S> springPage, Function<S, T> mapper) {
        List<T> content = springPage
                .getContent()
                .stream()
                .map(mapper)
                .toList();

        return MyAppPage
                .builder(content)
                .currentPage(springPage.getNumber() + 1)
                .pageSize(springPage.getSize())
                .totalElementsCount(springPage.getTotalElements())
                .build();
    }
}