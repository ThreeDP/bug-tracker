'use client'

import { createContext, useContext, useState, ReactNode } from "react";

interface RootContextType {
  component: ReactNode;
  setComponent: (component: ReactNode) => void;
}

const RootContext = createContext<RootContextType | undefined>(undefined);

export function RootProvider({ children }: { children: ReactNode }) {
  const [ component, setComponent ] = useState<ReactNode>(DefaultHeaderComponent);
  
  return (
    <RootContext.Provider value={{component, setComponent }}>
      {children}
    </RootContext.Provider>
  );
}

export function useRootLayoutContext() {
  const context = useContext(RootContext);
  if (!context) {
    throw new Error("deu erro aqui em mano doido");
  }
  return context;
}

function DefaultHeaderComponent() {
  return (
    <div className="flex justify-between animate-pulse">
      <div>
        <div className="w-50 h-6 bg-slate-200 rounded-full mb-2" />
      </div>
      <div className="flex justify-around">
        <div className="w-35 h-6 bg-slate-200 rounded-full mb-2" />
        <div className="w-35 h-6 bg-slate-200 ml-2 rounded-full mb-2" />
        <div className="w-35 h-6 bg-slate-200 ml-2 rounded-full mb-2" />
      </div>
    </div>
  );
}
