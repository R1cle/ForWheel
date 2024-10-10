// src/types/news.ts
export interface Comment {
    name: string;
    text: string;
}

export interface NewsArticle {
    id: number;
    title: string;
    date: string;
    content: string;
    image: string;
    categories: string[];
    comments: Comment[];
}
