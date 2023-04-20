const fs = require('fs');
const axios = require('axios');
const cheerio = require('cheerio');


// Function to fetch HTML content and extract text
  const fetchTextContent = async (url) => {
    try {
      const response = await axios.get(url);
      return  (cheerio.load(response.data))('body').text().replace(/\r/g, '').trim();
    } 
    
    catch (error) {

      if (error.code === 404){
        console.log("404 error, Page not exists: "+url);
        return null;
      }

      else if (error.code === 'ENOTFOUND'){
        console.log("No internet connection: ");
      }

      else
      console.error(`Invalid url: ${url}: ${error}`);
      return null;
    }
  };



// Function to read URLs and words from files, and validate them
function readUrlAndWords(){

  try{

    const urls = fs.readFileSync('urls.txt', 'utf-8').trim().replace(/\r/g, '').split('\n').filter((url) => url !== '');

    
    if (urls.length === 0) {
      console.error('No URLs found in urls.txt');
      return;
    }

    
    const words= fs.readFileSync('words.txt', 'utf-8').trim().replace(/\r/g, '').split('\n').filter((word) => word !== '');
    if (words.length === 0) {
      console.error('No words found in words.txt');
      return;
    }

  // convert urls and words to sets to remove duplicates
    const urlsSet = new Set(urls);
    const wordsSet = new Set(words);  


  // call function to process each URL and show top 3 words, and count overall word count
  getWordsData(urlsSet,wordsSet);

}

catch(err){
  if (err.code === 'ENOENT')
    console.log("File not found: "+err.path);
  else
  console.log(err)
}

}


// Function to count occurrences of words in text content
const countOccurrences = (textContent, words) => {
  const wordCount = {};
  words.forEach((word) => {
    const regex = new RegExp(`\\b${word}\\b`, 'gi');
    const count = (textContent.match(regex) || []).length;
    wordCount[word] = count;
  });
  return wordCount;
};


// Function to process each URL and show top 3 words, and count overall word count
async function getWordsData( urls, words){
// Process each URL
const totalWordCount = {};

for (const url of urls) {
  console.log(`Processing ${url}...`);
  const textContent = await fetchTextContent(url);
  if (textContent) {
    const wordCount = countOccurrences(textContent, words);
    const sortedWords = Object.keys(wordCount).sort((a, b) => wordCount[b] - wordCount[a]);
    console.log(`Top 3 words for ${url}:`);
    c=0;
    sortedWords.forEach((word) => {
      if (c<3)
      // console.log(`${word}: ${wordCount[word]}`);
      console.log(word+": "+wordCount[word]);

      totalWordCount[word] = (totalWordCount[word]||0 ) + wordCount[word];
      c+=1

    });
  }
  console.log('-------------------');
}
showOverallWords(totalWordCount);
}


// Function to show overall word count
function showOverallWords(totalWordCount){
  const sortedWords = Object.keys(totalWordCount).sort((a, b) => totalWordCount[b] - totalWordCount[a]);
  console.log('Total occurrence of each word:');
  sortedWords.forEach((word) => {
    console.log(`${word}: ${totalWordCount[word]}`);
  });}

  
// Function to start the process
readUrlAndWords();