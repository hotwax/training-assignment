// DeserializationTest class
class DeserializationTest {
    static deserializeFromFile(filename) {
      const fs = require('fs');
      const data = fs.readFileSync(filename);
      return JSON.parse(data);
    }
  }

  
// deserializing and printing students from files
console.log(DeserializationTest.deserializeFromFile('output1.ser'));
console.log(DeserializationTest.deserializeFromFile('output2.ser'));