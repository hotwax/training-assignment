import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
//we will have to set class path 
// set classpath=%classpath%;D:\Programs\jsoup-1.15.3.jar;
class CountFrequency // a class to store count of words and words
{

  String word;
  int freq;

  CountFrequency(String word, int freq) // constructor to initialize 
  {
    this.word = word;
    this.freq = freq;
  }

  public int hashCode() //overriding hashCode
  {
    return word.hashCode();
  }

  //if hashCode is same then equals method will be called and here we will update frequency for final print

  public boolean equals(Object o) //overriding equals method
  {
    CountFrequency count = (CountFrequency) o;
    count.freq = freq + count.freq; //if word was same then we will update older and newer frequency
    return true;
  }

  public String toString() //overriding toString method
  {
    return "{Word =" + word + ", frequency =" + freq + "}\n";
  }

}
class WebScrapping // a class to get web page and count certain words
{

  String load(String url) throws Exception // method to load data from web file
  {
	  String text ="";
    try {
      Document doc = Jsoup.connect(url).get();
       text = doc.body().text();
      text = text.replaceAll("[^a-zA-Z\\w]", " ");
    } catch (IllegalArgumentException e) {
      System.out.println("Something wrong with URL");
    }
    return text;
  }

  int count(String word, String text) throws Exception //method to count frequency of a word
  {
    int count = 0;
    String arr[] = text.split(" ");
    for (String str: arr) {
      if (str.equalsIgnoreCase(word)) count++;
    }
    return count;
  }

}
class Main {
  public static void main(String ar[]) throws Exception {
    HashSet < CountFrequency > set = new HashSet < > ();
    WebScrapping ws = new WebScrapping();

    //storing all urls into a ArrayList
	try{
    BufferedReader br = new BufferedReader(new FileReader("url.txt"));
    String stringURL = br.readLine();
    ArrayList < String > url = new ArrayList <> ();
    while (stringURL != null) {
      try {
        url.add(stringURL);
        stringURL = br.readLine();
      } catch(IndexOutOfBoundsException e)
	  {
		  System.out.println("No urls found");
	  }
    }

    //storing all words to an arraylist
    BufferedReader br1 = new BufferedReader(new FileReader("word.txt"));
    String stringWORD = br1.readLine();
    ArrayList < String > words = new ArrayList < > ();
    while (stringWORD != null) {
      try {
        words.add(stringWORD);
        stringWORD = br1.readLine();
      }catch(IndexOutOfBoundsException e)
	  {
		  System.out.println("No urls found");
	  }
    }

    int url_num = 1; //variable to give url number

    for (String str: url) {
      ArrayList < CountFrequency > alist = new ArrayList < > ();
      System.out.println("\nURL : " + url_num);
      url_num++;

      for (String str1: words) {
        int count1 = ws.count(str1, ws.load(str));
        alist.add(new CountFrequency(str1, count1)); //adding to arraylist
        set.add(new CountFrequency(str1, count1)); // adding to hashSet
      }

      alist.sort((object1, object2) -> object2.freq - object1.freq); //sorting the arraylist

      for (int index = 0; index < 3; index++) //printing the first 3 
        System.out.print(alist.get(index));
    }

    //storing the hashSet to TreeSet to sort it descending
    System.out.println("\nComplete .......");

    TreeSet < CountFrequency > treeSet = new TreeSet < CountFrequency > ((object1, object2) -> object2.freq - object1.freq);
    for (CountFrequency countfrequency: set)
      treeSet.add(countfrequency);

    System.out.println(treeSet);

	} catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
	  catch(IndexOutOfBoundsException e){
		          System.out.println("Empty file");
	  }
  }
}