package org.example;
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



class WebContent {

    // getting the text from website using the url
    String getUrlContent(String url) {
        String content = "";
        try {
            // Get the complete document
            Document document = Jsoup.connect(url).get();
            // Get all the text from text tags
            content = document.body().text();
            // Regular Expression to remove any special character or unwanted spaces
            content = content.replaceAll("[^a-zA-Z\\w]", " ");
        } catch (IOException e) {
            System.out.println("Invalid Url: " + e);
        }
        // return the content
        return content;
    }


    // Function to count the frequency of the given word
    int countStoredWordOccurence(String word, String urlContent) {
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

public class WordScraper {

    public static void main(String[] args) {

        String homeDir = System.getProperty("user.dir");


        try {
            // Get URLS from the text file

            ArrayList<String> urls = new ArrayList<>();
            Scanner Reader = new Scanner(new File("C:\\Users\\apurva shukla\\urls.txt"));

            while (Reader.hasNext()) {
                urls.add(Reader.next());
            }

            // Get words from the text file

            ArrayList<String> words = new ArrayList<>();
            Reader = new Scanner(new File("C:\\Users\\apurva shukla\\Desktop\\words.txt"));
            while (Reader.hasNext()) {
                words.add(Reader.next());
            }


            if (urls.isEmpty() || words.isEmpty()){

                if(urls.isEmpty())
                    System.out.println("Urls file is empty");

                if (words.isEmpty())
                    System.out.println("Word file is empty");


            }

            else{



            // Find words in URL

            // Creating object of Class WebContent
            WebContent webContentObj = new WebContent();

            // Map to store the word as key and its frequency as value
            Map<String, Integer> AllwordsFrequencyMap = new TreeMap<String, Integer>();

            System.out.println();
            for (String url : urls) {
                // Storing words for each url
                Map<String, Integer> wordFrequencyMap = new TreeMap<String, Integer>();

                System.out.println("Top 3 words for url- "+ url);
                System.out.println();
                // Calling the pass url method
                String urlContent = webContentObj.getUrlContent(url);

                for (String word : words) {
                    // Getting the frequency of the word in the url
                    int wordOccurence = webContentObj.countStoredWordOccurence(word, urlContent);

                    // Adding the word as key and frequency as value
                    wordFrequencyMap.put(word, wordOccurence);
                }
                // Sort map in non-ascending order
                wordFrequencyMap = WebContent.sortByValue(wordFrequencyMap);
                int counter = 0;
                // Printing the top three words that occur the most
                for (Map.Entry m : wordFrequencyMap.entrySet())
                {
                    String key = (String) m.getKey();
                    int value = (int) m.getValue();
                    if (counter++ < 3) {
                        System.out.println(m.getKey() + " " + m.getValue());

                        if (AllwordsFrequencyMap.get(key) != null) {
                            // Adding the frequency of words that occur in multiple urls
                            value += AllwordsFrequencyMap.get(key);
                        }

                        AllwordsFrequencyMap.put(key, value);
                    } else{

                    if (AllwordsFrequencyMap.get(key) != null) {
                        // Adding the frequency of words that occur in multiple urls
                        value += AllwordsFrequencyMap.get(key);
                    }
                    AllwordsFrequencyMap.put(key, value);

                    }
                }
                System.out.println();
            }
            System.out.println();
            System.out.println("Overall occurence of the given words across all the urls :");

            AllwordsFrequencyMap = WebContent.sortByValue(AllwordsFrequencyMap);
            for (Map.Entry m : AllwordsFrequencyMap.entrySet()) {

                System.out.println(m.getKey() + " " + m.getValue());
            }

            System.out.println(); }
        } catch (FileNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }
}