const rp = require('request-promise-native');
const cheerio = require('cheerio');
const fs = require('fs');

// Read input files
const urls = fs.readFileSync('urls.txt', 'utf-8').split('\n').filter(Boolean);
const words = fs.readFileSync('words.txt', 'utf-8').split('\n').filter(Boolean);

// Function to count words in a text
function countWords(text) {
  const counts = {};
  const words = text.split(/\s+/);
  for (const word of words) {
    counts[word] = counts[word] ? counts[word] + 1 : 1;
  }
  return counts;
}

// Process each URL
const results = {};
for (const url of urls) {
  rp(url)
    .then(html => {
      const $ = cheerio.load(html);
      const text = $('body').text();
      const counts = countWords(text);
      const topWords = words
        .map(word => ({ word, count: counts[word] || 0 }))
        .sort((a, b) => b.count - a.count)
        .slice(0, 3);
      results[url] = topWords;
    })
    .catch(error => {
      console.error(`Error fetching ${url}: ${error}`);
    });
}

// Write output files
const urlsOutput = [];
const wordsOutput = {};
for (const [url, topWords] of Object.entries(results)) {
  urlsOutput.push(url);
  for (const { word, count } of topWords) {
    if (word in wordsOutput) {
      wordsOutput[word] += count;
    } else {
      wordsOutput[word] = count;
    }
  }
  const topWordsOutput = topWords.map(({ word, count }) => `${word} - ${count}`).join('\n');
  fs.writeFileSync(`${url.replace(/[^\w]/g, '_')}.txt`, topWordsOutput);
}
urlsOutput.push(''); // Add blank line to separate URLs from words
for (const [word, count] of Object.entries(wordsOutput).sort((a, b) => b[1] - a[1])) {
  urlsOutput.push(`${word} - ${count}`);
}
fs.writeFileSync('output.txt', urlsOutput.join('\n'));
