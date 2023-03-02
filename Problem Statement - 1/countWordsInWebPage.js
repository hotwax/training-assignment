import axios from 'axios';
import * as cheerio from 'cheerio';
import fs from 'fs';
// for current path
import path from 'path';
import { fileURLToPath } from 'url'; 

// The __dirname and __filename global variables are defined in CommonJS 
// modules, but not in ES modules.
// We can fix the “__dirname is not defined in ES module scope” error by
// using certain functions to create a custom __dirname variable that
// works just like the global variable, containing the full path of
// the file’s current working directly.
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

/* used modules (external library) - axious and cheerio */
// declaring urls var for storing urls in array
let urls;

// declaring words var for storing words in array
let words;

// reading from file urls.txt and words.txt
try{
    urls = fs.readFileSync(path.join(__dirname, 'urls.txt'), 'utf-8').split('\r\n');
    words = fs.readFileSync(path.join(__dirname, 'words.txt'), 'utf-8').toLowerCase().trim().split('\r\n');
}
catch(error){
    console.log('Error occurred while reading file', error);
}

// declaring a map for counting the word frequencies from all urls
let overallWordFreq = new Map();

// creating a function to fetch data from urls and count word frequencies
const fetchDataAndCountWordFreq = async () => {

    // looping through the listofUrls
    // just printing output 1
    console.log("Output #1 - the top 3 words on the given list of urls");
    console.log("\n===========");
    for (let i = 0; i < urls.length; i++) {
        // getting data using axios
        const response = await axios.get(urls[i]);

        // using cheerio to parse the html data
        const $ = cheerio.load(response.data);
        const urlData = $('body').text().split(' ');

        //decalring var for word occurence for particular url
        let wordFrequecyForUrl = new Map();

        // looping through the urlData and counting the word frequency
        words.forEach(word => {
            urlData.forEach(wordFromData => {
                if(wordFromData.toLowerCase() === word){
                    wordFrequecyForUrl.set(word, wordFrequecyForUrl.has(word) ? wordFrequecyForUrl.get(word) + 1 : 1);
                    overallWordFreq.set(word, overallWordFreq.has(word) ? overallWordFreq.get(word) + 1 : 1);
                }
            })
        })

        // sorting the map in descending order of word frequencies and then taking top 3 frequencies words
        wordFrequecyForUrl = new Map(
            [...wordFrequecyForUrl.entries()].sort((a, b) => b[1] - a[1]).slice(0, 3)
        );

        // Printing the Output for the url
        console.log("\n" + urls[i]);

        // looping through the map and printing the key value
        // printing th top 3 value
        for(const [key, value] of wordFrequecyForUrl) {
            console.log("\n" + await key + " - " + await value);
        }
    };

    // Printing the outputs from all urls
    console.log("\n===========================");
    console.log("\nOutput #2 - total number of occurrences across all the specified URLs");
    console.log("\n===========");

    // sorting the map in descending order of word frequencies
    overallWordFreq = new Map(
        [...overallWordFreq.entries()].sort((a, b) => b[1] - a[1])
    );

    // looping through the overalWordFreq
    for(const [key, value] of overallWordFreq) {
        console.log("\n" + key + " - " + value);
    }
} 

// calling the function fetchDataAndCountWordFreq
fetchDataAndCountWordFreq();
