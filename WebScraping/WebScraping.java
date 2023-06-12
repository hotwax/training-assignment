
//importing Java libraries
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.File;
import java.io.FileNotFoundException;

//defining class GetData
class GetData {
    public HashMap<String, Integer> wordFrequencyMap;
    public ArrayList<String> wordsList;

    GetData(ArrayList<String> words) {
        this.wordFrequencyMap = new HashMap<>();
        this.wordsList = words;
    }

    void scrapeData(String url) throws IOException {
        HashMap<String, Integer> wordMap = new HashMap<String, Integer>();

        // storing the words in Map
        for (String word : wordsList) {
            wordMap.put(word, 0);
        }

        // Connecting to the web page
        Connection connection = Jsoup.connect(url);
        // executing the get request
        Document document = connection.get();
        // Retrieving the content of the web page
        String pageContent = document.body().text();
        // Removing non-Alphanumeric characters
        pageContent = pageContent.replaceAll("[^a-zA-Z ' 's]", "");
        String[] words = pageContent.split(" ");
        // counting the frequency of each word in the HashMap
        for (String word : words) {
            if (wordMap.containsKey(word)) {
                wordMap.put(word, wordMap.get(word) + 1);
            }
        }
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(wordMap.entrySet());
        Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedList) {
            // look for top 3 words in each web page
            if (count < 3) {
                System.out.println(entry.getKey() + " " + "-" + " " + entry.getValue());
            }
            if (wordFrequencyMap.containsKey(entry.getKey())) {
                wordFrequencyMap.put(entry.getKey(), entry.getValue() + wordFrequencyMap.get(entry.getKey()));
            } else {
                wordFrequencyMap.put(entry.getKey(), entry.getValue());
            }
            count++;
        }
    }
}

public class WebScraping {
    public static void main(String args[]) throws IOException {
        /* ArrayList for Word.txt */
        ArrayList<String> wordList = new ArrayList<String>();
        // reading Word.txt
        try {
            // Get the current working directory
            String homeDir = System.getProperty("user.dir");
            // Create a file to get words from the file Words.txt
            File wordFile = new File((homeDir + "/WebScraping/Word.txt"));
            Scanner myReader = new Scanner(wordFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] split = data.split(" ");
                for (String word : split) {
                    wordList.add(word);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Word.txt not found.");
            e.printStackTrace();
        }
        // checking if Word.txt is empty
        if (wordList.isEmpty()) {
            System.out.println("Word file is empty");
            return;
        }

        GetData dataFetcher = new GetData(wordList);

        ArrayList<String> urlList = new ArrayList<String>();
        // reading file Url.txt
        try {
            String homeDir = System.getProperty("user.dir");
            // Create a file to get urls from the file urls.txt
            File urlFile = new File(homeDir + "/WebScraping/Url.txt");
            Scanner myReader = new Scanner(urlFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                urlList.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File Url.txt not found.");
            exception.printStackTrace();
        }
        // checking if Url.txt is empty
        if (urlList.isEmpty()) {
            System.out.println("Url file is empty");
            return;
        }
        System.out.println("========");
        System.out.println();
        // Iterate over the Urls ArrayList and process each URL
        for (int index = 0; index < urlList.size(); index++) {
            System.out.println(urlList.get(index));
            dataFetcher.scrapeData(urlList.get(index));
            System.out.println();
        }
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(dataFetcher.wordFrequencyMap.entrySet());
        Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });

        for (Map.Entry<String, Integer> entry : sortedList) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }
}