package Problem1;
// Importing JSoup Library
/* Jsoup is a Java Library to Parsing HTML to Java*/
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

// Pair class : to store key,value in array
class Pair{
    String word;
    int freq;  // frequency

    public Pair(String word, int freq) {
        this.word = word;
        this.freq = freq;
    }
    @Override
    public String toString() {
        return "Word: " + word + "-" + freq;
    }
}

public class WebScrapping {
    public static void main(String[] args) throws Exception {
        Map<String, Integer> map = new HashMap<>();


        String wordReader;
        try {
            // Get the current working directory
            String homeDir = System.getProperty("user.dir");
            //  Create a file to get words from the file Words.txt
            File wordsFile = new File(homeDir + "/Problem1/Words.txt");
            BufferedReader wordBuffer = new BufferedReader(new FileReader(wordsFile));
            ArrayList<String> words = new ArrayList<>();
            //  Create a file to get words from the file Words.txt
            while ((wordReader = wordBuffer.readLine()) != null) {
                map.put(wordReader, 0);
                words.add(wordReader);
            }
            words = (ArrayList<String>) words.stream().distinct().collect(Collectors.toList());
            // checking for the words array
            if(words.size() == 0 || map.size() == 0){
                System.out.println("No words found in the file");
                return;
            }
            // Create a file to get urls from the file urls.txt
            File url = new File(homeDir + "/Problem1/URL.txt");
            // Using BufferedReader to read url from urls.txt
            BufferedReader urlBuffer = new BufferedReader(new FileReader(url));


            System.out.println("------------- Output #1 --------------");
            //reading urls line by line
            ArrayList<String> urls = new ArrayList<>();
            String urlR;
            while ((urlR = urlBuffer.readLine()) != null) {
                urls.add(urlR);
            }
            urls = (ArrayList<String>) urls.stream().distinct().collect(Collectors.toList());
            for(String urlReader : urls){
            /* Jsoup has `connect` method to that accepts url as string and uses `Connection` Interface behind
               the scene to fetch a `Connection`  Object Reference : https://jsoup.org/apidocs/org/jsoup/Connection.html
             *
                /*  `.get()` parsed the result to Document*/
                Document doc = Jsoup.connect(urlReader).get();
                // Body method get the <body></body> tag and `.text()` extracts text from HTML tag
                String text = doc.body().text();
                System.out.println("---------------------------------------");
                System.out.println("For URL : " + urlReader);

                ArrayList<Pair> list = new ArrayList<>();
                for (String word : words) {
                    int count = getCount(word, text);
                    // Adding frequencies
                    list.add(new Pair(word, count));
                    if (map.containsKey(word) && count > map.get(word)) map.put(word, count);
                    else if (!map.containsKey(word)) map.put(word, count);
                }
                list.sort((pair1, pair2) -> pair2.freq - pair1.freq);
                for (int index = 0; list.size() >= 3 && index < 3; index++) {
                    System.out.println(list.get(index).toString());
                }

            }
            // Comparator to sort the hashmap by values (Frequencies)
            Comparator valueComparator = new Comparator() {
                // k1 and k2 are objects are temporary variable to get the comparator to sort
                public int compare(Object k1, Object k2) {
                    int comp = map.get(k2).compareTo(map.get(k1));
                    if (comp == 0) return 1;
                    else return comp;
                }
            };
            TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(valueComparator);

            sortedMap.putAll(map);

            System.out.println("------------- Output #2 --------------");
            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                System.out.println(entry.getKey() + "-" + entry.getValue());
            }
            System.out.println("---------------------------------------");
            // Handling Exception for Internet Connection
        } catch (java.net.UnknownHostException e){
            System.out.println("Failed to Connect to the Internet");
            e.printStackTrace();

             // Handling Exception for file
        } catch (IOException e){
                    System.out.println("File not Found!!");
            e.printStackTrace();
            // Handling any other Exception : Jsoup
        } catch (Exception e){
            System.out.println("Exception :" + e.getMessage());
        }

    }

//    Function to count the occurrence of each word in the file
    public static int getCount(String word, String text) {
        String[] wordsArray = text.split(" ");
        int count = 0;
        for (String eachWord : wordsArray) {
            if (eachWord.equalsIgnoreCase(word)) {
                count++;
            }
        }
        return count;
    }
}
