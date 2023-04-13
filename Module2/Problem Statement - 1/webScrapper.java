import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class webScrapper {
    public static Map<String, Integer> allWords = new HashMap<>();
    /*
     * This function is used for calculating the number of words in the given array.
     * The function takes two arguments word list and a word which need to be count.
     * Then retuen the number of occurance of that perticular word in that array.
     */
    static private int countWords(String[] wordList, String word){
        int count = 0;
        for(String s:wordList){
            if(s.strip().equals(word)){
                count+=1;
            }
        }
        return count;
    }

    static private List<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> dic){
 
        List<Map.Entry<String, Integer>> list = new LinkedList<>(dic.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        return list;
    }

    /*
     * This function is used for connecting to the website and scrap the data.
     * The function accepts two argument link of tghe website and linked list of words.
     */
    static private void Scrapper(String link, LinkedList<String> listOfWords) throws IOException {
        Document ds = Jsoup.connect(link).get();
        String text = ds.body().text();
        String [] arr = text.split(" ");

        Map<String, Integer> dic = new HashMap<>();

        for(String word: listOfWords){
            int count = countWords(arr,word );
            dic.put(word, count);
        }

        /*
         * Calling sortByValue function which will sort the dictionary and return a list 
         * containing top three words whith maximum frequency. 
         */
        List<Map.Entry<String, Integer>> sortedData = sortByValue(dic).subList(0, 3);

        
        sortedData.forEach(value->{
            System.out.println(value.getKey()+" - "+value.getValue());


            try{
                int words =  allWords.get(value.getKey());
                if(words < value.getValue() ){
                    allWords.put(value.getKey(), value.getValue());
                }
            }catch(Exception e){
                allWords.put(value.getKey(), value.getValue());
            }
            
            
        });
        System.out.println("\n");
        return ;
    }

    
    public static void main(String[] args){
        Scanner urlSc=null;

        try{

            //Creating a file pointer to the urls.txt file from local directory
            File fp = new File("./urls.txt");

            //Creating an object of Scanner class for reading the url file
            urlSc  = new Scanner(fp);


        }catch(FileNotFoundException e ){
            System.out.println("Url file not found.");
            return;

        }
        

        
        LinkedList<String> list = new LinkedList<String>();

        //Creating a file pointer to the words.txt file from local directory
        try{
            File word = new File("./word.txt");
            Scanner wordSc = new Scanner(word);
            if(!wordSc.hasNextLine()){
                System.out.println("Words are not present inside word.txt file");
                return;
            }
            while(wordSc.hasNextLine()){
                list.add(wordSc.nextLine());
            }
            wordSc.close();
        }catch(FileNotFoundException e){
            System.out.println("word file not found.");
            urlSc.close();
            return;
        }

        if(!urlSc.hasNextLine()){
            System.out.println("Url.txt file does not have any urls.");
        }

        while(urlSc.hasNextLine()){
            String link = urlSc.nextLine();
            System.out.println(link);
            try{
                Scrapper(link, list);
            }catch(Exception e){
                System.out.println("Invalid url ");
            }
        }
        
        List<Map.Entry<String, Integer>> finalOutput = sortByValue(allWords);

        /*
         * Printing final output after merging all data.
         */
        System.out.println("\nFinal Output....");

        finalOutput.forEach(s->{
            System.out.println(s.getKey()+" - "+s.getValue());
        });

       
        urlSc.close();
    }
}