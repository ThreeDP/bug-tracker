import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";

export const geistSans = Geist({
    variable: "--font-geist-sans",
    subsets: ["latin"],
});

export const geistMono = Geist_Mono({
    variable: "--font-geist-mono",
    subsets: ["latin"],
});

export const metadata: Metadata = {
    title: "Bug Tracker",
    description: "I solution for our tech problems.",
};
