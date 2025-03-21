import { Popover, PopoverButton, PopoverPanel } from '@headlessui/react';
import { ChevronDownIcon, PhoneIcon, PlayCircleIcon } from '@heroicons/react/20/solid';
import {
  ArrowPathIcon,
  ChartPieIcon,
  CursorArrowRaysIcon,
  FingerPrintIcon,
  SquaresPlusIcon,
} from '@heroicons/react/24/outline';
import React from 'react';

const solutions = [
  { name: 'Perfil', description: 'Config or perfil', href: '/perfil/', icon: ChartPieIcon },
  { name: 'Logout', description: "exit system", href: '#', icon: FingerPrintIcon }
];

interface NavOptionsProps {
  children?: React.ReactNode;
  className?: string,
}

const NavOptions: React.FC<NavOptionsProps> = ({ children, className }) => {
  return (
    <Popover className={`relative bg-emerald-400 rounded-xl px-2 py-1 flex ${className}`}>
      <PopoverButton className="inline-flex items-center gap-x-1 text-sm font-semibold text-white-50">
        {children}
        <ChevronDownIcon aria-hidden="true" className="h-5 w-5" />
      </PopoverButton>

      <PopoverPanel
        transition
        className="absolute right-0 z-10 mt-10 mx-0 flex w-screen max-w-max transition data-closed:translate-y-1 data-closed:opacity-0 data-enter:duration-200 data-enter:ease-out data-leave:duration-150 data-leave:ease-in"
      >
        <div className="w-screen max-w-sm flex-auto overflow-hidden rounded-3xl bg-white text-sm ring-1 shadow-lg ring-gray-900/5">
          <div className="p-4">
            {solutions.map((item) => (
              <div key={item.name} className="group relative flex gap-x-6 rounded-lg p-4 hover:bg-gray-50">
                <div className="mt-1 flex h-11 w-11 flex-none items-center justify-center rounded-lg bg-gray-50 group-hover:bg-white">
                  <item.icon aria-hidden="true" className="h-6 w-6 text-gray-600 group-hover:text-emerald-400" />
                </div>
                <div className='text-left'>
                  <a href={item.href} className="font-semibold text-gray-900 group-hover:text-emerald-400">
                    {item.name}
                    <span className="absolute inset-0" />
                  </a>
                  <p className="mt-1 text-gray-600">{item.description}</p>
                </div>
              </div>
            ))}
          </div>
        </div>
      </PopoverPanel>
    </Popover>
  );
};

export default NavOptions;