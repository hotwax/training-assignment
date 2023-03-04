const fs = require('fs');
const request = require('request');
const cheerio = require('cheerio');

// Read the URLs and words from the input files
const urlFile = fs.readFileSync('urls.txt', 'utf-8');
const urls = urlFile.trim().split('\n');
const wordFile = fs.readFileSync('words.txt', 'utf-8');
const words = wordFile.trim().split('\n');



// Define a function to count the occurrences of each word in the given HTML content
function countWords(htmlContent, words) {
    const $ = cheerio.load(htmlContent);
    const wordCounts = {};
    for (let i = 0; i < words.length; i++) {
        const word = words[i];
        const count = $('body').text().split(word).length - 1;
        wordCounts[word] = count;
    }
    return wordCounts;
}

// Define a function to sort an object by its values in descending order
function sortObject(obj) {
    return Object.entries(obj).sort((a, b) => b[1] - a[1]);
}

// Define a function to print the top 3 words and their counts for a given URL
function printTopWords(url, wordCounts) {
    console.log(`${url}\n`);
    const sortedWords = sortObject(wordCounts);
    for (let i = 0; i < 3 && i < sortedWords.length; i++) {
        const word = sortedWords[i][0];
        const count = sortedWords[i][1];
        console.log(`${word} - ${count}`);
    }
    console.log('');
}

// Define a function to get the word counts for all URLs and all words
function getWordCounts(urls, words) {
    const allWordCounts = {};
    if (urls.length == 0) {
        console.log("No URLs provided.");
    }
    for (let i = 0; i < urls.length; i++) {
        const url = urls[i];
        request(url, (error, response, body) => {
            if (!error && response.statusCode == 200) {
                const wordCounts = countWords(body, words);
                printTopWords(url, wordCounts);
                for (const word in wordCounts) {
                    if (!allWordCounts[word]) {
                        allWordCounts[word] = 0;
                    }
                    allWordCounts[word] += wordCounts[word];
                }
                if (i === urls.length - 1) {
                    console.log('Total Counts\n');
                    const sortedWords = sortObject(allWordCounts);
                    for (let i = 0; i < sortedWords.length; i++) {
                        const word = sortedWords[i][0];
                        const count = sortedWords[i][1];
                        console.log(`${word} - ${count}`);
                    }
                }
            }
        });
    }
}

// Call the main function to get the word counts for all URLs and all words
getWordCounts(urls, words);

