import fs from "fs";// import the 'fs' module to work with file system

const deSerializeObject=()=>{
  try{
  const serializeStudentsObject=fs.readFileSync(process.argv[2]);
  console.log(`Deserialize the ${process.argv[2]} file`);
  
  //print the Student data after deserialize the .ser file
  console.log(JSON.parse(serializeStudentsObject));
  }catch(error){
    console.log(error.message);
  }
}

//call the deSerializeObject function
deSerializeObject();