import axios from 'axios';
import fs from 'fs';
import * as cheerio from 'cheerio';

// getting urls from urls.txt file
const urls = [
  ...new Set(fs.readFileSync('urls.txt', 'utf8').trim().split('\r\n')),
];
// checking for empty urls
!urls.length ? console.log('No URLs Provided') && process.exit() : null;

//getting words from words.txt file
const words = [
  ...new Set(
    fs.readFileSync('words.txt', 'utf-8').trim().toLowerCase().split('\r\n')
  ),
];
// checking for empty words
console.log(words.length);
!words ? console.log('No Words Provided') || process.exit() : null;
// creating a map to store the word frequencies
var wordFreq = new Map();
var urlWordCount = new Map();
// setting the initial value of the word frequencies to 0
const fetchURL = async (urls) => {
  for (let i = 0; i < urls.length; i++) {
    // using axios to fetch url data
    await axios
      .get(urls[i])
      .then((response) => {
        // using cheerio to parse the html data
        const $ = cheerio.load(response.data);
        const urlData = $('body').text().toLowerCase();
        // creating a map to store the word frequencies for each url
        urlWordCount = new Map();
        // looping through the words array to get the word frequencies
        words.map((word) => {
          // setting the word frequencies for each url
          urlWordCount.set(word, urlData.split(word).length - 1);
          // setting the word frequencies for all the urls
          wordFreq.set(
            word,
            (wordFreq.get(word) || 0) + urlData.split(word).length - 1
          );
        });
        // printing the url
        console.log(urls[i]);
        // sorting the map in descending order of word frequencies and then taking top 3 frequencies words
        const wordCount = new Map(
          [...urlWordCount.entries()].sort((a, b) => b[1] - a[1]).slice(0, 3)
        );
        // printing the word frequencies for each url
        console.log(wordCount);
      })
      .catch((error) => {
        console.log('Invalid URL', error);
      });
  }
};
fetchURL(urls).then(() =>
  // printing the word frequencies for all the urls
  console.log('-----------OUTPUT#2-------------\n', wordFreq)
);
