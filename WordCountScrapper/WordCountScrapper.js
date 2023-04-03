const axios = require('axios');
const cheerio = require('cheerio');
const file = require('fs');

// Condition for if there is No url.txt file 
if (file.existsSync('url.txt') == false) {
  console.log("Url.txt File Not Found");
  return;
}

// Condition for if there is No words.txt file
if (file.existsSync('words.txt') == false) {
  console.log("Words.txt File Not Found");
  return;
}

// Read URLs from urls.txt
const file_urls = file.readFileSync('url.txt', 'utf-8').split('\n').filter(Boolean);

// remove if there is duplicate urls
function removeDuplicates(arr) {
        return arr.filter((item, 
            index) => arr.indexOf(item) === index);
}

const urls = removeDuplicates(file_urls);

// If there is no url in the file or File is Empty 
if (urls.length == 0) {
  console.log("No Url Found");
  return;
}
// Read words from words.txt
const words = file.readFileSync('words.txt', 'utf-8').split(' ').filter(Boolean);

// If there is no words in the file or File is Empty 
if (words.length == 0) {
  console.log("No Words Found");
  return;
}

// Initialize a JavaScript object to store the word counts for All the URL
const totalWordsCount = {};

// Initialise all the Word with count = 0 ;
for (let word of words) 
  totalWordsCount[word] = 0;

// Function to Scrap data from Url and find words from it.
async function wordCount() {
  // Iterate over the URLs and count the occurrences of the specified words
  for (const url of urls) {

    // Create a javascript Object to Store Word and its Count for particular URL
    const wordCountMap = {};

  
    await axios.get(url)
      .then(response => {

        // get All the HTML content 
        const html = response.data;
        const $ = cheerio.load(html); // Parse the Html Content using cheerio
        const text = $('body').text().toLowerCase(); // Get all the text of html body in lowerCase

        const wordsInPage = text.split(/\W+/); // Split the text into Word Array

        for (wordInFile of words) 
        {
          // compare the Words of file and Web using Filter
          const count = wordsInPage.filter(wordInpage => wordInpage.toLowerCase() === wordInFile.toLowerCase()); 
          // Sort Word and Count in object 
          wordCountMap[wordInFile] = count.length;
        }

        console.log(url) ;

        const SortedMap = Object.entries(wordCountMap)
          .sort((element1, element2) => element2[1] - element1[1]) // // Sort the Object according to count in descending order
          .slice(0, 3); // Get the Top 3 words using slice me
       
        for (const [word, count] of SortedMap) {
          // Store words of All the Urls in Object 
          totalWordsCount[word] = totalWordsCount[word] + count;
          console.log(word,count);
        }
      })
      .catch(error => {
        if(error.code === 'ENOTFOUND')
        {
          console.log("No internet Connection");
          process.exit(1);
        }
        else
        {
          console.error("Invalid URL" + " " + url);
        }
      })
  }

  // Wait for all requests to complete and print the results
  await axios.all(urls.map(url => axios.get(url)))
    .then(() => {
      // Print the total number of occurrences for each word
      console.log('Total occurrences for each word:');
      const wordsSortedByTotal = Object.entries(totalWordsCount)
            .sort((element1, element2) => element2[1] - element1[1]);

      for (const [word, count] of wordsSortedByTotal) {
        if (count != 0) {
          console.log(word,count);
        }

      }
    })
    .catch(error => {
      // console.log(error);
    });
}

// Call the wordCount Function
wordCount();