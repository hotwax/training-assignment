
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

public class webscraper {
    // Defining a static function named "printword" with three parameters and an
    // exception so that we can find the top 3 words in a particular url
    static void printword(String st, Map<String, Integer> wordmap, Map<String, Integer> wordmaplocal) throws Exception {
        // Initializing "doc" variable with null
        Document doc = null;
        // Parsing the URL and getting the HTML content of the page
        doc = Jsoup.connect(st).get();
        String text = doc.body().text();

        // Splitting the HTML content into an array of words
        String a[] = text.split(" ");

        // Looping through the array of words
        for (int i = 0; i < a.length; i++) {
            // // If the word is present in the main map, increase its count by 1
            if (wordmap.get(a[i]) != null) {

                int temp = wordmap.get(a[i]) + 1;

                wordmap.replace(a[i], temp);
                // If the word is present in the local map, increase its count by 1
                if (wordmaplocal.get(a[i]) != null) {
                    int localtemp = wordmaplocal.get(a[i]) + 1;
                    wordmaplocal.replace(a[i], localtemp);

                } else {
                    wordmaplocal.put(a[i], 1);
                }
            }

        }

        // printing the empty line
        System.out.println();
        // Sorting the local map by value in descending order
        wordmaplocal = sortByValue(wordmaplocal);
        // Printing the top 3 words from the local map
        System.out.println("Top 3 words are on website url : " + st + " are -");
        int valueprint = 3;

        for (Map.Entry<String, Integer> entry : wordmaplocal.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            valueprint--;
        }
        // If there are less than 3 words in the local map, print 0 for the remaining
        // positions
        if (valueprint != 0) {
            while (valueprint > 0) {
                System.out.println(0);
                valueprint--;
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
        Map<String, Integer> wordmap = new TreeMap<>();
        int numberofword = 0;

        // Read words.txt file and store the word count in wordmap

        BufferedReader brword;
        try {

            brword = new BufferedReader(new FileReader(words));

            String wordsextract;

            // Loop through each line of words.txt and store the word count in wordmap
            while ((wordsextract = brword.readLine()) != null) {
                // Store the word count for each word in wordmap
                wordmap.put(wordsextract, 0);
                numberofword++;
            }

        } catch (FileNotFoundException e1) {
            // If words.txt file is not found, print the stack trace
            e1.printStackTrace();
        } catch (IOException e) {
            // If there's an error reading the file, print the stack trace
            e.printStackTrace();
        }

        // Read urls.txt file and print the word count for each URL

        BufferedReader br;
        try {

            br = new BufferedReader(new FileReader(urls));

            String st;
            // Loop through each line of urls.txt
            while ((st = br.readLine()) != null) {
                // Create a TreeMap to store the word count for each word in the current URL
                Map<String, Integer> wordmaplocal = new TreeMap<>();
                // Print the word count for each word in the current URL
                printword(st, wordmap, wordmaplocal);
            }

        } catch (FileNotFoundException e1) {
            // If urls.txt file is not found, print the stack trace
            e1.printStackTrace();
        } catch (IOException e) {
            // If there's an error reading the file, print the stack trace
            e.printStackTrace();
        } catch (Exception e) {
            // If there's any other error, print the stack trace
            e.printStackTrace();
        }

        // Sort wordmap by value and print the top 3 words
        wordmap = sortByValue(wordmap);
        // Print the top 3 words in wordmap
        System.out.println();
        System.out.println("Total top 3 words are :");

        int decreasevalue = 3;
        for (Map.Entry<String, Integer> entry : wordmap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            decreasevalue--;

            // Break out of the loop after printing the top 3 words
            if (decreasevalue == 0) {
                break;
            }

        }

    }

}
