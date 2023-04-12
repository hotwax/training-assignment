import { getWordsCount } from "./getWordsCounts.js";

//declare a function that print the all words with counts sorted by number of occurance of words in each webpage
const getWordCountInAllUrls = async () => {
  try {
    //get words count of all the urls
    const allWordsWithCount = await getWordsCount();
    if (allWordsWithCount!=undefined && allWordsWithCount.size !== 0) {
      
      //sort the allWordsWithCount by counts in Descending order 
      const allSortedWordWithCount = new Map(
        [...allWordsWithCount.entries()].sort(
          (firstValue, secondValue) => secondValue[1] - firstValue[1]
        )
      );
      console.log("==========================================================");
      for (const wordWithCount of allSortedWordWithCount) {
        console.log(wordWithCount[0] + "->" + wordWithCount[1]);
      }
    }
  } catch (error) {
    console.log(error);
  }
};

//call the getWordCountInAllUrls function 
getWordCountInAllUrls();