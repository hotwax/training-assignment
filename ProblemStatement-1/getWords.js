import fs from "fs";

//This function reads the contents of Words.txt file, 
export const getWords = () => {
  const wordsList = new Map();
  return new Promise((resolve,reject)=>{
    try {
      const wordsData = fs.readFileSync("ProblemStatement-1/Words.txt"); //read Words.txt file
      const fileData = wordsData.toString();
      const words = fileData.split(/\s+/); //split the file content by whitespace
      const filteredWords = words.filter((word) => word.trim() !== ""); //remove empty strings and whitespace from the file content
      for (const word of filteredWords) {
        const trimmedWord = word.trim(); //trim leading and trailing whitespaces from the word
        wordsList.set(trimmedWord, 0); //add the trimmed word to the map with 0 count
      }
      resolve(wordsList); //resolve the promise with the map
    } catch (error) {
      reject(error); //reject the promise if there is an error
    }
  })
};