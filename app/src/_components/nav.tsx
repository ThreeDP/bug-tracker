'use client'

import { Disclosure, DisclosureButton, DisclosurePanel } from '@headlessui/react';
import { Bars3Icon, BellIcon, XMarkIcon } from '@heroicons/react/24/outline'
import Link from 'next/link';
import React from 'react';
import NavOptions from './navOptions';
import {
  UsersIcon,
  ChartPieIcon,
  ExclamationCircleIcon,
} from '@heroicons/react/24/outline';

 
import { usePathname } from 'next/navigation'
import { DesignSystem } from './designSystem';

interface NavigationItem {
    name: string;
    href: string;
    current: boolean;
    icon: React.ElementType;
}

interface NavigationItemProps {
    navigation: NavigationItem[];
}

const navItems: NavigationItem[] = [
  { name: 'Issues', href: '/issues', current: true, icon:  UsersIcon },
  { name: 'Users', href: '/users', current: false, icon: ExclamationCircleIcon},
  { name: 'Analytics', href: "/analytics", current: false, icon: ChartPieIcon}
]

function classNames(...classes: (string | undefined | null)[]): string {
    return classes.filter(Boolean).join(' ');
}

const NavIcon: React.FC = () => {
    return (
        <Link  href='/' className="flex shrink-0 items-center">
            <img
            alt="Bug Tracker"
            src="https://tailwindcss.com/plus-assets/img/logos/mark.svg?color=emerald&shade=400"
            className="h-8 w-auto"
            />
        </Link>
    );
}

const NavItems: React.FC<NavigationItemProps> = ({ navigation }) => {
    const currentPath = usePathname();
    return (
        <div className="hidden sm:ml-6 sm:block">
            <div className="flex space-x-4">
            {navigation.map((item) => (
                <Link
                key={item.name}
                href={item.href}
                // aria-current={item.current ? 'page' : undefined}
                className={classNames(
                    currentPath.startsWith(item.href) ? `bg-gray-900 ${DesignSystem.textPrimary}` : `text-gray-800 hover:bg-gray-400 hover:${DesignSystem.textPrimary}`,
                    'flex items-center rounded-md px-3 py-2 text-sm font-medium',
                )}
                >
                    <>
                        <item.icon className="h-5 w-5 mr-2" aria-hidden="true" />
                        <span>{item.name}</span>
                    </>
                </Link>
            ))}
            </div>
        </div>
    );
}

const NavMobileItems: React.FC<NavigationItemProps> = ({ navigation }) => {
    const currentPath = usePathname();
    console.log(currentPath)
    return (
        <DisclosurePanel className="sm:hidden">
            <div className="space-y-1 px-2 pt-2 pb-3">
            {navItems.map((item) => (
                <DisclosureButton
                key={item.name}
                as="a"
                href={item.href}
                // aria-current={item.current ? 'page' : undefined}
                className={classNames(
                    item.href.startsWith(currentPath) ? `bg-gray-900 ${DesignSystem.textPrimary}` : `text-gray-800 hover:bg-gray-400 hover:${DesignSystem.textPrimary}`,
                    'flex items-center block rounded-md px-3 py-2 font-medium',
                )}
                >
                    <>
                        <item.icon className="h-5 w-5 mr-2" aria-hidden="true" />
                        <span>{item.name}</span>
                    </>
                </DisclosureButton>
            ))}
            </div>
        </DisclosurePanel>
    );
}

const Nav: React.FC = () => {
    return (
        <Disclosure as="nav" className="bg-slate-50">
        <div className="mx-auto max-w-7xl px-2 sm:px-6 lg:px-8">
          <div className="relative flex h-16 items-center justify-between">
            <div className="absolute inset-y-0 left-0 flex items-center sm:hidden">
              {/* Mobile menu button*/}
              <DisclosureButton className="group relative inline-flex items-center justify-center rounded-md p-2 text-gray-400 hover:bg-emerald-500 hover:text-white focus:ring-2 focus:ring-white focus:outline-hidden focus:ring-inset">
                <span className="absolute -inset-0.5" />
                <span className="sr-only">Open main menu</span>
                <Bars3Icon aria-hidden="true" className="block size-6 group-data-open:hidden" />
                <XMarkIcon aria-hidden="true" className="hidden size-6 group-data-open:block" />
              </DisclosureButton>
            </div>
            <div className="flex flex-1 items-center justify-center sm:items-stretch sm:justify-start">
                <NavIcon/>
                <NavItems navigation={navItems} />

            </div>
            <div className="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                <button
                    type="button"
                    className={`relative rounded-full bg-gray-200 p-1 text-gray-800 hover:${DesignSystem.textPrimary} focus:ring-2 focus:${DesignSystem.ringPrimary} focus:ring-offset-2 focus:ring-offset-gray-800 focus:outline-hidden`}
                >
                    <span className="absolute -inset-1.5" />
                    <span className="sr-only">View notifications</span>
                    <BellIcon aria-hidden="true" className="size-6" />
                </button>
  
              {/* Profile dropdown */}
                <NavOptions className='ml-2'>
                    <img
                    alt=""
                    src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                    className="size-8 rounded-full"
                    />
                </NavOptions>
            </div>
          </div>
        </div>
  
        <NavMobileItems navigation={navItems} />
      </Disclosure>
    );
}

export default Nav;