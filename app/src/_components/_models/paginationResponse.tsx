interface PaginationResponse<T> {
    currentPage: number;
    pageSize: number;
    nextPage: number | null;
    previousPage: number | null;
    hasNextPage: boolean;
    hasPreviousPage: boolean;
    totalPages: number;
    content:  Iterable<T>;
}

export type { PaginationResponse };