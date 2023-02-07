/*
Jsoup is a Java library for parsing HTML and XML documents. 
It provides a simple and convenient API for extracting data from HTML and XML documents, 
and can also be used to manipulate and clean up HTML. 
Jsoup can be used for a variety of purposes, such as web scraping, data extraction, and HTML parsing.
 */

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

public class WebScrapper {

    static int countOfWords(String str, String word) {
        // split the string by spaces.
        String temporary[] = str.split(" ");
        // search for a pattern and increment the count. 
        int count = 0;
        for (int i = 0; i < temporary.length; i++) {
            // if match found increase count
            if (word.equals(temporary[i]))
                count++;
        }
        return count;
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> mapCntainer) {
        // Create a list from elements of HasmapCntainerap
        List<Map.Entry<String, Integer>> lists = new LinkedList<Map.Entry<String, Integer>>(mapCntainer.entrySet());

        // Sort the lists
        Collections.sort(lists, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // putting the data into the hasmapCntainerap.
        Map<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> list : lists) {
            temp.put(list.getKey(), list.getValue());
        }
        return temp;
    }

    public static void main(String[] args) throws Exception {

        // map to store the word count
        Map<String, Integer> wordCount = new TreeMap<String, Integer>();

        // current working directory patt
        String dir = System.getProperty("user.dir");

        // get url and words files
        File urls = new File(dir + "/Program1/src/urls.txt");
        File words = new File(dir + "/Program1/src/words.txt");
        String key;

        // read words file and add to wordCount map
        BufferedReader brwords = new BufferedReader(new FileReader(words));
        while ((key = brwords.readLine()) != null) {
            wordCount.put(key, 0);
        }

        // read urls file and scrape each url
        BufferedReader urlContainer = new BufferedReader(new FileReader(urls));
        String st;
        while ((st = urlContainer.readLine()) != null) {

            // scrape url
            Document doc = Jsoup.connect(st).get();

            // get text from body
            String text = doc.body().text();

            // count occurrences of each word in text
            for (String word : wordCount.keySet()) {
                wordCount.put(word, wordCount.get(word) + countOfWords(text, word));
            }
        }

        // sort map by value
        wordCount = sortByValue(wordCount);
//        System.out.println(wordCount);
        for(Map.Entry<String, Integer> value : wordCount.entrySet()) {
            System.out.println(value);
        }
    }

}