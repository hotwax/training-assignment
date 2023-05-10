package Scrapping;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Scrapping {
   private static HashMap<String, Integer> allWordsCount = new HashMap<String, Integer>();;
   private static ArrayList<LinkedHashMap<String, LinkedHashMap<String, Integer>>> allUrlsWordsCount = new ArrayList<LinkedHashMap<String, LinkedHashMap<String, Integer>>>();;
   private static File currDir = new File("Scrapping");;

   // get all the words with count
   public HashMap<String, Integer> getAllWordsCount() {
      return allWordsCount;
   }

   // get sorted words by count in descending order
   public PriorityQueue<Map.Entry<String, Integer>> sortOrderByCount(HashMap<String, Integer> map) {
      PriorityQueue<Map.Entry<String, Integer>> sortedWords = new PriorityQueue<>(valueComparator());
      sortedWords.addAll(map.entrySet());
      return sortedWords;
   }

   // Comaparator is used to compare the values in Priority queue
   public Comparator<Map.Entry<String, Integer>> valueComparator() {
      return (firstValue, secondValue) -> secondValue.getValue() - firstValue.getValue();
   }

   // scrap the Data From Url and print top the word of words.txt file
   // which prsent in Url data with Count
   private List<String> scrappingWords(String url) {
      List<String> wordsInWebPage = new ArrayList<>();
      // Connecting to the web page
      try {
         Connection conn = Jsoup.connect(url);
         // executing the get request
         Document doc = conn.get();
         // Retrieving the contents (body) of the web page
         String result = doc.body().text();
         // replace all spaces to single space
         result = result.replaceAll("[^a-zA-Z' ']", "");
         // split the words by single space
         String[] wordsInUrl = result.split(" ");
         wordsInWebPage = Arrays.asList(wordsInUrl);
      } catch (IOException exception) {
         System.out.println(exception);
         return null;
      } catch (Exception exception) {
         System.out.println(exception);
         return null;
      }
      return wordsInWebPage;
   }

   // fetch words counts of each url
   ArrayList<LinkedHashMap<String, LinkedHashMap<String, Integer>>> fetchWordsCount(HashSet<String> urlList,
         HashSet<String> wordsList) {
      for (String url : urlList) {
         HashMap<String, Integer> urlWordsCounts = new HashMap<String, Integer>();
         // insertion of words in HashMap which you want to Check
         for (String word : wordsList) {
            urlWordsCounts.put(word, 0);
         }
         List<String> wordsInUrl = scrappingWords(url);
         if (wordsInUrl == null) {
            continue;
         }
         for (String word : wordsInUrl) {
            if (word.equals("")) {
               continue;
            }
            if (urlWordsCounts.containsKey(word.toLowerCase())) {
               urlWordsCounts.put(word.toLowerCase(), urlWordsCounts.get(word.toLowerCase()) + 1);
            }
         }
         // Extract the top three words with count in Descending Order
         PriorityQueue<Map.Entry<String, Integer>> sortedSet = sortOrderByCount(urlWordsCounts);
         int count = 0;
         LinkedHashMap<String, Integer> wordsCount = new LinkedHashMap<String, Integer>();
         while (!sortedSet.isEmpty()) {
            Map.Entry<String, Integer> map = sortedSet.poll();
            if (count < 3) {
               wordsCount.put(map.getKey(), map.getValue());
               count++;
            }
            if (allWordsCount.containsKey(map.getKey())) {
               allWordsCount.put(map.getKey(), allWordsCount.get(map.getKey()) + map.getValue());
            } else {
               allWordsCount.put(map.getKey(), map.getValue());
            }
         }
         LinkedHashMap<String, LinkedHashMap<String, Integer>> h = new LinkedHashMap<>();
         h.put(url, wordsCount);
         allUrlsWordsCount.add(h);
      }
      return allUrlsWordsCount;
   }

   // get all the words from Words file
   HashSet<String> getWords() {
      HashSet<String> wordsList = new HashSet<>();
      try {
         // read the words file
         File wordFile = new File(currDir.getAbsolutePath() + "/Words.txt");
         Scanner inputWord = new Scanner(wordFile);
         while (inputWord.hasNextLine()) {
            String word = inputWord.nextLine();
            if (word.trim().length() > 0) {
               String words = word.trim().replaceAll("\\s{2,}", " ");
               String splitWord[] = words.split(" ");
               for (String singleword : splitWord) {
                  // store the words in HashSet
                  wordsList.add(singleword.toLowerCase());
               }
            }
         }
         inputWord.close();
      } catch (FileNotFoundException exception) {
         System.out.println(exception);
         System.out.println("Words file is not found");
         return null;
      } catch (Exception exception) {
         System.out.println(exception);
         System.out.println("Some thing is wrong");
         return null;
      }
      return wordsList;
   }

   // get all the urls from Urls file
   HashSet<String> getUrls() {
      HashSet<String> urlList = new HashSet<>();
      try {
         // read the Urls file
         File urlsFile = new File(currDir.getAbsolutePath()+"/Urls.txt");
         Scanner inputUrl = new Scanner(urlsFile);
         while (inputUrl.hasNextLine()) {
            String urls = inputUrl.nextLine();
            if (urls.trim().length() > 0) {
               String url = urls.trim().replaceAll("\\s{2,}", " ");
               String splitUrl[] = url.split(" ");
               for (String singleword : splitUrl) {
                  // store the urls in HashSet
                  urlList.add(singleword.toLowerCase());
               }
            }
         }
         inputUrl.close();
      } catch (FileNotFoundException exception) {
         System.out.println(exception);
         System.out.println("Urls file is not found");
         return null;
      } catch (Exception exception) {
         System.out.println(exception);
         System.out.println("Some thing is wrong");
         return null;
      }
      return urlList;
   }

   public static void main(String args[]) {
      Scrapping scrapper = new Scrapping();
      HashSet<String> wordsList = scrapper.getWords();// get the word List from Words file
      HashSet<String> urlsList = scrapper.getUrls();// get the Url List from Urls file
      if (wordsList == null) {
         return;
      }

      if (urlsList == null) {
         return;
      }

      // check the Urls file is empty
      if (urlsList.isEmpty()) {
         System.out.println("Urls file is empty");
         return;
      }

      // check the Words file is empty
      if (wordsList.isEmpty()) {
         System.out.println("Words File is empty");
         return;
      }

      // get all the Urls with top three sorted words by count
      ArrayList<LinkedHashMap<String, LinkedHashMap<String, Integer>>> urlsWordsCount = scrapper
            .fetchWordsCount(urlsList, wordsList);
      if (urlsWordsCount.isEmpty()) {
         return;
      }
      System.out.println("========");
      for (LinkedHashMap<String, LinkedHashMap<String, Integer>> allUrl : urlsWordsCount) {
         for (Map.Entry<String, LinkedHashMap<String, Integer>> urlWordsCount : allUrl.entrySet()) {
            System.out.println(urlWordsCount.getKey());
            for (Map.Entry<String, Integer> wordCount : urlWordsCount.getValue().entrySet()) {
               if (wordCount.getValue() == 0) {
                  System.out.println("All other words count is zero");
                  break;
               }
               System.out.println(wordCount.getKey() + "->" + wordCount.getValue());
               System.out.println();
            }
            System.out.println("=======================================================\n");
         }
      }

      // Added the all the word in to priority queue with count
      // Print the all in sorted order(Descending)
      PriorityQueue<Map.Entry<String, Integer>> sortedWords = scrapper
            .sortOrderByCount(scrapper.getAllWordsCount());
      System.out.println("========");
      while (!sortedWords.isEmpty()) {
         // Print all the wors with count in sorted order
         Map.Entry<String, Integer> word = sortedWords.poll();
         if (word.getValue() == 0) {
            System.out.println(word.getKey() + " is not present");
         } else {
            System.out.println(word.getKey() + " ->" + word.getValue());
         }
      }
   }
}