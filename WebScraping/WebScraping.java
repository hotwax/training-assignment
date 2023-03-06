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

//defining class Getdata
class Getdata
{
   public HashMap<String,Integer> h;
   public ArrayList<String>Words;
   Getdata(ArrayList<String>words){
    this.h=new HashMap<>();
    this.Words=words;
   }

  void data(String Url)throws IOException
  {
  HashMap<String,Integer> map = new HashMap<String,Integer>();


  //storing the words in Map
    for(String s:Words){
        map.put(s,0);
    }

  //Connecting to the web page
  Connection conn = Jsoup.connect(Url);
  //executing the get request
  Document doc = conn.get();
  //Retrieving the content of the web page
  String result = doc.body().text();
  //Removing non-Alphnumeric character
  result= result.replaceAll("[^a-zA-Z ' 's]", "");
  String str[]= result.split(" ");
    // counting the frequency of each word in the HashMap
  for(String a:str){
        if(map.containsKey(a))
        {
            map.put(a, map.get(a)+1);
        }
  }
    List<Map.Entry<String,Integer>> sortedList = new ArrayList<>(map.entrySet());
    Collections.sort(sortedList, new Comparator<Map.Entry<String,Integer>>()
    {
        public int compare(Map.Entry<String,Integer> o1,Map.Entry<String,Integer>o2)
        {
            return o2.getValue().compareTo(o1.getValue());
        }
    });
    int count=0;
    for(Map.Entry<String ,Integer> entry : sortedList){
        //look for top 3 word in each web page
        if(count < 3){
            System.out.println(entry.getKey()+" " + "-" + " "+ entry.getValue());
        }
        if(h.containsKey(entry.getKey())){
            h.put(entry.getKey(),entry.getValue()+ h.get(entry.getKey()));
        }
        else{
            h.put(entry.getKey(),entry.getValue());
        }
        count++;
    }
  }  
}

public class WebScraping{
   public static void main(String args[]) throws IOException{
    /*Array list for Word.txt*/
    ArrayList<String> Word = new ArrayList<String>();
    //reading Word.txt
    try {
        File myObj1 = new File("Word.txt");
        Scanner myReader = new Scanner(myObj1);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          String split[]= data.split(" ");
          for(String singleword: split)
          {    
           Word.add(singleword);
        }
    }
    myReader.close();
    }catch (FileNotFoundException e) {
        System.out.println("File Word.txt not found.");
        e.printStackTrace();
    }
    Getdata get=new Getdata(Word);

    ArrayList<String> Urls = new ArrayList<String>();
    //reding file Url.txt
    try {
        File myObj = new File("Url.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
           Urls.add(data);
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("File Url.txt not found.");
        e.printStackTrace();
    }

    System.out.println("========");
    System.out.println();
    //Iterate over the Urls ArrayList and process each URL
    for(int index=0;index<Urls.size();index++){
        System.out.println(Urls.get(index));
        get.data(Urls.get(index));
        System.out.println();
    }
    List<Map.Entry<String,Integer>> sortedList = new ArrayList<>(get.h.entrySet());
    Collections.sort(sortedList, new Comparator<Map.Entry<String,Integer>>()
    {
        public int compare(Map.Entry<String,Integer> o1,Map.Entry<String,Integer>o2)
        {
            return o2.getValue().compareTo(o1.getValue());
        }
    });

    for(Map.Entry<String ,Integer> m : sortedList){
        System.out.println(m.getKey()+"-"+m.getValue());
    }
}
}