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
import org.jsoup.nodes.Document;

/*
Jsoup :-
Jsoup is a Java library for working with real-world HTML.
It provides a very convenient API for fetching URLs and extracting and manipulating data,
using the best of HTML5 DOM methods and CSS selectors.

*/
public class CountWords {
    public static void main(String args[]) throws Exception{
        //list to store given urls
        ArrayList<String> inputUrls=new ArrayList<>();
        //list to store given words to search in url.
        ArrayList<String> inputWords=new ArrayList<>();     
        //created a arrayList of hashMap to store occurence of every word in every url.
        ArrayList<HashMap<String,Integer>> wordsFreq=new ArrayList<>();

        
        //====================================================================================================================
        //taking input urls from urls.txt file.
        //created an object of File class to perform operations on input files.
        File inputFile=new File("E:\\Work\\java work\\Module2\\src\\word_count\\urls.txt");
        
        //passing the object of File in FileReaded to read file and then pass the object of FileReader to BufferedReader
        BufferedReader bufferedReader=new BufferedReader(new FileReader(inputFile));
        //every url is stored as a new string in inputWords arraylist
        while(true){
            String currLine=bufferedReader.readLine();
            //if cuurLine is null, it means we have read all characer from the urls.txt file.
            //that's why we need to break the loop
            if(currLine==null) break;
            inputUrls.add(currLine);
            //creating object of hashMap<> for every url present in urls.txt.
            wordsFreq.add(new HashMap<>());
        }
        //closing the Buffered Reader
        bufferedReader.close();
        
        //====================================================================================================================
        //taking input words from words.url.
        //recreate File object to perform operations on words.txt file.
        inputFile=new File("E:\\Work\\java work\\Module2\\src\\word_count\\words.txt");
        
        //recreate buffered reader object to read content of words.txt file.
        bufferedReader=new BufferedReader(new FileReader(inputFile));
        while(true){
            String temp=bufferedReader.readLine();
            //if cuurLine is null, it means we have read all characer from the words.txt file.
            //that's why we need to break the loop
            if(temp==null) break;
            //adding words in inputWords ArrayList.
            inputWords.add(temp);
        }
        // clossing the BufferedReader
        bufferedReader.close();
        
        //====================================================================================================================
        for(int i=0;i<inputUrls.size();i++){
            //single url from ArrayList of urls
            String url=inputUrls.get(i);
            
            //-----------------------------------------------------------------------------------------------------------
            //FILTERING
            //created an object of Document class store all content of url fetched using Jsoup
            Document newdoc = Jsoup.connect(url).get();
            //we need only text from whole document.
            String text = newdoc.body().text();
            //we don't need anything other than alphabates(small and capital).
            //that's why we repalce all other than alphabates from empty space
            text = text.replaceAll("[^a-zA-Z\\w]", " ");
            
            //fetchedWords is an array of String which store all words seperated by an empty space. 
            String fetchedWords[]=text.split(" ");

            //-----------------------------------------------------------------------------------------------------------
            //SEARCHING
            //iterating over input words
            for(String word_to_Search : inputWords){
                wordsFreq.get(i).put(word_to_Search,0);
                //iterating over ords fetched from url
                for(String fetchedWord : fetchedWords){
                    //if we find the exact same word that we want ro search then
                    //fetch the specific hashMap from wordsFreq ArrayLits and increase the 
                    //frequency of the word_to_search by 1
                    if(word_to_Search.equalsIgnoreCase(fetchedWord)){
                        wordsFreq.get(i).put(word_to_Search,wordsFreq.get(i).get(word_to_Search)+1);
                    }
                }
            }
            //-----------------------------------------------------------------------------------------------------------
            //Sorting the map on the basis of values(frequencies) in decreasing order
            List<Map.Entry<String,Integer>> list=new ArrayList<>(wordsFreq.get(i).entrySet());
            Collections.sort(list,new Comparator<Map.Entry<String,Integer>>(){
                @Override
                public int compare(Map.Entry<String,Integer> m1,Map.Entry<String,Integer> m2){
                    return m2.getValue()-m1.getValue();
                }
            });
            //printing the first 3 highest frequency words.
            System.out.println(inputUrls.get(i));
            for(int j=0;j<3;j++){
                System.out.println(list.get(j).getKey()+" = "+list.get(j).getValue());
            }
            System.out.println();
            System.out.println("===============");
            System.out.println();
        }
        //==================================================================================================================
        
        
        
        //merging all maps
        //merging all maps input into the first map (at index 0)
        for(int i=1;i<wordsFreq.size();i++){
            wordsFreq.get(i).forEach((key, value) ->
                    wordsFreq.get(0).merge(key, value, (v1, v2) -> v1 + v2) );
        }
        
        //printing merged output for every url
        System.out.println("merged output of all urls");
        for(Map.Entry<String,Integer> entry: wordsFreq.get(0).entrySet()){
            System.out.println(entry.getKey()+" = "+entry.getValue());
        }
        
    }
}
