const axios   = require('axios');
const cheerio = require('cheerio');
const fs      = require('fs');


// Scrapper function which will scrap the provided url and will log the count of words present inside the words.txt file.
function scrapper (url, localWords){
    return axios.get(url)
    .then(response =>{
        const $ = cheerio.load(response.data);
        $("script").remove()
        const texts = $("body").text();

        let wordsList = {};

        localWords.map((word)=>{
            const count = texts.toLowerCase().split(word).length-1;
            wordsList[word] = count;
        })


        const data = (Object.entries(wordsList).sort((val1,val2)=>val2[1]-val1[1])).slice(0,3);
        
        console.log(url)
        console.log(data);
        console.log("***********************************************\n");
        return data;
    })
    .catch((err)=>{
        console.log("Inavlid url.");
    })    
}

if(require.main==module){

    //Reading the urls.txt file from local directory. 

    const finalWords = {};
    const urlReader = fs.readFileSync("urls.txt",'utf-8');
    const allUrls = urlReader.split('\n');

    try{
      const wordsReader = fs.readFileSync("words.txt",'utf-8');
      const allWords = wordsReader.split(/\s+/);
    }catch(err){
      console.log("Urls.txt file does not exists in current directory.");
      return ;
    }
    


    if(allUrls.length===1 && allUrls[0]==''){
        console.log("No urls found...");
        return ;
    }
    
    // Reading all words from words.txt file
  try{
    const wordsReader = fs.readFileSync("words.txt",'utf-8');
    const allWords = wordsReader.split(/\s+/);
  }catch(err){
    console.log("Words.txt file does not exists in current directory.");
    return ;
  }
    
    // console.log(allWords===)
    if(allWords.length===1 && allWords[0]==''){
        console.log("No words found...");
        return ;
    }

    allWords.map((word)=>{
        finalWords[word] = 0;
    })

    /*
     Calling the scrapper function itteratively for every url.
     and storing the outcome in a dictionary.
    */
    allUrls.map(async(url)=>{
      if(url!=""){
        const data =await scrapper(url,allWords);
        data.map((item)=>{
            finalWords[item[0]] += item[1];
        })
      }
    })

    // sorting the finalwords dictionary in decending order.
    setTimeout(()=>{  // setTimeout is used so that scarapper can scrap the data. It will wait for 2 sec.
            console.log(Object.entries(finalWords).sort((val1,val2)=>val2[1]-val1[1]));
    },2000);
}