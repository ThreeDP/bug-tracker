'use client'

import { PaginationResponse } from "@/_components/_models/paginationResponse";
import { Pagination } from "@/_components/pagination";
import { IssueResponse, IssueStatus, IssueList } from "./_components/issueList";
import { useEffect, useState } from "react";
import { fetchDataIssues } from "@/_components/_repositories/IssueRepository";

export default function Page() {

    const [pagination, setPagination] = useState<PaginationResponse<IssueResponse> | null>(null)
    const [issues, setIssues] = useState<IssueResponse[] | null>(null)

    const fetchIssues = async (page: number) => {
        setIssues(null);
        const data = await fetchDataIssues(page)
        setPagination(data);
        setIssues(data.content);
    }

    useEffect(() => {
        fetchDataIssues(1);
    }, []);


    function handler(event: React.MouseEvent<HTMLButtonElement>) {
        const page = parseInt(event.currentTarget.value);
        fetchIssues(page);
    }

    return (
        <Pagination className="max-w-1/2" pagination={pagination} handlerPagination={handler}>
            <IssueList issues={issues} />
        </Pagination>
    );
}