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

//before compilation- set classpath=%classpath%;D:\Nidhi pal\hotwax\training-assignment\module2\problem1\jsoup-1.15.3.jar;

public class CountWords {

  public static void main(String[] args) {
    try {
      FileReader fileReaderForUrls = new FileReader("urls.txt");
      BufferedReader bufferedReaderForUrls = new BufferedReader(fileReaderForUrls);

      HashMap<String, Integer> totalNumberOfOccurrences = new HashMap<>(); // total number of occurences of a word across all urls

      System.out.println("Output #1 \n======== \n");

      String urlString = "";
      while ((urlString = bufferedReaderForUrls.readLine()) != null) {
        URL urlObject = new URL(urlString);
        InputStreamReader inputStreamReaderForUrlData = new InputStreamReader(urlObject.openStream());
        BufferedReader bufferedReaderForUrlData = new BufferedReader(inputStreamReaderForUrlData);

        String siteHtml = "";
        StringBuilder unfilteredData = new StringBuilder(); // full content of site including tags eg- <html>, <script>

        while ((siteHtml = bufferedReaderForUrlData.readLine()) != null) {
          unfilteredData.append(siteHtml);
        }

        String filteredData = Jsoup.parse(unfilteredData.toString()).text(); // only text content of site

        // System.out.println(filteredData);

        HashMap<String, Integer> numberOfOccurrences = new HashMap<>(); // number of occurences of a word in the present url 

        FileReader fileReaderForWords = new FileReader("words.txt");
        BufferedReader bufferedReaderForWords = new BufferedReader(fileReaderForWords);

        String wordString = "";
        while ((wordString = bufferedReaderForWords.readLine()) != null) {
          String regex = wordString;
          Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
          Matcher matcher = pattern.matcher(filteredData);

          int count = 0;
          while (matcher.find()) {
            count++;
          }

          numberOfOccurrences.put(wordString, count); // occurences of the word in the present url

          if (totalNumberOfOccurrences.containsKey(wordString)) { // total number of occurences of the word across all urls
            totalNumberOfOccurrences.put(wordString, totalNumberOfOccurrences.get(wordString) + count);
          } else {
            totalNumberOfOccurrences.put(wordString, count);
          }
        }

        ArrayList<Map.Entry<String, Integer>> arrayListForNumberOfOccurrences = new ArrayList<>(numberOfOccurrences.entrySet());
        Collections.sort(arrayListForNumberOfOccurrences, new MyComparator());

        System.out.println(urlString);
        for (int i = 0; i < 3; i++) {
          System.out.println(arrayListForNumberOfOccurrences.get(i));
        }
        System.out.println();

        bufferedReaderForWords.close();
        bufferedReaderForUrlData.close();

      }

      System.out.println("============================== \nOutput #2 \n======== \n");
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
      return entry2.getValue() - entry1.getValue();
    }
  }
}