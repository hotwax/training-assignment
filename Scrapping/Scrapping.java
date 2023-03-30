package Scrapping;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Scrapping {

   // List of words prsent in words.txt
   private ArrayList<String> Words;
   private HashMap<String, Integer> allWordWithCount;
   private ArrayList<LinkedHashMap<String, LinkedHashMap<String, Integer>>> AllUrlWordsWithCount;

   Scrapping(String wordsfile) {
      allWordWithCount = new HashMap<String, Integer>();
      Words = new ArrayList<>();
      AllUrlWordsWithCount = new ArrayList<LinkedHashMap<String, LinkedHashMap<String, Integer>>>();
      File wordFile = new File(wordsfile);
      try (Scanner Reader = new Scanner(wordFile)) {
         while (Reader.hasNextLine()) {
            String word = Reader.nextLine();
            String splitWord[] = word.split(" ");
            for (String singleword : splitWord) {
               Words.add(singleword);
            }

         }
      } catch (FileNotFoundException exception) {
         System.out.println(exception);
      }

   }

   public HashMap<String, Integer> getAllWordsWithCount() {
      return this.allWordWithCount;
   }

   public PriorityQueue<Map.Entry<String, Integer>> sortOrderByCount(HashMap<String, Integer> map) {
      PriorityQueue<Map.Entry<String, Integer>> sortedWords = new PriorityQueue<>(valueComparator());
      sortedWords.addAll(map.entrySet());
      return sortedWords;
   }

   // Comaparator is used to compare the values in Treeset
   public Comparator<Map.Entry<String, Integer>> valueComparator() {
      return (e1, e2) -> e2.getValue().compareTo(e1.getValue());
   }

   // scrap the Data From Url and print top the word of words.txt file
   // which prsent in Url data with Count
   private String[] scrappingword(String URL) throws IOException {
      // Connecting to the web page

      Connection conn = Jsoup.connect(URL);
      // executing the get request
      Document doc = conn.get();
      // Retrieving the contents (body) of the web page
      String result = doc.body().text();

      result = result.replaceAll("[^a-zA-Z' ']", "");
      String[] WordsInUrl = result.split(" ");
      return WordsInUrl;
   }

   ArrayList<LinkedHashMap<String, LinkedHashMap<String, Integer>>> CheckWordsCount(String UrlFile)
         throws FileNotFoundException {
      File file = new File(UrlFile);
      try (Scanner inputUrl = new Scanner(file)) {
         ArrayList<String> urlList = new ArrayList<>();

         while (inputUrl.hasNextLine()) {
            urlList.add(inputUrl.next());
         }
         if (urlList.isEmpty()) {
            System.out.println("url.txt file is empty");
            return null;
         }
         if (Words.isEmpty()) {
            System.out.println("words.txt file is empty");
            return null;
         }
         for (int index = 0; index < urlList.size(); index++) {
            String URL = urlList.get(index);
            HashMap<String, Integer> hm = new HashMap<String, Integer>();
            // insertion of words in HashMap which you want to Check
            for (String word : Words) {
               hm.put(word, 0);
            }

            String[] WordsInUrl = scrappingword(URL);
            for (String word : WordsInUrl) {
               if (word.equals("")) {
                  continue;
               }
               if (hm.containsKey(word)) {
                  hm.put(word, hm.get(word) + 1);
               }
            }
            // Extract the top three words with count in Descending Order
            PriorityQueue<Map.Entry<String, Integer>> sortedSet = sortOrderByCount(hm);
            int count = 0;
            LinkedHashMap<String, Integer> wordsCount = new LinkedHashMap<String, Integer>();
            for (Map.Entry<String, Integer> map : sortedSet) {
               if (count < 3) {

                  wordsCount.put(map.getKey(), map.getValue());
                  count++;
               }
               if (allWordWithCount.containsKey(map.getKey())) {
                  allWordWithCount.put(map.getKey(), allWordWithCount.get(map.getKey()) + map.getValue());
               } else {
                  allWordWithCount.put(map.getKey(), map.getValue());
               }

            }
            LinkedHashMap<String, LinkedHashMap<String, Integer>> h = new LinkedHashMap<>();
            h.put(URL, wordsCount);
            AllUrlWordsWithCount.add(h);
         }
      } catch (IOException exception) {
         System.out.println(exception);
      }

      return AllUrlWordsWithCount;

   }

   public static void main(String args[]) throws FileNotFoundException {
      Scrapping scrapping = new Scrapping("Scrapping/Words.txt");

      ArrayList<LinkedHashMap<String, LinkedHashMap<String, Integer>>> AllUrlWordsWithCount = scrapping
            .CheckWordsCount("Scrapping/Url.txt");
      if (AllUrlWordsWithCount != null) {
         for (LinkedHashMap<String, LinkedHashMap<String, Integer>> AllUrl : AllUrlWordsWithCount) {
            for (Map.Entry<String, LinkedHashMap<String, Integer>> UrlWordsWithCount : AllUrl.entrySet()) {
               System.out.println(UrlWordsWithCount.getKey());
               for (Map.Entry<String, Integer> WordWithCount : UrlWordsWithCount.getValue().entrySet()) {
                  if (WordWithCount.getValue() == 0) {
                     System.out.println(WordWithCount.getKey() + " words not present");
                     System.out.println();
                     break;
                  } else {
                     System.out.println(WordWithCount.getKey() + "->" + WordWithCount.getValue());
                     System.out.println();
                  }
               }
               System.out.println("=======================================================\n");

            }
         }
      }

      // Added the all the word in to treeset with count
      // Print the all in sorted order(Descending)
      PriorityQueue<Map.Entry<String, Integer>> sortedWords = scrapping
            .sortOrderByCount(scrapping.getAllWordsWithCount());
      if (!sortedWords.isEmpty()) {
         System.out.println("========");
         // Print all the wors with count in sorted order
         for (Map.Entry<String, Integer> word : sortedWords) {
            if (word.getValue() == 0) {
               System.out.println(word.getKey() + " is not present");
            } else {
               System.out.println(word.getKey() + " ->" + word.getValue());
            }
         }
      }
   }
}
