'use client'

import { RootProvider, useRootLayoutContext } from "@/_components/rootContext";
import "./globals.css";
import Nav from '@/_components/nav';
import { geistMono, geistSans, metadata } from "@/_components/data";
import { ReactNode } from "react";

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {

  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        <Nav/>
        <RootProvider>
          <MainLayout>{children}</MainLayout>
        </RootProvider>
      </body>
    </html>
  );
}

function MainLayout({ children }: { children: ReactNode }) {
  const { component } = useRootLayoutContext();

  return (
    <>
      <header className="bg-white shadow-sm">
          <div className="mx-auto max-w-7xl px-4 py-6 sm:px-6 lg:px-8">
              {component}
          </div>
      </header>
      <main>
          <div className="mx-auto max-w-7xl px-4 py-6 sm:px-6 lg:px-8">
              {children}
          </div>
      </main>
    </>
  );
}