import java.util.ArrayList;
import java.util.Scanner;

public class Application {
    private static ArticleRepository articleRepository = new MySqlArticleRepository();
    private static ArticleService vnexpressService = new VnexpressArticleService();
    private static ArticleService myService = new MyArticleService();

    public static void main(String[] args) {
        generateMenu();
    }

    private static void generateMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Crawl thông tin từ vnexpress.");
            System.out.println("2. Crawl thông tin từ nguồn của tôi.");
            System.out.println("3. Hiển thị danh sách tin hiện có.");
            System.out.println("4. Thoát chương trình.");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    crawlArticles(vnexpressService, "https://vnexpress.net/");
                    break;
                case 2:
                    crawlArticles(myService, "https://vnexpress.net/");
                    break;
                case 3:
                    displayArticles();
                    break;
                case 4:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    private static void crawlArticles(ArticleService service, String baseUrl) {
        ArrayList<String> links = service.getLinks(baseUrl);
        for (String link : links) {
            Article article = service.getArticle(link);
            articleRepository.save(article);
        }
        System.out.println("Crawl thông tin thành công.");
    }

    private static void displayArticles() {
        ArrayList<Article> articles = articleRepository.findAll();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            for (Article article : articles) {
                System.out.println(article.getId() + " - " + article.getTitle());
            }

            System.out.println("Nhập mã tin cần xem chi tiết hoặc 'exit' để thoát:");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            try {
                int articleId = Integer.parseInt(input);
                Article article = articles.stream().filter(a -> a.getId() == articleId).findFirst().orElse(null);
                if (article != null) {
                    System.out.println(article);
                } else {
                    System.out.println("Mã tin không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số hợp lệ.");
            }
        }
    }
}
