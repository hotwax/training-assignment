import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App {
    public static void main(String[] args) throws Exception {

        Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
        String text = doc.body().text();
        System.out.println(text);
        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            System.out.println(headline.absUrl("href"));
        }
    }
}
