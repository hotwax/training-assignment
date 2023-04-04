import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.net.UnknownHostException;


class WebContent {

   // Function to fetch the content of a given URL and return its text content
  String parseUrl(String url) {
    String content = "";
    try {
      Document document = Jsoup.connect(url).get(); 
      content = document.body().text(); 
      content = content.replaceAll("[^a-zA-Z\\w]", " "); 
    } catch (UnknownHostException e) {
      System.out.println("Please add a valid url");
      System.out.println(e);
    } catch (IOException e) {
      System.out.println(e);
    }
    return content;
  }

  // Function to count the number of occurrences of a given word in a given text
  int countWordOccurence(String word, String urlContent) {
    int wordCount = 0;
    String contentList[] = urlContent.split(" "); 
    for (String str : contentList) { 
      if (str.equalsIgnoreCase(word)) { 
        wordCount++; 
      }
    }
    return wordCount;
  }

   // Function to sort a given hashmap by its values in descending order and return the sorted map
  public static Map<String, Integer> sortByValue(Map<String, Integer> hashmap) {
    
    List<Map.Entry<String, Integer>> lists = new LinkedList<Map.Entry<String, Integer>>(
        hashmap.entrySet());

    Collections.sort(
        lists,
        new Comparator<Map.Entry<String, Integer>>() {
          public int compare(
              Map.Entry<String, Integer> o1,
              Map.Entry<String, Integer> o2) {
            return (o2.getValue()).compareTo(o1.getValue());
          }
        });

    Map<String, Integer> maplist= new LinkedHashMap<String, Integer>();
    for (Map.Entry<String, Integer> entryy : lists) {
      maplist.put(entryy.getKey(), entryy.getValue());
    }
    return maplist;
  }

}

// main Class

public class Main {

  public static void main(String[] args) {
    try {
      // Fetch URLS from the text file => url.txt

      HashSet<String> urlsSet= new HashSet<>();
      Scanner Reader = new Scanner(new File("url.txt"));
      while (Reader.hasNext()) {
        urlsSet.add(Reader.next());
      }
      ArrayList<String> urls = new ArrayList<>(urlsSet);
      if(urls.size()==0)
      {
        System.out.println("No Url Found");
        return ;
      }

      // Fetch Words from the text file => words.txt

      HashSet<String> wordsSet= new HashSet<>();
      Reader = new Scanner(new File("words.txt"));
      while (Reader.hasNext()) {
        wordsSet.add(Reader.next());
      }
      ArrayList<String> words = new ArrayList<>(wordsSet);
      if(words.size()==0)
      {
        System.out.println("No Words Found");
        return ;
      }
      //Found words in URL

      WebContent webContentObj = new WebContent(); 
      Map<String, Integer> AllwordsFrequencyMap = new TreeMap<String, Integer>(); 
      System.out.println("===========================================");
      for (String url : urls) {
        Map<String, Integer> wordFrequencyMap = new TreeMap<String, Integer>(); 
        System.out.println(url); 
        System.out.println();

        for (String word : words) {
          String urlContent = webContentObj.parseUrl(url); 
          int wordOccurence = webContentObj.countWordOccurence(word, urlContent); 
          wordFrequencyMap.put(word, wordOccurence); 
        }
        wordFrequencyMap = WebContent.sortByValue(wordFrequencyMap); 
        int counter = 0;
        for (Map.Entry m : wordFrequencyMap.entrySet()) 
        {
          if (counter++ < 3) {
            System.out.println(m.getKey() + " " + m.getValue());
            String key = (String) m.getKey();
            int value = (int) m.getValue();
            if (AllwordsFrequencyMap.get(key) != null) {
              value += AllwordsFrequencyMap.get(key); 
            }
            AllwordsFrequencyMap.put(key, value);
          } else
            break;
        }
        System.out.println();
      }
      System.out.println("===========================================");
      AllwordsFrequencyMap = WebContent.sortByValue(AllwordsFrequencyMap);
      for (Map.Entry m : AllwordsFrequencyMap.entrySet()) {
        System.out.println(m.getKey() + " " + m.getValue());
      }
      System.out.println("===========================================");
    } catch (FileNotFoundException e) {
      System.out.println("File Not Found");
    }
  }
}