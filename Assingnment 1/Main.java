import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;




class WebContent {

  // getting the text from website using the url
  public static String parseUrl(String urlString) throws IOException {
    URL url = new URL(urlString);
    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
        sb.append(line);
        sb.append("\n");
    }
    reader.close();
    return sb.toString();
}

  // Function to count the frequency of the given word
  int countWordOccurence(String word, String urlContent) {
    int wordCount = 0;
    // Using the .split method to remove space and store the content into array of string
    String contentList[] = urlContent.split(" "); 
    for (String str : contentList) { 
      // find the word in the text
      if (str.equalsIgnoreCase(word)) {
        // if the word is found then increment the counter for words
        wordCount++; 
      }
    }
    //return the number of occurences of the word
    return wordCount;
  }

  // Sort the Map by Value
  public static Map<String, Integer> sortByValue(Map<String, Integer> hm) {
    // Create a list from elements of HashMap
    List<Map.Entry<String, Integer>> lists = new LinkedList<Map.Entry<String, Integer>>(
        hm.entrySet());

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
    Map<String, Integer> temp = new LinkedHashMap<String, Integer>();
    for (Map.Entry<String, Integer> aa : lists) {
      temp.put(aa.getKey(), aa.getValue());
    }
    return temp;
  }

}

// Driver / main Class

public class Main {

  public static void main(String[] args) throws IOException {
    try {
      // Get URLS from the text file

      ArrayList<String> urls = new ArrayList<>();
      Scanner Reader = new Scanner(new File("url.txt"));
      while (Reader.hasNext()) {
        urls.add(Reader.next());
      }

      // Get words from the text file

      ArrayList<String> words = new ArrayList<>();
      Reader = new Scanner(new File("words.txt"));
      while (Reader.hasNext()) {
        words.add(Reader.next());
      }

      // Find words in URL
        
      // Creating object of Class WebContent
      WebContent webContentObj = new WebContent(); 
      
      // Map to store the word as key and its frequency as value
      Map<String, Integer> AllwordsFrequencyMap = new TreeMap<String, Integer>(); 
      
      System.out.println("===========================================");
      for (String url : urls) {
        // Storing words for each url
        Map<String, Integer> wordFrequencyMap = new TreeMap<String, Integer>(); 
        
        System.out.println(url);
        System.out.println();
        // Calling the pass url method
        String urlContent = webContentObj.parseUrl(url); 

        for (String word : words) {
          // Getting the frequency of the word in the url
          int wordOccurence = webContentObj.countWordOccurence(word, urlContent); 
          
          // Adding the word as key and frequency as value
          wordFrequencyMap.put(word, wordOccurence); 
        }
        // Sort map in non-ascending order
        wordFrequencyMap = WebContent.sortByValue(wordFrequencyMap);
        int counter = 0;
        // Printing the top three words that occur the most
        for (Map.Entry m : wordFrequencyMap.entrySet()) 
        {
          if (counter++ < 3) {
            System.out.println(m.getKey() + " " + m.getValue());
            String key = (String) m.getKey();
            int value = (int) m.getValue();
            if (AllwordsFrequencyMap.get(key) != null) {
              // Adding the frequency of words that occur in multiple urls
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
      System.out.println(e);
    }
  }
}