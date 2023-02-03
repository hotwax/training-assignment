import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// For Jsoup Library

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/* For setting path - in cmd
 * set classpath=%classpath%;C:\Users\LENOVO\Desktop\Hotwax\module2\training-assignment\Problem Statement - 1\jsoup-1.15.3.jar;
 */

/* Using jsoup Library */

/*Jsoup is an open source Java library used mainly for extracting data from HTML. 
It also allows you to manipulate and output HTML. It has a steady development line, 
great documentation, and a fluent and flexible API. Jsoup can also be used to parse 
and build XML.
features of jsoup:

Loading: fetching and parsing the HTML into a Document
Filtering: selecting the desired data into Elements and traversing it
Extracting: obtaining attributes, text, and HTML of nodes
Modifying: adding/editing/removing nodes and editing their attributes */

public class CountWordInWebPage {

    public static List<String> listOfWords = new ArrayList<String>();
    public static List<String> listOfUrls = new ArrayList<String>();
    public static List<String> lsitOfUrlData = new ArrayList<String>();
    public static HashMap<String, Integer> totalWordOccurrence = new HashMap<String, Integer>(); // Creating HashMap for
    // wordOccurrence of all urls  

    public static void main(String args[]) throws Exception {

        // Reading the words file and urls file

        try {

            // Reading from the words file and adding all the content to the listOfwords
            File wordsFile = new File("words.txt");
            Scanner Reader = new Scanner(wordsFile); // Scanner object

            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                listOfWords.add(data);
            }

            Reader.close();

            // Reading the urls file and adding all the content to the list of urls

            File urlsFile = new File("urls.txt");
            Reader = new Scanner(urlsFile); // Scanner object

            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                listOfUrls.add(data);
            }

            Reader.close();

        } catch (IOException exception) {
            System.out.println("Exception occurred" + exception);
        } catch (Exception exception) {
            System.out.println("Exception occurred" + exception);
        }

        /* fetching data from the urls */
        // looping through the listofUrls

        // just printing output 1

        System.out.println("Output #1 - the top 3 words on the given list of urls");

        System.out.println("\n===========");

        // looping through the urls list for fetching the data through jsoup

        for (String url : listOfUrls) {

            /*
             * Document class represents an HTML document loaded through the Jsoup library.
             */
            // fetching the data
            Document doc = Jsoup.connect(url).get();

            // filtering text from Jsoup Document

            String text = doc.body().text();

            text = text.replaceAll("[^a-zA-Z\\w]", " ");

            String wordArray[] = text.split(" ");

            // creating hashmap for word occurrence

            HashMap<String, Integer> wordOccurrence = new HashMap<String, Integer>(); // Creating HashMap for
                                                                                      // wordOccurrence

            // finding words in text and calculating their occurence

            for (String word : listOfWords) {
                for (String str : wordArray) {
                    if (str.equalsIgnoreCase(word)) {
                        wordOccurrence.put(word, wordOccurrence.getOrDefault(word, 0) + 1);
                        totalWordOccurrence.put(word, totalWordOccurrence.getOrDefault(word, 0) + 1);
                    }
                }
            }

            // sorting the entrries by creating list

            List<HashMap.Entry<String, Integer>> listOfWord = sortByValueAndReturnList(wordOccurrence);

            // Printing the Output for the user

            System.out.println("\n" + url);

            // looping through the map and printing the key value

            // printing th top 3 value
            int count = 0;

            for (HashMap.Entry<String, Integer> entry : listOfWord) {
                System.out.println("\n" + entry.getKey() + " - " + entry.getValue());
                count++; // increasing count for printing only top 3 entries
                if (count == 3) {
                    break;
                }
            }

        }

        // sorting totalwords map by creating a list

        List<HashMap.Entry<String, Integer>> listOfTotalWords = sortByValueAndReturnList(totalWordOccurrence);

        // Printing the outputs from all urls

        System.out.println("\n===========================");

        System.out.println("\nOutput #2 - total number of occurrences across all the specified URLs");

        System.out.println("\n===========");

        // looping through the totalListofwords

        for (HashMap.Entry<String, Integer> entry : listOfTotalWords) {
            System.out.println("\n" + entry.getKey() + " - " + entry.getValue());
        }

    }

    // function to sort hashmap by making a list and returning it 

    public static List<HashMap.Entry<String, Integer>> sortByValueAndReturnList(HashMap<String, Integer> hashMap) {
        // Create a list from elements of HashMap
        List<HashMap.Entry<String, Integer>> list = new ArrayList<HashMap.Entry<String, Integer>>(hashMap.entrySet());

        // creating a comparator

        Comparator<HashMap.Entry<String, Integer>> mapValueComparator = Comparator.comparing(
                HashMap.Entry::getValue,
                Comparator.reverseOrder());

        // Sort the list using collections sort
        Collections.sort(
                list,
                mapValueComparator);

        return list;
    }
}
