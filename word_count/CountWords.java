package word_count;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import org.jsoup.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.*;

public class CountWords {
    public static void main(String args[]) throws Exception{
        File inputFile=new File("E:\\Work\\java work\\Module2\\src\\word_count\\input.txt");
        BufferedReader br=new BufferedReader(new FileReader(inputFile));
        
        ArrayList<String> inputUrls=new ArrayList<>();
        ArrayList<String> inputWords=new ArrayList<>();
        
        //created a arrayList of hashMap to store occurence of every word in every url.
        ArrayList<HashMap<String,Integer>> maps=new ArrayList<>();
        
        while(true){
            String temp=br.readLine();
            if(temp==null) break;
            inputUrls.add(temp);
            maps.add(new HashMap<>());
        }
        inputFile=new File("E:\\Work\\java work\\Module2\\src\\word_count\\words.txt");
        
        for(int i=0;i<maps.size();i++){
            br=new BufferedReader(new FileReader(inputFile));
            while(true){
            String temp=br.readLine();
            if(temp==null) break;
            inputWords.add(temp);
            maps.get(i).put(temp,0);
        }
        }
        for(int i=0;i<inputUrls.size();i++){
            String s=inputUrls.get(i);
            URL url=new URL(s);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            String text="";
            while ((inputLine = in.readLine()) != null)
                text+=inputLine;
            in.close();
            
            //filtering
            String filteredText=Jsoup.parse(text).text();
            
            for(String word:inputWords){
                Pattern pattern = Pattern.compile("\\W*([ ]"+word+"[. ])\\W*");
                Matcher matcher = pattern.matcher(filteredText);
                String finalRes="";
                while(matcher.find()){
                    String key=matcher.group();
                    maps.get(i).put(word,maps.get(i).get(word)+1);
                }
                
            }
            List<Map.Entry<String,Integer>> list=new ArrayList<>(maps.get(i).entrySet());
            Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
                @Override
                public int compare(Map.Entry<String,Integer> m1,Map.Entry<String,Integer> m2){
                    return m2.getValue()-m1.getValue();
                }
            });
            
            System.out.println(inputUrls.get(i));
            for(int j=0;j<3;j++){
                System.out.println(list.get(j).getKey()+" = "+list.get(j).getValue());
            }
            System.out.println();
            System.out.println("===============");
            System.out.println();
        }
        
        //merging all maps
        for(int i=1;i<maps.size();i++){
            maps.get(i).forEach((key, value) ->
        maps.get(0).merge(key, value, (v1, v2) -> v1 + v2) );
        }
        
        List<Map.Entry<String,Integer>> list=new ArrayList<>(maps.get(0).entrySet());
            Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
                @Override
                public int compare(Map.Entry<String,Integer> m1,Map.Entry<String,Integer> m2){
                    return m2.getValue()-m1.getValue();
                }
            });
            System.out.println("merged output of all urls");
            for(int j=0;j<list.size();j++){
                System.out.println(list.get(j).getKey()+" = "+list.get(j).getValue());
            }
        
    }
}
