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
import java.util.random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

//before compilation- set classpath=%classpath%;D:\Nidhi pal\hotwax\training-assignment\module2\problem1\jsoup-1.15.3.jar;

public class CountWords {

  public static void main(String[] args) {
    try {
      FileReader fileReaderForUrls = new FileReader(
          "D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem1\\urls.txt");
      BufferedReader bufferedReaderForUrls = new BufferedReader(fileReaderForUrls);

      HashMap<String, Integer> mapForOutput2 = new HashMap<>(); // for output 2

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

        HashMap<String, Integer> mapForOutput1 = new HashMap<>(); // for output 1

        FileReader fileReaderForWords = new FileReader(
            "D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem1\\words.txt");
        BufferedReader bufferedReaderForWords = new BufferedReader(fileReaderForWords);

        String wordString = "";
        while ((wordString = bufferedReaderForWords.readLine()) != null) {
          String regex = wordString;
          Pattern pattern = Pattern.compile(regex);
          Matcher matcher = pattern.matcher(filteredData);

          int count = 0;
          while (matcher.find()) {
            count++;
          }

          mapForOutput1.put(wordString, count); // for output 1

          if (mapForOutput2.containsKey(wordString)) { // for output 2
            mapForOutput2.put(wordString, mapForOutput2.get(wordString) + count);
          } else {
            mapForOutput2.put(wordString, count);
          }
        }

        ArrayList<Map.Entry<String, Integer>> output1 = new ArrayList<>(mapForOutput1.entrySet());
        Collections.sort(output1, new MyComparator());

        System.out.println(urlString);
        for (int i = 0; i < 3; i++) {
          System.out.println(output1.get(i));
        }
        System.out.println();

        bufferedReaderForWords.close();
        bufferedReaderForUrlData.close();

      }

      System.out.println("============================== \nOutput #2 \n======== \n");
      ArrayList<Map.Entry<String, Integer>> output2 = new ArrayList<>(mapForOutput2.entrySet());
      Collections.sort(output2, new MyComparator());
      for (int i = 0; i < output2.size(); i++) {
        System.out.println(output2.get(i));
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