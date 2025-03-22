'use client'

import { User, UserSkeleton } from '@/app/users/_components/user';

export interface UserResponse {
  userId: string
}

const UserList = ({ users }:
  { users: UserResponse[] }) => {

  if ( !users )
  {
    return (
      <ul role="list" className="divide-y divide-gray-100">
        {Array(5).fill(0).map((_, index) => (
          <UserSkeleton key={index} />
        ))}
      </ul>
    );
  }

  return (
    <ul role="list" className="divide-y divide-gray-100">
      {users.map((user) => (
        <User key={user.userId} userId={user.userId}/> 

      ))}
    </ul>
  );
}

export { UserList };