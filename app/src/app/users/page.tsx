'use client'

import { PaginationResponse } from "@/_components/_models/paginationResponse";
import { Pagination } from "@/_components/pagination";
import { UserList } from "./_components/userList";
import type { UserResponse } from "./_components/userList";

const UserListR: UserResponse[] = [
        {userId: "1"},
        {userId: '2'},
        {userId: '3'},
        {userId: '4'},
        {userId: '5'}
]

const paginationTest: PaginationResponse<UserResponse> = {
    currentPage: 2,
    pageSize: 5,
    nextPage: 3,
    previousPage: 1,
    hasNextPage: true,
    hasPreviousPage: true,
    totalPages: 3,
    Data: UserListR
}

export default function Page() {
    // const { setComponent } = useRootLayoutContext();

    function handler(event: React.MouseEvent<HTMLButtonElement>) {}

    // setComponent(<h1>Users</h1>);
    
    return (
        <section className="flex justify-around">
            <Pagination className="max-w-1/2" pagination={paginationTest} handlerPagination={handler}>
                <UserList users={UserListR} />
            </Pagination>
        </section>
    );
}