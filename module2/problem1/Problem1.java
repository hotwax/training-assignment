import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;  

public class Problem1{
    public static void main(String[] args) {
      try {
        FileReader fr = new FileReader("D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem1\\urls.txt");
        BufferedReader br = new BufferedReader(fr);

        HashMap<String, Integer> map1 = new HashMap<>(); //for output 2

        System.out.println("Output #1 \n======== \n");

        String surl="";
        while ((surl=br.readLine())!=null) {
           URL url = new URL(surl);
           InputStreamReader ir = new InputStreamReader(url.openStream());
           BufferedReader brr = new BufferedReader(ir);

           String siteHtml="";
           String unfilteredData = "";

           while ((siteHtml=brr.readLine())!=null) {
               unfilteredData+=siteHtml;
           }

          //  System.out.println(unfilteredData);

           String filteredData = Jsoup.parse(unfilteredData).text();

          //  System.out.println(filteredData);

           HashMap<String, Integer> map = new HashMap<>();  //for output 1
           
           FileReader fr1 = new FileReader("D:\\Nidhi pal\\hotwax\\training-assignment\\module2\\problem1\\words.txt");
           BufferedReader br1 = new BufferedReader(fr1);

           String sword="";
           while ((sword=br1.readLine())!=null) {
              String regex= sword;
              Pattern pattern = Pattern.compile(regex);
              Matcher matcher = pattern.matcher(filteredData);

              int count=0;
              while (matcher.find()) {
                count++;
              }

              map.put(sword, count);  //for output 1

              if (map1.containsKey(sword)) {   // for output 2
                map1.put(sword, map1.get(sword)+count);
              }
              else{
                map1.put(sword, count);
              }
           }

           ArrayList<Map.Entry<String, Integer>> al = new ArrayList<>(map.entrySet()) ;
           Collections.sort(al, new MyComparator());

           System.out.println(surl);
           for (int i = 0; i < 3; i++) {
             System.out.println(al.get(i));
           }
           System.out.println();


        }

        System.out.println("============================== \nOutput #2 \n======== \n");
        ArrayList<Map.Entry<String, Integer>> al1 = new ArrayList<>(map1.entrySet());
        Collections.sort(al1, new MyComparator());
        for (int i = 0; i < al1.size(); i++) {
          System.out.println(al1.get(i));
        }

        
        br.close();
          

      } catch (Exception e) {
        System.out.println(e);
      }

    }

    public static class MyComparator implements Comparator<Map.Entry<String, Integer>>{

      public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2){
          return e2.getValue()-e1.getValue();
      }
    }
}