'use client'

import { CheckCircleIcon, XCircleIcon, ClockIcon, ExclamationCircleIcon, DocumentDuplicateIcon } from '@heroicons/react/24/outline';

export interface OwnerResponse {
    name: string,
    pictureUrl: string
}

export enum IssueStatus {
    New = 0, 
    Pending = 1,
    InProgress = 2,
    Resolved = 3,
    Cancelled = 4
}

export const IssueIcons = {
    [IssueStatus.New]: <DocumentDuplicateIcon className="h-6 w-6 text-blue-500" />,
    [IssueStatus.Pending]: <ClockIcon className="h-6 w-6 text-yellow-500" />,
    [IssueStatus.InProgress]: <ExclamationCircleIcon className="h-6 w-6 text-orange-500" />,
    [IssueStatus.Resolved]: <CheckCircleIcon className="h-6 w-6 text-green-500" />,
    [IssueStatus.Cancelled]: <XCircleIcon className="h-6 w-6 text-red-500" />
};

export interface IssueResponse {
    title: string,
    description: string,
    date: Date,
    code: string,
    owner: OwnerResponse,
    status: IssueStatus
}

export const IssueItem = ({ issue }:
{issue: IssueResponse }) => {
    return (
    <li className="gap-x-6 py-5">
        <div className="flex justify-between items-center">
            <div className="flex min-w-0 gap-x-4">
                <div className="size-10 flex-none rounded-full bg-gray-50 flex justify-center items-center">
                    {IssueIcons[issue.status]}
                </div>
                <div className="min-w-0 flex-auto">
                    <p className="text-sm/6 font-semibold text-gray-900">{issue.title}</p>
                    <div className='flex'>
                        <p className="text-xs/5 px-2 rounded-full bg-slate-100">{issue.code}</p>
                        <p className='text-xs/5 px-2 rounded-full bg-slate-100 ml-3'>{IssueStatus[issue.status]}</p>
                    </div>
                </div>
            </div>
            <div className="hidden shrink-0 sm:flex sm:flex-col sm:items-end">
                <div className='flex justify-end items-center'>
                    <img className="size-6 flex-none rounded-full bg-gray-50" src={issue.owner.pictureUrl} alt={`${issue.owner.name} picture.`} />
                    <p className="text-sm/6 text-gray-900 ml-2">{issue.owner.name}</p>
                </div>
                <p className="mt-1 text-xs/5 text-gray-500">create at: <time dateTime={issue.date.toISOString()}>{`${issue.date.toLocaleTimeString()} ${issue.date.toLocaleDateString()}`}</time></p>
            </div>
        </div>
        <div className='mt-2 max-w-full min-w-full bg-slate-100 px-3 py-2 rounded-lg'>
            <p className="mt-1 truncate text-xs/5 text-gray-500">{issue.description}</p>
        </div>
    </li>
    );    
}

export const IssueList = ({ issues }: 
{ issues: IssueResponse[] }) => {
    
    return (
        <ul role="list" className="divide-y divide-gray-100">
            {issues.map((issue) => (
                <IssueItem key={issue.code} issue={issue}/>
            ))}
        </ul>
    );
}

