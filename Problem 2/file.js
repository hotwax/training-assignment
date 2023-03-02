process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', (inputStdin) => {
  inputString += inputStdin;
});

process.stdin.on('end', (_) => {
  inputString = inputString
    .trim()
    .split('\n')
    .map((string) => {
      return string.trim();
    });

  main();
});

function readLine() {
  return inputString[currentLine++];
}

/****** BELOW HERE START WRITING YOUR CODE IN main() FUNCTION   ***************************************/
/**
 * Use "readLine()" function for input, which will return a string consisting the entire line, so accordingly split the string
 * when required.
 *
 * I am using console.log() to output
 */
function main() {
  let t = readLine();
  t = parseInt(t);
  console.log('mai yahan aaya');
  while (t--) {
    let line = readLine();
    line = line.split(' ');
    let n = parseInt(line[0]);
    let m = parseInt(line[1]);
    if (n === 1) {
      console.log('0');
    } else if (n === 2) {
      console.log(m);
    } else {
      console.log(2 * m);
    }
  }
}
const arr = [retailers, github, open, source, shopify, order];
