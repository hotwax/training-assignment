import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.net.URL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//before compilation- set classpath=%classpath%;D:\Nidhi pal\training-assignment\CountWords\jsoup-1.15.3.jar;

public class CountWords {

  public static void main(String[] args) {
    try {
      FileReader fileReaderForUrls = new FileReader("urls.txt");  
       
      //or we can use 
      // String currentDirPath = System.getProperty("user.dir");
      // FileReader fileReaderForUrls = new FileReader(currentDirPath+"\\urls.txt");

      BufferedReader bufferedReaderForUrls = new BufferedReader(fileReaderForUrls);

      HashMap<String, Integer> totalNumberOfOccurrences = new HashMap<>(); // total number of occurences of a word across all urls (key, value)->(word, total count)

      System.out.println("Top 3 words on the basis of number of occurrences in descending order in url: ");

      String urlString = "";

      //Here we will apply a loop on urls obtained, fetch the content of the present url. 
      //Then apply another loop on words obtained from words file.
      //For each word we will find word matches in the content of the url and put its count in numberOfOccurrences hashmap.
      //To find total number of occurrences of the given word across all urls we will keep updating(existing count + present count) totalNumberOfOccurrences hashmap with the count obtained.
      //To sort the count in descending order we can either use treemap or an arraylist to sort the counts of words, ,I have used arraylists.

      while ((urlString = bufferedReaderForUrls.readLine()) != null) {
        URL urlObject = new URL(urlString);
        InputStreamReader inputStreamReaderForUrlData = new InputStreamReader(urlObject.openStream());
        BufferedReader bufferedReaderForUrlData = new BufferedReader(inputStreamReaderForUrlData);

        String siteHtml = "";
        StringBuilder unfilteredData = new StringBuilder(); // full content of site including tags eg- <html>, <script>. Full code of site. 

        while ((siteHtml = bufferedReaderForUrlData.readLine()) != null) {  // to read line by line site data
          unfilteredData.append(siteHtml); 
        }

        String filteredData = Jsoup.parse(unfilteredData.toString()).text(); // only text content which is visible on site

        // System.out.println(filteredData);

        HashMap<String, Integer> numberOfOccurrences = new HashMap<>(); // number of occurences of a word in the present url (key, value)-> (word, count)

        FileReader fileReaderForWords = new FileReader("words.txt");
        BufferedReader bufferedReaderForWords = new BufferedReader(fileReaderForWords);

        String wordString = "";
        while ((wordString = bufferedReaderForWords.readLine()) != null) {
          //using regex to find match
          String regex = wordString;
          Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
          Matcher matcher = pattern.matcher(filteredData);

          int count = 0;
          while (matcher.find()) {
            count++;
          }

          numberOfOccurrences.put(wordString, count); // occurences of the word in the present url

          if (totalNumberOfOccurrences.containsKey(wordString)) { // total number of occurences of the word across all urls, existing count+present count
            totalNumberOfOccurrences.put(wordString, totalNumberOfOccurrences.get(wordString) + count);
          } else {
            totalNumberOfOccurrences.put(wordString, count);
          }
        }

        ArrayList<Map.Entry<String, Integer>> arrayListForNumberOfOccurrences = new ArrayList<>(numberOfOccurrences.entrySet()); //using arraylist to sort data 
        Collections.sort(arrayListForNumberOfOccurrences, new MyComparator());

        System.out.println(urlString+"\n");
        for (int i = 0; i < 3; i++) {
          System.out.println(arrayListForNumberOfOccurrences.get(i));
        }
        System.out.println();

        bufferedReaderForWords.close();
        bufferedReaderForUrlData.close();

      }

      System.out.println("Total number of occurences of each word across all urls. \n");
      ArrayList<Map.Entry<String, Integer>> arrayListForTotalNumberOfOccurrences = new ArrayList<>(totalNumberOfOccurrences.entrySet());
      Collections.sort(arrayListForTotalNumberOfOccurrences, new MyComparator());
      for (int i = 0; i < arrayListForTotalNumberOfOccurrences.size(); i++) {
        System.out.println(arrayListForTotalNumberOfOccurrences.get(i));
      }

      bufferedReaderForUrls.close();

    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  static class MyComparator implements Comparator<Map.Entry<String, Integer>> {

    public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
      return entry2.getValue() - entry1.getValue(); //descending order
    }
  }
}