// Import necessary packages
import fs from "fs";
import cheerio from "cheerio";
import axios from "axios";

// Define file paths for URLs and words
const urlsPath = 'urls.txt'; 
const wordsPath = 'words.txt'; 

// Initialize arrays for URLs and words
let words=[];
let urls=[];

try { 
    // Read URLs from file
    urls = fs.readFileSync(urlsPath, 'utf-8').split('\n').filter(url => url.trim() !== ''); 
} catch(error) {
    // Handle error reading URLs from file
    console.log(error);
}

try {
    // Read words from file
    words = fs.readFileSync(wordsPath, 'utf-8').split('\n').filter(word => word.trim() !== ''); 
} catch(error) {
    // Handle error reading words from file
    console.log(error);
}

// Initialize an empty object to store the count of each word
const wordCounts = {}; 

// Define function to count occurrences of a word in a string
function countOccurrences(string, word) {
  const regex = new RegExp(word, 'gi');
  return (string.match(regex) || []).length;
}

// Define an asynchronous main function to perform the web scraping and word counting
const main = async () => {
  return new Promise(async (resolve, reject) => {
    // Check that there are URLs and words to process
    if (urls.length == 0) {
      reject("urls file is empty");
      return;
    }
    if (words.length == 0) {
      reject("words file is empty");
      return;
    }
    // Loop through each URL and scrape its text content
    for (const url of urls) {
      const body = await axios.get(url);
      const $ = cheerio.load(body.data);
      const text = $('body').text().toLowerCase();
      const counts = {};
      // Loop through each word and count its occurrences in the scraped text
      for (const word of words) {
        const woords = word.replace(/\r*/g, '');
        const count = countOccurrences(text, woords.toLowerCase());
        counts[woords] = count;
        // If the word hasn't been seen before, add it to the total word count
        if (!wordCounts[woords]) {
          wordCounts[woords] = count;
        } else {
          // If the word has been seen before, add its count to the total count
          wordCounts[woords] += count;
        }
      };
      // Sort the word counts in descending order and print the top three words for the URL
      const sortedCounts = Object.entries(counts).sort((value1, value2) => value2[1] - value1[1]);
      const topWords = sortedCounts.slice(0, 3).map(entry => entry[0] + ' - ' + entry[1]);
      console.log(url + '\n' + topWords.join('\n') + '\n');
    };
    resolve(wordCounts);
  });
}

// Call the main function and store the result in a variable
const data = await main();

// Sort the total word counts in descending order and print them
const sortedWordCounts = Object.entries(data).sort((value1, value2) => value2[1] - value1[1]);
console.log('Total word counts:\n');
sortedWordCounts.forEach(entry => console.log(entry[0] + ' - ' + entry[1]));
