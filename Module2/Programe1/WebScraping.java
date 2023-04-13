
//Importing necessary packages
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//Defining a public class named "websraper"
public class WebScraping {
    // Defining a static function named "printword" with three parameters and an
    // exception so that we can find the top 3 words in a particular url
    static void printWord(String url, Map<String, Integer> wordMap, Map<String, Integer> wordMapLocal)
            throws Exception {
        // Initializing "doc" variable with null
        Document doc = null;
        // Parsing the URL and getting the HTML content of the page
        doc = Jsoup.connect(url).get();
        String text = doc.body().text();

        // Splitting the HTML content into an array of words
        String a[] = text.split(" ");

        // Looping through the array of words
        for (int i = 0; i < a.length; i++) {
            // // If the word is present in the main map, increase its count by 1
            if (wordMap.get(a[i]) != null) {

                int temp = wordMap.get(a[i]) + 1;

                wordMap.replace(a[i], temp);
                // If the word is present in the local map, increase its count by 1
                if (wordMapLocal.get(a[i]) != null) {
                    int localtemp = wordMapLocal.get(a[i]) + 1;
                    wordMapLocal.replace(a[i], localtemp);

                } else {
                    wordMapLocal.put(a[i], 1);
                }
            }

        }

        // printing the empty line
        System.out.println();
        // Sorting the local map by value in descending order
        wordMapLocal = sortByValue(wordMapLocal);
        // Printing the top 3 words from the local map
        System.out.println("Top 3 words are on website url : " + url + " are -");
        int valuePrint = 3;

        for (Map.Entry<String, Integer> entry : wordMapLocal.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            valuePrint--;
            if (valuePrint == 0) {
                break;
            }
        }
        // If there are less than 3 words in the local map, print 0 for the remaining
        // positions
        if (valuePrint != 0) {
            while (valuePrint > 0) {
                System.out.println(0);
                valuePrint--;
            }
        }

    }

    // Defining a function to sort a map by value in descending order
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

    @SuppressWarnings("unused")

    public static void main(String[] args) {

        // Get the current working directory
        String dir = System.getProperty("user.dir");
        // Create file objects for urls.txt and words.txt
        File urls = new File(dir + "/src/urls.txt");
        File words = new File(dir + "/src/words.txt");

        // Create a TreeMap to store the word count for each word in words.txt
        Map<String, Integer> wordMap = new TreeMap<>();
        int numberOfWord = 0;

        // Read words.txt file and store the word count in wordmap

        BufferedReader brWord;
        try {

            brWord = new BufferedReader(new FileReader(words));

            String wordsExtract;

            // Loop through each line of words.txt and store the word count in wordmap
            while ((wordsExtract = brWord.readLine()) != null) {
                // Store the word count for each word in wordmap
                wordMap.put(wordsExtract, 0);
                numberOfWord++;
            }

            BufferedReader brUrl;
            brUrl = new BufferedReader(new FileReader(urls));

            String url;
            // Loop through each line of urls.txt
            while ((url = brUrl.readLine()) != null) {
                // Create a TreeMap to store the word count for each word in the current URL
                Map<String, Integer> wordMapLocal = new TreeMap<>();
                // Print the word count for each word in the current URL
                try {
                    printWord(url, wordMap, wordMapLocal);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    System.out.println("Somethings went wrong in print the words");
                }
            }

            // Sort wordmap by value and print the top 3 words
            wordMap = sortByValue(wordMap);
            // Print the top 3 words in wordmap
            System.out.println();
            System.out.println("Total top 3 words are :");

            int decreaseValue = 3;
            for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
                decreaseValue--;

                // Break out of the loop after printing the top 3 words
                if (decreaseValue == 0) {
                    break;
                }

            }

        } catch (FileNotFoundException e1) {
            // If words.txt file is not found, print the stack trace
            System.out.println("File not found");
        } catch (IOException e) {
            // If there's an error reading the file, print the stack trace
            e.printStackTrace();
        }

        // Read urls.txt file and print the word count for each URL

    }

}
