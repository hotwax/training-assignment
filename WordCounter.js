const fileHandler = require("fs")
const axios = require("axios");
const { htmlToText } = require("html-to-text");

const urlsArray = fileHandler.readFileSync("urls.txt", "utf-8").split("\n");
const wordsArray = fileHandler.readFileSync("words.txt", "utf-8").split("\n");

const wordsFrequencyForAllUrls = new Map();

urlsArray.forEach((url) => {
  axios.get(url).then((response) => {
    let html = response.data;
    let wordsFromUrl = htmlToText(html).split("\n").join(" ");

    // console.log(wordsFromUrl);


    let wordsFrequencyForOneUrl = new Map();

    wordsArray.forEach(async (wordFromFile) => {
      // console.log(wordFromFile)
      const regExp = new RegExp(`${wordFromFile}`, 'gi');
      // console.log(wordsFromUrl.match(regExp))
      const matchedWords = wordsFromUrl.match(regExp)

      if (matchedWords == null) {
        wordsFrequencyForOneUrl.set(wordFromFile, 0)
      } else {
        const wordFrequency = matchedWords.length
        wordsFrequencyForOneUrl.set(wordFromFile, wordFrequency)

        if (wordsFrequencyForAllUrls.has(wordFromFile)) {
          const oldFrequency = wordsFrequencyForAllUrls.get(wordFromFile)
          wordsFrequencyForAllUrls.set(wordFromFile, oldFrequency + wordFrequency);

          // console.log("oldFrequency " + oldFrequency);
          // console.log("newFrequency " + wordsFrequencyForAllUrls.get(wordFromFile));
        }
        else {
          wordsFrequencyForAllUrls.set(wordFromFile, wordFrequency)
        }

        // console.log(wordFromFile,wordsFrequencyForAllUrls.get(wordFromFile));
      }
    })

    console.log("Top 3 frequency words for url- " + url);

    wordsFrequencyForOneUrl = new Map([...wordsFrequencyForOneUrl.entries()].sort(
      function (a, b) {
        return b[1] - a[1]
      }
    ).slice(0, 3))

    for (let [key, value] of wordsFrequencyForOneUrl) {
      console.log(key);
      console.log(value);
    }
    console.log("-----------------------")

  });
});

console.log("Frequency of all words across all urls");

wordsFrequencyForAllUrls.forEach((value, key) => {
  console.log(key)
  console.log(value)
})

















