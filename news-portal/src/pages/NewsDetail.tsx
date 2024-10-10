import React from 'react';
import { useParams } from 'react-router-dom';
import newsService from '../services/newsService';

const NewsDetail: React.FC = () => {
    const { id } = useParams<{ id: string }>();
    const article = newsService.getArticleById(id);

    return (
        <div>
            <h1>{article.title}</h1>
            <p>{article.content}</p>
        </div>
    );
};

export default NewsDetail;
