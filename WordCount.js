const fileHandler = require("fs") // to read data from files 
const axios = require("axios"); // to fetch data from a site
const { htmlToText } = require("html-to-text"); // to convert html of the site to text

const urlsArray = fileHandler.readFileSync("urls.txt", "utf-8").split("\n"); //get urls from urls.txt file
const wordsArray = fileHandler.readFileSync("words.txt", "utf-8").split(","); //get words from words.txt file

let wordsCountForOneUrl = new Map(); //map to count words frequencies on one site
let wordsCountForAllUrls = new Map(); //map to count words frequencies across all urls

const updatefrequencyForAUrl = (wordFromFile) => {

  return function innerFunction(wordsFromUrl) {
    const matchedWords = wordsFromUrl.filter((word) => {

      const regExp = new RegExp(`${wordFromFile}`, 'gi');
      return (
        regExp.test(word)
      )
    })

    if (matchedWords == null) {
      wordsCountForOneUrl=new Map([...wordsCountForOneUrl, [wordFromFile,0]])
    } else {
      const wordCount = matchedWords.length //total count of the word in the site
      wordsCountForOneUrl=new Map([...wordsCountForOneUrl, [wordFromFile,wordCount]])

      //updating count of the word in the map which stores word total count
      if (wordsCountForAllUrls.has(wordFromFile)) {
        const oldCount = wordsCountForAllUrls.get(wordFromFile)
        wordsCountForAllUrls=new Map([...wordsCountForAllUrls, [wordFromFile,oldCount+wordCount]])
      }
      else {
        wordsCountForAllUrls=new Map([...wordsCountForAllUrls, [wordFromFile, wordCount]])
      }
    }
  }



}

const sortMapAndReturnTopThreeWords = () => {
  //sorting in descending order of words frequencies and then taking top 3 frequencies words
  wordsCountForOneUrl = new Map([...wordsCountForOneUrl.entries()].sort(
    function (a, b) {
      return b[1] - a[1]
    }
  ).slice(0, 3))
}

const WordCount = async () => {
  for (let index = 0; index < urlsArray.length; index++) {
    let url = urlsArray[index]

    await axios.get(url).then((response) => { //using axios to fetch url data 
      let html = response.data; //html content of the site
      let wordsFromUrl = htmlToText(html).split("\n").join(" ").split(" "); //html to text

      wordsCountForOneUrl = new Map();

      for (let index = 0; index < wordsArray.length; index++) {
        let wordFromFile = wordsArray[index]
        updatefrequencyForAUrl(wordFromFile)(wordsFromUrl)
      }

      sortMapAndReturnTopThreeWords()

      console.log("Top 3 frequency words for url- " + url + "\n");

      for (let [key, value] of wordsCountForOneUrl) {
        console.log(key);
        console.log(value);
      }
      console.log("-----------------------\n")

    });

  }


  console.log("Frequency of all words across all urls\n");

  wordsCountForAllUrls.forEach((value, key) => {
    console.log(key)
    console.log(value)
  })
}

WordCount()



















