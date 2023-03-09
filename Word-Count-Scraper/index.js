const cheerio = require("cheerio");
const fs = require("fs");
const axios = require("axios");

let urls = [];
let words = [];
let words_count = {};

// Read words from file
function readWords() {
  try {
    words = fs.readFileSync("words.txt", "utf-8").split(",");
    words.map((word) => {
      words_count[word] = 0;
    });
  } catch (err) {
    console.log();
    console.log("Error reading words file");
  }
}

// Read urls from file
function readUrls() {
  try {
    urls = fs.readFileSync("urls.txt", "utf-8").split(",");
  } catch (err) {
    console.log();
    console.log("Error reading urls file");
  }
}

// Get text data from webpage
async function calculateOccurance() {
  const regex = new RegExp(
    "^(https?:\\/\\/)" + // protocol
      "((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|" + // domain name
      "((\\d{1,3}\\.){3}\\d{1,3}))" + // OR ip (v4) address
      "(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*" + // port and path
      "(\\?[;&a-z\\d%_.~+=-]*)?" + // query string
      "(\\#[-a-z\\d_]*)?$",
    "i"
  );
  try {
    for (const url of urls) {
      if (!regex.test(url)) {
        console.log();
        console.log("Invalid url: ", url);
        console.log();
        continue;
      } else {
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
    }
    console.log("Total Occurance");
    console.log();
    console.log(words_count);
    console.log();
    console.log("Sorted map in descending order.");
    console.log();
    const wordCountMap = Object.entries(words_count).sort(
      (a, b) => b[1] - a[1]
    );
    console.log(wordCountMap)
    console.log();
  } catch (err) {
    console.log(err);
  }
}

readUrls();
readWords();
console.log();
if (urls.length != 0 && words.length != 0) {
  calculateOccurance();
} else {
  console.log();
  console.log("Please check the files. Files are not present or may be empty.");
  console.log();
}
