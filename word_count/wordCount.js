const axios = require('axios')
const { htmlToText } = require('html-to-text');
const fs = require('fs');

//object to store urls
let urls = {}
//object to store words
let words = {}
//object to store final merged map
let finalMap = [];

async function main() {
    finalMap = [];
    urls = []
    words = []
    try {
        //reding urls from inputURLs.txt file
        urls = fs.readFileSync('inputURLs.txt', 'utf8').split(/\r\n|\n/);
    }
    catch (error) {
        console.log('!!! INPUTURLS.TXT FILE NOT FOUND !!!')
    }

    try {
        //reding words from inputWords.txt file
        words = fs.readFileSync('inputWords.txt', 'utf8').split(/\r\n|\n/);
    }
    catch (error) {
        console.log('!!! INPUTWORDS.TXT FILE NOT FOUND !!!')
    }

    await getFromURL();
    return finalMap;
}


//function to get data from urls
async function getFromURL() {
    //finalMap = new Array();
    //loop to iterate over every urls
    //(not used forEach() method because it only works for synchronous functions.)
    for (var url = 0; url < urls.length; url++) {
        //printing url
        console.log('\n' + urls[url]);

        //used axios get method to get data from urls 
        await axios.get(urls[url])
            .then((response) => {
                // this method filter the text from our response object and then performs
                // further computations.
                filterResponse(response);
            })
            .catch((error) => {
                //print this if url is not found or incorrect
                console.log('URL NOT FOUND !!!')
            })
    }
    //sorting final merged map in descending order.
    finalMap.sort((first, second) => { return second[1] - first[1]; })
    console.log('\nMerged Results for all URLs');
    //printing the final sorted map
    finalMap.forEach((value) => { console.log(value[0] + " - " + value[1]) })

}

//this function performs most of the computations
function filterResponse(response) {
    //map to handle words count for single url
    let map = []
    //storing the data of response in results
    let results = response.data;
    //storing data without html tags and scripts.
    let text = htmlToText(results).split(' ');
    //removing the empty words.
    let filteredText = text.filter((word) => {
        return word != '';
    })
    
    //for every word, counting its frequency in the fetched data
    words.forEach((word) => { getCount(filteredText, word, map) });
    //sort map in descending order.
    map.sort(function (first, second) { return second[1] - first[1] });
    //printing only 3 top values with most frequencies.
    map.slice(0, 3).forEach((value) => {
        console.log(value[0] + " - " + value[1])
    });

}

//function to count the the frequency of words in fetched arrays and storing in map.
function getCount(array, word, map) {
    //assigning frequency to 0
    let count = 0;
    //if word present in array are matched with our word then increase its frequency by 1.
    array
        .filter((arrayword) => { return arrayword.toLowerCase() === word.toLowerCase() })
        .forEach(() => { count++ });

    //push the word and its corresponding frequencies in map.
    map.push([word, count]);

    //flag to know if word is already present in map
    var found = false;

    finalMap
        .filter((value) => { return value[0] == word })
        .every((value) => {
            //if we found the matching word then increment add new frequencies to previously stored frequencies.
            value[1] += count;
            found = true;
            return false;
        });
    //if flag is not present in map the push it
    if (!found) finalMap.push([word, count]);
    //in the end return frequency.
    return count;
}

//calling main function.
main();

//export the getFromURL function and finalMap array
module.exports = { main, finalMap };
