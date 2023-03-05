const fetch = require("node-fetch");
const cheerio = require("cheerio");
const fs = require("fs");
const axios = require("axios");

let urls = [];
let words = [];
let words_count = {};

// Read words from file
function readWords() {
  words = fs.readFileSync("words.txt", "utf-8").split(",");
  for (const word of words) {
    words_count[word] = 0
  }
}

// Read urls from file
function readUrls() {
  urls = fs.readFileSync("urls.txt", "utf-8").split(",");
}

// Get text data from webpage
async function calculateOccurance() {
  try {
    for (const url of urls) {
      console.log(url);
      console.log();
      const response = await axios.get(url);
      const $ = cheerio.load(response.data);
      const text = $("body")
        .text()
        .toLowerCase()
        .replace(/[^a-zA-Z ]/g, "")
        .split(" ");
      const wordCountMap = {};
      for (const word of words) {
        const count = text.filter((w) => w == word).length;
        if (count > 0) {
          wordCountMap[word] = count;
          words_count[word] += count;
        }
      }
      console.log(wordCountMap);
      console.log();
    }
    console.log("Total Occurance");
    console.log()
    console.log(words_count);
    console.log();
  } catch (err) {
    console.log(err);
  }
}

readUrls();
readWords();
console.log();
calculateOccurance();
