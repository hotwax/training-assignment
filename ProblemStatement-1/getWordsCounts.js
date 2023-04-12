// import necessary dependencies
import axios from "axios";
import CheerioAPI from "cheerio";
import { getWords } from "./getWords.js";
import { getUrls } from "./getUrls.js";

// declare an async function to get the count of words in URLs
export const getWordsCount = async () => {
  // initialize flags to check if files exist
  let isWordsFileExist = false;
  let isUrlsFileExist = false;
  // declare a variable to store the list of URLs
  let urlList;
  try {
    urlList = await getUrls();
    isUrlsFileExist = true;
  } catch (error) {
    console.log(error.message);
  }
  // declare a variable to store the list of words
  let words;
  try {
    words = await getWords();
    isWordsFileExist = true;
  } catch (error) {
    console.log(error.message);
  }
  // check if the URLs file exists
  if (!isUrlsFileExist) {
    console.log("urls file is not found");
    words = undefined;
    return words;
  }
  // check if the words file exists
  if (!isWordsFileExist) {
    console.log("words file is not found");
    return words;
  }
  // check if the words file is empty
  if (words.size === 0) {
    console.log("Words.txt file is empty");
    words = undefined;
    return words;
  }
  // check if the URLs file is empty
  if (urlList.size === 0) {
    console.log("Urls.txt file is empty");
    words = undefined;
    return words;
  }
  // if both the words and URLs files exist and are not empty, proceed to count the words in each URL
  if (words.size !== 0 && urlList.size !== 0) {
    const getWordsWithCount = new Map(words);
    try {
      // loop through each URL in the list of URLs
      for (const url of urlList) {
        // create a copy of the map of words with count for each URL
        const getWordCountInUrl = new Map(getWordsWithCount);

        // get the HTML content of the URL using axios
        const scrapWebData = await axios.get(url);

        // load the HTML content in Cheerio
        const loadInCheerio = CheerioAPI.load(scrapWebData.data);
        let count = 0;

        // remove the text of script and noscript tags from the HTML content
        loadInCheerio("script").remove();
        loadInCheerio("noscript").remove();
        // get the text of the body tag and split it into an array of words
        const word = loadInCheerio("body").text().split(/\s+/);
        word.forEach((element) => {
          // if the word is in the map of words with count, increment its count in the map for each URL and the overall map
          if (words.has(element)) {
            words.set(element, words.get(element) + 1);
            getWordCountInUrl.set(element, getWordCountInUrl.get(element) + 1);
          }
        });

        // Sort the words by their count in descending order
        const sortedWordCount = new Map(
          [...getWordCountInUrl.entries()].sort(
            (firstValue, secondValue) => secondValue[1] - firstValue[1]
          )
        );

        // Display the top 3 most frequent words in the url
        console.log(url);
        for (const wordCount of sortedWordCount) {
          if (count == 3) {
            break;
          }
          console.log(wordCount[0] + "->" + wordCount[1]);
          count++;
        }
        console.log();
      }
    } catch (error) {
      console.log(error.message);
      console.log(`incorrect url`);
      return;
    }
  }

  // Return the list of words with number occurance
  return words;
};
