const articles = Array.from({ length: 50 }, (_, i) => ({
    id: i + 1,
    title: `Notícia ${i + 1}`,
    content: `Este é o conteúdo da notícia ${i + 1}. Lorem ipsum dolor sit amet, consectetur adipiscing elit.`,
}));

const getNews = () => {
    return new Promise((resolve) => {
        setTimeout(() => {
            resolve(articles);
        }, 1000);
    });
};

const getArticleById = (id: string) => {
    return articles.find(article => article.id === Number(id)) || null;
};

export default {
    getNews,
    getArticleById,
};

