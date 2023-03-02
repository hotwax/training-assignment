const fetch = require("node-fetch");
const cheerio = require("cheerio");
const fs = require('fs');
const readline = require('readline');


const wordCountMap = {};
let urls = [];
let words = [];

// Function to get the raw data
async function getRawData(URL) {
  return fetch(URL)
    .then((response) => response.text())
    .then((data) => {
      return data;
    });
};

// Get text data from webpage
function updateMap(url) {
  const html = getRawData(url);
  const parsed = cheerio.load(html);
  const finalText = parsed.text();
  words.forEach((word)=>{
    countOccurrences(finalText, word);
  });
  
};

function readWords(){
  words = fs.readFileSync("words.txt", "utf-8").split(",");
}

function readUrls(){
    urls = fs.readFileSync("urls.txt", "utf-8").split(",");
}


// Count occurance of word on webpage
function countOccurrences(str,word){
    let a = str.split(" ");
    let count = 0;
    for (let i = 0; i < a.length; i++)
      {
      if (word==(a[i]))
          count++;
          
    }
    console.log(count)
    wordCountMap[word] += count
    console.log(wordCountMap)
}


readUrls()
readWords()
urls.forEach((url)=>{
    updateMap(url);
});






// const data = readFile('urls.txt');
// console.log(data)
// data.forEach(function (url) {
//   console.log(url)
//   // readFile('words.txt').forEach((word) => {
//   //   console.log(word)
//   // });
// });



