'use client'

import React from "react";

export interface UserProps {
    userId: string;
}

const UserSkeleton: React.FC = () => {
    return (
        <div></div>
    );
}

const User: React.FC<UserProps> = ({ userId }) => {
    return (
        <h1>User {userId}</h1>
    );
};

export { User, UserSkeleton };