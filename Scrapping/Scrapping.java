package Scrapping;

import java.io.IOException;
import java.io.File;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

class Scrap {

   // List of words prsent in words.txt
   private ArrayList<String> Words;
   HashMap<String, Integer> hash;

   Scrap(ArrayList<String> words) {
      hash = new HashMap<String, Integer>();
      Words = new ArrayList<>();
      Words = words;
   }

   // Comaparator is used to compare the values in Treeset
   public Comparator<Map.Entry<String, Integer>> valueComparator() {
      return (e1, e2) -> e2.getValue().compareTo(e1.getValue());
   }

   // scrap the Data From Url and print top the word of words.txt file
   // which prsent in Url data with Count
   void scrapData(String URL) throws IOException {
      HashMap<String, Integer> hm = new HashMap<String, Integer>();
      // insertion of words in HashMap which you want to Check
      for (String word : Words) {
         hm.put(word, 0);
      }

      // Connecting to the web page
      Connection conn = Jsoup.connect(URL);
      // executing the get request
      Document doc = conn.get();
      // Retrieving the contents (body) of the web page
      String result = doc.body().text();
      result = result.replaceAll("[^a-zA-Z' ']", "");
      String[] WordsInUrl = result.split(" ");
      for (String word : WordsInUrl) {
         if (word.equals("")) {
            continue;
         }
         if (hm.containsKey(word)) {
            hm.put(word, hm.get(word) + 1);
         }
      }

      TreeSet<Map.Entry<String, Integer>> set = new TreeSet<>(valueComparator());
      set.addAll(hm.entrySet());
      int count = 0;
      System.out.println(URL);
      for (Map.Entry<String, Integer> map : set) {
         if (count < 3) {
            System.out.println(map.getKey() + "->" + map.getValue());
            count++;
         }
         if (hash.containsKey(map.getKey())) {
            hash.put(map.getKey(), hash.get(map.getKey()) + map.getValue());
         } else {
            hash.put(map.getKey(), map.getValue());
         }

      }

   }

}

public class Scrapping {

   public static void main(String args[]) throws IOException {
      ArrayList<String> words = new ArrayList<>();
      // Read the words from File
      File wordFile = new File("Scrapping/Words.txt");
      try (Scanner Reader = new Scanner(wordFile)) {
         while (Reader.hasNextLine()) {
            String word = Reader.nextLine();
            String splitWord[] = word.split(" ");
            for (String singleword : splitWord) {
               words.add(singleword);
            }

         }
      }

      Scrap scrap = new Scrap(words);

      // Scrap the data from websites
      File file = new File("Scrapping/Url.txt");
      try (Scanner inputUrl = new Scanner(file)) {
         ArrayList<String> urlList = new ArrayList<>();

         while (inputUrl.hasNextLine()) {
            urlList.add(inputUrl.next());
         }

         System.out.println("========");
         System.out.println();
         for (int index = 0; index < urlList.size(); index++) {
            String URL = urlList.get(index);
            scrap.scrapData(URL);
            System.out.println();
         }
         System.out.println("==============================");

      } catch (Exception e) {
         System.out.println(e);
      }

      // Added the all the word in to treeset with count
      // Print the all in sorted order(Descending)
      TreeSet<Map.Entry<String, Integer>> sortedWords = new TreeSet<>(scrap.valueComparator());
      sortedWords.addAll(scrap.hash.entrySet());
      System.out.println("========");

      // Print all the wors with count in sorted order
      for (Map.Entry<String, Integer> word : sortedWords) {
         System.out.println(word.getKey() + " ->" + word.getValue());
      }

   }
}
