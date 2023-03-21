import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class WebContent 
{
    // getting the text from website using the url
    String parseUrl(String url) 
    {
        String content="";
        try 
        {
            Document document=Jsoup.connect(url).get();
            content = document.body().text();
            content = content.replaceAll("[^a-zA-Z\\w]", " ");
        }catch(Exception e) 
        {
            System.out.println(e.getMessage());
        }
        // return the content
        return content;
    }
    
    // Function to count the frequency of the given word
    int countWordOccurence(String word, String urlContent) 
    {
        int wordCount=0;
        String contentList[]=urlContent.split(" ");
        for(String str:contentList) if(str.equalsIgnoreCase(word)) wordCount++;
        return wordCount;
    }

    // Sort the Map by Value
    public static Map<String,Integer> sortByValue(Map<String,Integer> hm) 
    {
        List<Map.Entry<String,Integer>> lists=new LinkedList<Map.Entry<String,Integer>>(hm.entrySet());
        // Sort the lists
        Collections.sort(lists,
                new Comparator<Map.Entry<String,Integer>>() {
                    public int compare(
                            Map.Entry<String,Integer> o1,
                            Map.Entry<String,Integer> o2) {
                        return (o2.getValue()).compareTo(o1.getValue());
                    }
                });
        // put data from sorted list to hashmap
        Map<String,Integer> tmp=new LinkedHashMap<>();
        for(Map.Entry<String,Integer> aa:lists)  tmp.put(aa.getKey(),aa.getValue());
        return tmp;
    }

};

class eg1psp 
{
    public static void main(String gg[]) 
    {
        try 
        {
            // Get URLS from the text file
            ArrayList<String> urls=new ArrayList<>();
            Scanner sc=new Scanner(new File("E:\\Module2\\problem1\\urls.txt"));
            while(sc.hasNext()) urls.add(sc.next());

            // Get words from the text file
            ArrayList<String> words=new ArrayList<>();
            sc=new Scanner(new File("E:\\Module2\\problem1\\words.txt"));
            while(sc.hasNext()) words.add(sc.next());
            
            // Find words in URL
            // Creating object of Class WebContent
            WebContent webContent=new WebContent();

            // Map to store the word as key and its frequency as value
            Map<String,Integer> allWordsFrequencyMap=new TreeMap<>();

            for (String url : urls) 
            {
                // Storing words for each url
                Map<String, Integer> wordFrequencyMap=new TreeMap<>();
                System.out.println("URL --> "+url);
                
                // Calling the pass url method
                String urlContent = webContent.parseUrl(url);
                for(String word:words) 
                {
                    // Getting the frequency of the word in the url
                    int wordOccurence = webContent.countWordOccurence(word,urlContent);
                    // Adding the word as key and frequency as value
                    wordFrequencyMap.put(word,wordOccurence);
                }
                // Sort map in non-ascending order
                wordFrequencyMap=WebContent.sortByValue(wordFrequencyMap);
                int counter=0;
                // Printing the top three words that occur the most
                for(Map.Entry<String,Integer> m:wordFrequencyMap.entrySet())
                {
                    if(counter++<3) 
                    {
                        System.out.println(m.getKey()+" "+m.getValue());
                        String key=(String)m.getKey();
                        int value=(int)m.getValue();
                        if(allWordsFrequencyMap.get(key)!=null) value+=allWordsFrequencyMap.get(key);
                        allWordsFrequencyMap.put(key, value);
                    } 
                    else break;
                }
                System.out.println();
                System.out.println("**********************");
            }
            System.out.println("------------------------------------");
            allWordsFrequencyMap=WebContent.sortByValue(allWordsFrequencyMap);
            for (Map.Entry<String,Integer> m:allWordsFrequencyMap.entrySet()) System.out.println(m.getKey()+" "+m.getValue());
            System.out.println("-------------------------------------");
        } 
        catch(Exception e) 
        {
            System.out.println(e);
        }
    }
} 