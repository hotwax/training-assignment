// importing required dependencies
import axios from 'axios';
import cheerio from 'cheerio';
import fs from 'fs';

/* scrapData function that reads reads both the file and count the words present in words.txt form url 
present in url.txt and give the top 3 words from each urls and print the total count of words written in word.txt
*/
const scrapData=async()=>{

// read URL from url.txt file and gives error if file not present
fs.readFile('urls.txt', 'utf8', async (err, urldata) => {
  if (err) {
    console.error("url.txt file not found");
    return;
}
//map to store words with their counts
const word=new Map();
//set to store list of urls 
const urls=new Set();
//splitting urls in form of an array
const urlFile = urldata.split(/\r\n/);
urlFile.forEach((element) => {
  urls.add(element);
});

// read words from words.txt file and gives error if file not present
fs.readFile('words.txt', 'utf-8', async (err, worddata) => {
 if (err) {
   console.error("Word.txt file not found");
   return;
 }

//splitting the words in form of array of strings
const words = worddata.split(/\r\n/);
 for(const element of words){
       word.set(element,0);
 }
 const fetchOldOWords =new Map(word);
// Use Axios to fetch the content of the webpage
console.log("=================================================");
for(const url of urls){
  const wordCount = new Map(fetchOldOWords);
  console.log(url);
 const  response= await axios.get(url);
   // Load the HTML content into Cheerio for parsing
   const $ = cheerio.load(response.data);
   // to remove the scripts form the parsed data
   $("script").remove();
   $("noscript").remove();

   // Extract the text content of the webpage Split the text into an array of words
   let data=$("body").text().split(/\s+/);
   
   //checking the words in scrapped data and count them
   const scrappeddata = data;
   for(const element of scrappeddata){
     if(word.has(element)){
       word.set(element,word.get(element)+1);
       wordCount.set(element,wordCount.get(element)+1);
     }
  }
    // sorted the words present in particular url according to their counts
  const sortWords=new Map([...wordCount.entries()].sort((a,b)=>{
      return b[1]-a[1];
     }))
     let count=0; 
    for(const sortWord of sortWords){
        if(count >2){
          break;
        }
        console.log(sortWord[0]+"->"+sortWord[1]);
        count++;
      }
    }
console.log("=================================================");
    const sortTotal = new Map([...word.entries()].sort((a,b)=>{
      return b[1]-a[1];
     }))
      // sorted the total words present in all urls according to their counts
     for(const sortWord of sortTotal){
      console.log(sortWord[0]+"->"+sortWord[1]);
     }
  });
 });
 
}
//calling scrapData function
scrapData();