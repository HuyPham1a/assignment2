import java.sql.*;
import java.util.ArrayList;

public class MySqlArticleRepository implements ArticleRepository {
    private Connection connection;

    public MySqlArticleRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/huyhuy", "", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Article> findAll() {
        ArrayList<Article> articles = new ArrayList<>();
        return articles;
    }

    @Override
    public Article findByUrl(String url) {
        return new Article();
    }

    @Override
    public Article save(Article article) {
        return article;
    }

    @Override
    public Article update(Article article) {
        return article;
    }

    @Override
    public void deleteByUrl(String url) {
    }
}
