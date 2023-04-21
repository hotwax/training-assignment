const fs = require("fs");
const { argv } = require("process");

if(require.main===module){

    const fileName = argv[2];
    if(!fileName){
        console.log("Please enter file name while running the code.");
    }

   
    fs.readFile(fileName, (err, data)=>{
        if(err){
            if(err.code === 'ENOENT'){
                console.log("File not found");
                return;
            }
            else if(err){
                console.log("Something went wrong.");
                return;
            }
        }
        try{
            const datas = JSON.parse(data);
            console.log(datas);
            console.log("All data.");
        }catch(err){
            console.log("Data not available in the file provided.");
        }
        
    });
}