import { PaginationResponse } from '@/_components/_models/paginationResponse';
import { IssueResponse } from '@/app/issues/_components/issueList';
const url = "https://refactored-fishstick-9qwgpvwxvwxfxgr6-8080.app.github.dev";
const issuePath = "/api/v1/issues";

export const fetchDataIssues = async (
    currentPage: number = 1,
    pageSize: number = 5,
    filterLabel: string = "",
) : Promise<PaginationResponse<IssueResponse>> => {
    try {
        const response = await fetch(
            `${url}${issuePath}/?page=${currentPage}&size=${pageSize}`,
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                }
            }
        );

        if (!response.ok) {
            throw new Error("Failed to fetch rooms");
        }

        const data: PaginationResponse<IssueResponse> = (await response.json()).paginatedItems;
        return data;
    } catch (err) {
        return err.message;
    }
}
