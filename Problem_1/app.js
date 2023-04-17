// This program reads two files - "words.txt" and "urls.txt", and performs web scraping on each url in the urls file to count the occurrence of 
// each word in the words file. The program outputs the top 3 words for each url, as well as the total top 3 words across all urls.


// importing neccessary dependencies

const request = require('request');
const cheerio = require('cheerio');
const fs = require('fs');


// The program first defines and initializes several variables including arrays for holding the words and urls data, and objects for counting the occurrence of words.
let wordlocal = [];
let words;
let countlocalwords = {};
let countwords = {};
let urldata = [];
let sizeofurls=0;
let webscrapingexecuted = 0;


// The function readwordfile() reads the words file and populates the data in wordlocal and countlocalwords arrays.
function readwordfile() {
  try {
    // This block of code reads a file named "words.txt" using the 'fs' module
    // The contents of the file are split by line breaks ('\r\n') into an array called 'words'
    words = fs.readFileSync("words.txt", "utf-8").split('\r\n');
    
    // The code then iterates over each element in the 'words' array and performs the following:
    words.forEach(item =>{
        // - pushes the current word into a new array called 'wordlocal'
        wordlocal.push(item);
        // - sets the initial count of the current word to 0 in the 'countlocalwords' object
        countlocalwords[item] = 0;
        // - sets the initial count of the current word to 0 in the 'countwords' object
        countwords[item]=0;
        
      });

      // If there is an error reading the file, the code logs an error message.
  } catch (err) {
    console.log();
    console.log("Error reading words file");
  }
    
}

// The function readurlfile() reads the urls file and populates the urldata array.

function readurlfile()
{
    try {

        // This block of code reads a file named "urls.txt" using the 'fs' module
    // The contents of the file are split by line breaks ('\r\n') into an array called 'urlsdatafile'
        urlsdatafile = fs.readFileSync("urls.txt", "utf-8").split('\r\n');
        
        // The code then iterates over each element in the 'words' array and performs the following:
        urlsdatafile.forEach(item =>{
            // - pushes the current word into a new array called 'urldata'
            urldata.push(item);
            // increase the size of url counter
            sizeofurls++;
            
          });
          // If there is an error reading the file, the code logs an error message.
      } catch (err) {
        console.log();
        console.log("Error reading words file");
      }
}

// The function startprogram() loops through each url in urldata and calls the webscraping() function.

function startprogram()
{
    urldata.forEach(item=>
        {
            // calling webscraping function for each url
            webscraping(item);
        });
}




// calling functions
readwordfile();
readurlfile();
startprogram();

// The webscraping() function performs the actual web scraping by making a request to the url and parsing the body text using cheerio. It then splits the text into words, counts the occurrence of each word,
// and outputs the top 3 words for the url. It also updates the countwords object, which keeps track of the total occurrence of each word across all urls.
function webscraping(u)
{
    const url = u;

    // This code block extracts the text content of the given URL using the Cheerio library
request(url, function(error, response, body) {
  if (!error && response.statusCode == 200) {
    const $ = cheerio.load(body);
    const text = $('body').text();
    // then splits the text into individual words using a regular expression
    let words = text.split(/\s+/);
    // It then iterates over each word, converts it to lowercase, and checks if it is present in the countlocalwords object
    words.forEach(item=>{
        const it = item.toLowerCase();

        // If it is, it increments the count of the word in the countlocalwords object and the countwords object. 
        if(countlocalwords.hasOwnProperty(it))
        {
            const startingvalue = countlocalwords[it];
            const globalwordcount = countwords[it];
            countlocalwords[it]= startingvalue+1
            countwords[it] = globalwordcount+1;
            
        }
        
    })

    // sort the counterlocalword object data
    const wordCountsort = Object.entries(countlocalwords).sort(
        (a, b) => b[1] - a[1]
      );

      // taking only top 3 value from the counterlocalword
      const firstThreewords = Object.fromEntries(Object.entries(wordCountsort).slice(0, 3));
      console.log();

      // printing top 3 words of each urls
      console.log("Top 3 words from url : :"+ url + " are :");
      for (let key in firstThreewords) {
        console.log(`${firstThreewords[key][0]}  : ${firstThreewords[key][1]}`);
      }
      for (let key in countlocalwords) {
        countlocalwords[key] = 0;
      }
      webscrapingexecuted++;

      // Finally, after all urls have been scraped, the program outputs the top 3 words across all urls.
      if(sizeofurls==webscrapingexecuted)
      {
        // sort the data
        const sorttotal = Object.entries(countwords).sort(
            (a, b) => b[1] - a[1]
          );

          // taking top 3 values 
          const totalsorted = Object.fromEntries(Object.entries(sorttotal).slice(0, 3));


          // printing top 3 values
          console.log();
          console.log("Total Top 3 words  are :");
          console.log();
          for (let key in totalsorted) {
            console.log(`${totalsorted[key][0]}  : ${totalsorted[key][1]}`);
        }
      }
  }
});
}
