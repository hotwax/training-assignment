// import necessary modules
const axios = require('axios');
const cheerio = require('cheerio');
const fs = require('fs');

// read URLs and words from files and split them into arrays
const urls = fs.readFileSync('urls.txt', 'utf-8').split('\r\n');
const words = fs.readFileSync('words.txt', 'utf-8').split('\r\n');

// create an object to store the cumulative count of each word
const cumulativeCount = {};

// define an async function to process each URL
const processUrl = async (url) => {
  // print the URL being processed
  console.log(`URL: ${url}\n`);
  
  try {
    // use axios to fetch the content of the webpage
    const response = await axios.get(url);
  
    // load the webpage content into Cheerio
    const $ = cheerio.load(response.data);
  
    // extract the text content from the webpage and convert it to lowercase
    const content = $('body').text().toLowerCase();
  
    // create an object to store the count of each word in the webpage content
    const wordCount = {};
  
    // loop through each word in the words array
    words.forEach((word) => {
      // use regex to count the number of occurrences of the word in the content
      const count = (content.match(new RegExp(word, 'g')) || []).length;
  
      // if the word occurs at least once, add it to the wordCount object
      if (count > 0) {
        wordCount[word] = count;
  
        // add the count of the word to the cumulativeCount object
        cumulativeCount[word] = (cumulativeCount[word] || 0) + count;
      }
    });
  
    // sort the wordCount object in descending order of count and store it in sortedCount
    const sortedCount = Object.entries(wordCount).sort((a, b) => b[1] - a[1]);
  
    // print the top 3 words in sortedCount
    console.log('Top 3 words:');
    sortedCount.slice(0, 3).forEach(([word, count]) => {
      console.log(`${word} - ${count}`);
    });
  
    // print the count of each word in sortedCount
    console.log('\nWord count:');
    sortedCount.forEach(([word, count]) => {
      console.log(`${word} - ${count}`);
    });
    console.log('\n');
  } catch (error) {
    // if there is an error, print it to the console
    console.log(error);
  }
};

// define an async function to loop through each URL and process it
const processUrls = async () => {
  for (const url of urls) {
    await processUrl(url);
  }

  // print the cumulative count of each word
  console.log('Cumulative word count:');
  Object.entries(cumulativeCount).forEach(([word, count]) => {
    console.log(`${word} - ${count}`);
  });
};

// call the processUrls function
processUrls();

