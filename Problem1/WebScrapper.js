// Importing necessary packages
const axios = require('axios');
const files = require('fs');
const cheerio = require('cheerio');

// Initializing arrays to store results
let result = [];
let number = 1;

// Function to count number of occurrences of a word in a given text
function countWord(dataString, word, array) {
    const words = dataString.split(' ');

    // Removing any empty strings from the array
    let filteredText = words.filter((word) => {
        return word != '';
    });

    let count = 0;
    filteredText
        // Filtering only the occurrences of the given word
        .filter((arrayword) => { return arrayword.toLowerCase() === word.toLowerCase() })
        // Counting the number of occurrences
        .forEach(() => { count++ });

    // Adding the word and its count to the array
    array.push([word, count]);

    // Updating the result array with the count of the word
    var exists = true;
    result
        // Filtering to find the existing entry for the word in the result array
        .filter((value) => { return value[0] == word })
        // Updating the count of the existing entry
        .every((value) => {
            value[1] += count;
            exists = false;
            return false;
        });
    // If the word does not already exist in the result array, add it
    if (exists) result.push([word, count]);
    return count;
}

// Function to read URLs from a file and process each URL
async function readUrl() {
    // Reading the URLs from the file
    try{
        files.readFile("url.txt", "utf8", async (error, urlsData) => {
            if (error) {
                console.log("Somthing wrong happened with url");
                process.exit(1);
            }
            // Splitting the URLs by line
            const urls = urlsData.split(/\r?\n/);
            // Processing each URL
            for (const url of urls) {
                try {
                    const response = await axios.get(url);
                    await readWord(response);
                } catch (err) {
                    console.error(`Issue in processing URL ${url}: ${err.message}\n`);
                    number++;
                }
            }
      await new Promise((resolve) => setTimeout(resolve, 10));
      if(result.length>0)
      console.log('Total result map :');
      show(result, 8);
    });
    }catch(exception){
        console.log("File not found");
    }
}

// Function to read words from a file and count their occurrences in a web page
async function readWord(response) {
    let storedValues = [];
    const $ = cheerio.load(response.data);
    let textData = '';
    // Extracting the text from the body of the web page
    $("body").each((index, element) => {
        textData += $(element).text() + ' ';
    });
    // Reading the words from a file
    try{
        files.readFile("word.txt", "utf8", async (error, wordsData) => {
            if (error) {
                console.log("Somthing wrong happened with words file");
                return;
            }
        // Splitting the words by line
            const words= wordsData.split(/\r?\n/);
            if(words.length>1)
            {
            // Counting the occurrences of each word
            words.forEach((word) => { countWord(textData, word, storedValues) });
            // Sorting the results and displaying the top 3
            console.log("URL : " + number);
            show(storedValues, 3);
            // Displaying the overall results and the top 8 words
            console.log('=====================');
            number++;
            }
            else
            {
                console.log("Word file is empty");
                process.exit(1);
            }            
        });
    }catch(exception)
    {
        console.log("File not found");
    }
}

// Function to sort and display the results
function show(map, num) {
    // Sorting the array in descending order based on the count
    map.sort(function (a, b) { return b[1] - a[1] });
    // Displaying the top 'number' of words and their counts
    map.slice(0, num).forEach((value) => { console.log(value[0] + " => " + value[1]) });
}

readUrl();
