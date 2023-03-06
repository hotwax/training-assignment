import axios from 'axios';
import * as cheerio from 'cheerio';
import fs from 'fs';
// for current path
import path from 'path';
import { exit } from 'process';
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
    urls = fs.readFileSync(path.join(__dirname, 'urls.txt'), 'utf-8').trim().split('\r\n').filter(Boolean);
    words = fs.readFileSync(path.join(__dirname, 'words.txt'), 'utf-8').trim().toLowerCase().split('\r\n').filter(Boolean);

    // Handling case if any of the file is empty
    if(!urls.length){
        console.log("urls file is empty!");
        exit();
    }
    if(!words.length){
        console.log("Words file is empty!");
        exit();
    }
}
catch(error){
    //hadling case if file does not exist
    if(error.code === "ENOENT"){
        console.log('Error occurred while reading file: file does not exist: ', error.path);
    }else{
        console.log('Error occurred while reading file', error);
    }
    exit();
}

// remove if there is duplicate urls
urls = urls.filter((item, index) => urls.indexOf(item) === index);

// remove if there is duplicate words
words = words.filter((item, index) => words.indexOf(item) === index);

// declaring a map for counting the word frequencies from all urls
let overallWordFreq = new Map();

//initializing map with words
words.forEach(word => {
    overallWordFreq.set(word, 0);
})

// creating a function to fetch data from urls and count word frequencies
const fetchDataAndCountWordFreq = async () => {

    // looping through the listofUrls
    // just printing output 1
    console.log("Output #1 - the top 3 words on the given list of urls");
    console.log("\n===========");
    for (let i = 0; i < urls.length; i++) {

        let response;

        // getting data using axios
        try {       
            response = await axios.get(urls[i]);
        } catch (error) {
            // if there is no internet
            if(error.code === 'ENOTFOUND')
            {
                console.log("No internet Connection!");
                exit();
            }
            //if url does not exist 
            else if(error.response.status == 404){
                console.log("\n-- Url does not exist : " + urls[i] + "\n");
                continue;
            } 
            else{
                console.log(error);
            } 
        }

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

        //if no words where matched
        if(!wordFrequecyForUrl.size){
            console.log("No words where found from given words !");
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
