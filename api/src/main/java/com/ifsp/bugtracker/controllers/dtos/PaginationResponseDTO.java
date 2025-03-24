package com.ifsp.bugtracker.controllers.dtos;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;


public record PaginationResponseDTO<T>(
    int currentPage,
    int pageSize,
    Integer nextPage,
    Integer previousPage,
    Boolean hasNextPage,
    Boolean hasPreviousPage,
    int totalPages,
    List<T> content
) {

    public static <E, T> PaginationResponseDTO<T> map(
        Page<E> page,
        Function<E, T> mapper
    ) {
        List<T> transformedContent = page.getContent().stream()
            .map(mapper)
            .toList();

        Integer nextPage = page.hasNext() ? page.getNumber() + 1 : null;
        Integer previousPage = page.hasPrevious() ? page.getNumber() - 1 : null;

        return new PaginationResponseDTO<>(
            page.getNumber(),
            page.getSize(),
            nextPage,
            previousPage,
            page.hasNext(),
            page.hasPrevious(),
            page.getTotalPages(),
            transformedContent
        );
    }
}
