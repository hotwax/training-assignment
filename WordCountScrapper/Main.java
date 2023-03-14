import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



// to run this program you have to set Path for jsoup file 

// set classpath=%classpath%;D:\Programs\jsoup-1.15.3.jar;
class WebContent {

  // parse the website using URL
  String parseUrl(String url) {
    String content = "";
    try {
      Document document = Jsoup.connect(url).get(); // Get the whole HTML document
      content = document.body().text(); // get the text part of the html document
      content = content.replaceAll("[^a-zA-Z\\w]", " "); // Regex to get only text without any symbol or special
                                                         // character
    } catch (IOException e) {
      System.out.println(e);
    }
    return content; // return text
  }

  // Method to give frequecy of the word
  int countWordOccurence(String word, String urlContent) {
    int wordCount = 0;
    String contentList[] = urlContent.split(" "); // spilt the whole text by space and store it into string array
    for (String str : contentList) { // find the word in the text
      if (str.equalsIgnoreCase(word)) { // Case not matter
        wordCount++; // if word found count increase
      }
    }
    return wordCount;
  }

  // Sort the Map by Value
  public static Map<String, Integer> sortByValue(Map<String, Integer> hashmap) {
    // Create a list from elements of HashMap
    List<Map.Entry<String, Integer>> lists = new LinkedList<Map.Entry<String, Integer>>(
        hashmap.entrySet());

    // Sort the lists
    Collections.sort(
        lists,
        new Comparator<Map.Entry<String, Integer>>() {
          public int compare(
              Map.Entry<String, Integer> o1,
              Map.Entry<String, Integer> o2) {
            return (o2.getValue()).compareTo(o1.getValue());
          }
        });

    // put data from sorted list to hashmap
    Map<String, Integer> maplist= new LinkedHashMap<String, Integer>();
    for (Map.Entry<String, Integer> entryy : lists) {
      maplist.put(entryy.getKey(), entryy.getValue());
    }
    return maplist;
  }

}

// Driver / main Class

public class Main {

  public static void main(String[] args) {
    try {
      // Step 1 :- Fetch URLS from the text file => url.txt

      ArrayList<String> urls = new ArrayList<>();
      Scanner Reader = new Scanner(new File("url.txt"));
      while (Reader.hasNext()) {
        urls.add(Reader.next());
      }
      if(urls.size()==0)
      {
        System.out.println("No Url Found");
        return ;
      }

      // step 2 : - Fetch Words from the text file => words.txt

      ArrayList<String> words = new ArrayList<>();
      Reader = new Scanner(new File("words.txt"));
      while (Reader.hasNext()) {
        words.add(Reader.next());
      }
      if(words.size()==0)
      {
        System.out.println("No Words Found");
        return ;
      }
      // Step 3 : - Found words in URL

      WebContent webContentObj = new WebContent(); // Create Object of WebContent Class

      Map<String, Integer> AllwordsFrequencyMap = new TreeMap<String, Integer>(); // Map to Store Word And their
                                                                                  // Frequency
      System.out.println("===========================================");
      for (String url : urls) {
        Map<String, Integer> wordFrequencyMap = new TreeMap<String, Integer>(); // URL wise store Word and their
                                                                                // frquency
        System.out.println(url); // Fetch url one by one for list of urls
        System.out.println();

        for (String word : words) {
          String urlContent = webContentObj.parseUrl(url); // Parse all the conent of the Url in String
          int wordOccurence = webContentObj.countWordOccurence(word, urlContent); // Find the Occurence of the word in
                                                                                  // URL
          wordFrequencyMap.put(word, wordOccurence); // put word and its frequency in map
        }
        wordFrequencyMap = WebContent.sortByValue(wordFrequencyMap); // Sort Map by frequency of the words in
                                                                     // desecending order
        int counter = 0;
        for (Map.Entry m : wordFrequencyMap.entrySet()) // Print top 3 words with highest frequency with the help of
                                                        // counter
        {
          if (counter++ < 3) {
            System.out.println(m.getKey() + " " + m.getValue());
            String key = (String) m.getKey();
            int value = (int) m.getValue();
            if (AllwordsFrequencyMap.get(key) != null) {
              value += AllwordsFrequencyMap.get(key); // Add the frequency of the words which appears in the multiple
                                                      // URLs
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
