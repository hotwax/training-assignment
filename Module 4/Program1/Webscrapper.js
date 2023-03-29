// Importing packages required. 
const fs = require('fs');
const request = require('request');
const cheerio = require('cheerio');

// Read the URLs and words from the input files
const urlFile = fs.readFileSync('urls.txt', 'utf-8');
const urls = urlFile.trim().split('\n'); // Store each URL in an array
const wordFile = fs.readFileSync('words.txt', 'utf-8');
const words = wordFile.trim().split('\n'); // Store each word in an array

// A function to count the occurrences of each word in the given HTML content
function countWords(htmlContent, words) {
    const $ = cheerio.load(htmlContent); // Load the HTML content into Cheerio
    const wordCounts = {}; // Initialize an empty object to store the word counts
    for (let i = 0; i < words.length; i++) {
        const word = words[i]; // Get the current word
        const count = $('body').text().split(word).length - 1; // Count the occurrences of the word in the HTML
        wordCounts[word] = count; // Store the count for the current word in the object
    }
    return wordCounts; // Return the object with the word counts
}

// A function to sort an object by its values in descending order
function sortObject(obj) {
    return Object.entries(obj).sort((a, b) => b[1] - a[1]); // Use the Object.entries() method to convert the object into an array of [key, value] pairs, sort the array by the values in descending order, and return the sorted array
}

// A function to print the top 3 words and their counts for a given URL
function printTopWords(url, wordCounts) {
    console.log('${ url }\n');
    // Sort the word counts for the URL in descending order
    const sortedWords = sortObject(wordCounts);
    // Print the top 3 words and their counts
    for (let i = 0; i < 3 && i < sortedWords.length; i++) {
        const word = sortedWords[i][0];
        const count = sortedWords[i][1];
        console.log('${ word } - ${ count }');
    }
    console.log('');
}

// A function to get the word counts for all URLs and all words
function getWordCounts(urls, words) {
    // Initialize an object to keep track of the total counts of all words across all URLs
    const allWordCounts = {};
    // If no URLs are provided, print an error message
    if (urls.length == 0) {
        console.log("No URLs provided.");
    }
    // Loop through each URL in the array of URLs
    for (let i = 0; i < urls.length; i++) {
        const url = urls[i];
        // Make a request to the URL to get its HTML content
        request(url, (error, response, body) => {
            // If there are no errors and the response status code is 200 (OK), count the occurrences of each word in the HTML content
            if (!error && response.statusCode == 200) {
                const wordCounts = countWords(body, words);
                // Print the top 3 words and their counts for the URL
                printTopWords(url, wordCounts);
                // Update the total counts for each word across all URLs
                for (const word in wordCounts) {
                    if (!allWordCounts[word]) {
                        allWordCounts[word] = 0;
                    }
                    allWordCounts[word] += wordCounts[word];
                }
                // If this is the last URL in the array, print the total counts for all words across all URLs
                if (i === urls.length - 1) {
                    console.log('Total Counts\n');
                    // Sort the total counts in descending order
                    const sortedWords = sortObject(allWordCounts);
                    // Print the total counts for each word
                    for (let i = 0; i < sortedWords.length; i++) {
                        const word = sortedWords[i][0];
                        const count = sortedWords[i][1];
                        console.log('${ word } - ${ count }');
                    }
                }
            }
        });
    }
}