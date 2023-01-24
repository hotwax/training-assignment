import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class App {

    static int countOccurrences(String str, String word) {
        // split the string by spaces in a
        String a[] = str.split(" ");
        // search for pattern in a
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            // if match found increase count
            if (word.equals(a[i]))
                count++;
        }
        return count;
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> lists = new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        // Sort the lists
        Collections.sort(lists, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        Map<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : lists) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    // method to sort hashmap by values

    public static void main(String[] args) throws Exception {

        // map to store the word count
        Map<String, Integer> wordCount = new TreeMap<String, Integer>();

        // current working directory patt
        String dir = System.getProperty("user.dir");

        // get url and words files
        File urls = new File(dir + "/Word-Count-Scraper/Scraper/src/urls.txt");
        File words = new File(dir + "/Word-Count-Scraper/Scraper/src/words.txt");
        String key;

        // read words file and add to wordCount map
        BufferedReader brwords = new BufferedReader(new FileReader(words));
        while ((key = brwords.readLine()) != null) {
            wordCount.put(key, 0);
        }

        // read urls file and scrape each url
        BufferedReader brurls = new BufferedReader(new FileReader(urls));
        String st;
        while ((st = brurls.readLine()) != null) {

            // scrape url
            Document doc = Jsoup.connect(st).get();

            // get text from body
            String text = doc.body().text();

            // count occurrences of each word in text
            for (String word : wordCount.keySet()) {
                wordCount.put(word, wordCount.get(word) + countOccurrences(text, word));
            }
        }

        // sort map by value
        wordCount = sortByValue(wordCount);
        System.out.println(wordCount);
    }

}
