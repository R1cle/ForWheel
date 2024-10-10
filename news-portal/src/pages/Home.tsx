import React, { useEffect, useState } from 'react';
import newsService from '../services/newsService';

const Home: React.FC = () => {
    const [articles, setArticles] = useState<any[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState('');

    useEffect(() => {
        newsService.getNews()
            .then(data => {
                setArticles(data);
                setLoading(false);
            })
            .catch(err => {
                setError('Nenhum artigo encontrado');
                setLoading(false);
            });
    }, []);

    if (loading) return <div>Carregando...</div>;
    if (error) return <div>{error}</div>;

    return (
        <div>
            <h1>Últimas Notícias</h1>
            <ul>
                {articles.map(article => (
                    <li key={article.id}>
                        <a href={`/news/${article.id}`}>
                            <h2>{article.title}</h2>
                            <p>{article.content.slice(0, 50)}...</p>
                        </a>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Home;
