const axios = require('axios')
const { htmlToText } = require('html-to-text');
const fs = require('fs');

let urls = {}
let words = {}
let finalMap = [];

try {
    urls = fs.readFileSync('inputURLs.txt', 'utf8').split(/\r\n|\n/);
}
catch (err) {
    console.log('!!! INPUTURLS.TXT FILE NOT FOUND !!!')
}

try {
    words = fs.readFileSync('inputWords.txt', 'utf8').split(/\r\n|\n/);
}
catch (err) {
    console.log('!!! INPUTWORDS.TXT FILE NOT FOUND !!!')
}

readFromURL();

async function readFromURL() {
    for (var i = 0; i < urls.length; i++) {
        console.log('\n' + urls[i]);

        await axios.get(urls[i])
            .then((response) => {
                doSomething(response);
            })
            .catch((error) => {
                console.log('!!! URL NOT FOUND !!!')
            })
    }

    finalMap.sort((a, b) => {return b[1] - a[1];})
    console.log('\nMerged Results for all URLs');
    finalMap.slice(0, 3).forEach((value) => { console.log(value[0] + " - " + value[1]) })

}

function doSomething(response) {
    let map = []
    let res = response.data;
    let text = htmlToText(res).split(' ');
    let filteredText = text.filter((word) => {
        return word != '';
    })
    words.forEach((word) => { count(filteredText, word, map) });
    map.sort(function (a, b) { return b[1] - a[1] });
    map.slice(0, 3).forEach((value) => { console.log(value[0] + " - " + value[1]) });

}


function count(array, word, map) {
    let res = 0;
    array
        .filter((arrayword) => { return arrayword.toLowerCase() === word.toLowerCase() })
        .forEach(() => { res++ });

    map.push([word, res]);
    var flag = true;

    finalMap
        .filter((value) => { return value[0] == word })
        .every((value) => {
            value[1] += res;
            flag = false;
            return false;
        });
    if (flag) finalMap.push([word, res]);
    return res;
}