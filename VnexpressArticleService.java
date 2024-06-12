import java.util.ArrayList;

public class VnexpressArticleService implements ArticleService {
    @Override
    public ArrayList<String> getLinks(String url) {
        return new ArrayList<>();
    }

    @Override
    public Article getArticle(String url) {
        return new Article();
    }
}
