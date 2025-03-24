'use client'

import { PaginationResponse } from "@/_components/_models/paginationResponse";
import { Pagination } from "@/_components/pagination";
import { IssueResponse, IssueStatus, IssueList } from "./_components/issueList";

const IssueListTest: IssueResponse[] = [
    {
        title: "Issue 1",
        description: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        date: new Date('2023-10-01'),
        code: "#ISSUE-001",
        owner: { name: "Alice", pictureUrl: "https://images.unsplash.com/photo-1519244703995-f4e0f30006d5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80" },
        status: IssueStatus.New
    },
    {
        title: "Issue 2",
        description: "This issue is pending.",
        date: new Date('2023-10-02'),
        code: "#ISSUE-002",
        owner: { name: "Bob", pictureUrl: "https://images.unsplash.com/photo-1519244703995-f4e0f30006d5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80" },
        status: IssueStatus.Pending
    },
    {
        title: "Issue 3",
        description: "This issue is in progress.",
        date: new Date('2023-10-03'),
        code: "#ISSUE-003",
        owner: { name: "Charlie", pictureUrl: "https://images.unsplash.com/photo-1519244703995-f4e0f30006d5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80" },
        status: IssueStatus.InProgress
    },
    {
        title: "Issue 4",
        description: "This issue has been resolved.",
        date: new Date('2023-10-04'),
        code: "#ISSUE-004",
        owner: { name: "Diana", pictureUrl: "https://images.unsplash.com/photo-1519244703995-f4e0f30006d5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80" },
        status: IssueStatus.Resolved
    },
    {
        title: "Issue 5",
        description: "This issue has been cancelled.",
        date: new Date('2023-10-05'),
        code: "#ISSUE-005",
        owner: { name: "Eve", pictureUrl: "https://images.unsplash.com/photo-1519244703995-f4e0f30006d5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80" },
        status: IssueStatus.Cancelled
    }
];

const paginationTest: PaginationResponse<IssueResponse> = {
    currentPage: 2,
    pageSize: 5,
    nextPage: 3,
    previousPage: 1,
    hasNextPage: true,
    hasPreviousPage: true,
    totalPages: 3,
    content: IssueListTest
}

export default function Page() {
    
    function handler(event: React.MouseEvent<HTMLButtonElement>) {}

    return (
        <Pagination className="max-w-1/2" pagination={paginationTest} handlerPagination={handler}>
            <IssueList issues={IssueListTest} />
        </Pagination>
    );
}