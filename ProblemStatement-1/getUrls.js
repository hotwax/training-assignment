import fs from "fs";

// Define a function that reads a file named "Urls.txt"  and returns a Promise that resolves to a Set of URLs contained in the file.
export const getUrls = () => {
  const urls = new Set(); 

  return new Promise((resolve, reject) => { // Return a new Promise that resolves to the Set of URLs.
    try {
      const urlData = fs.readFileSync("ProblemStatement-1/Urls.txt"); // Read the contents of the file synchronously.
      const urlFileData = urlData.toString(); // Convert the contents to a string.
      const urlsList = urlFileData.split(/\s+/); // Split the string into an array of URLs using a regular expression to split on whitespace characters.
      const filteredUrls = urlsList.filter((url) => url.trim() !== ""); // Filter out any empty URLs.
      for (const url of filteredUrls) { // Iterate over the remaining URLs.
        urls.add(url); // Add each URL to the Set.
      }
      resolve(urls); // Resolve the Promise with the Set of URLs.
    } catch (error) {
      reject(error); // If an error occurs while reading the file, reject the Promise with the error object.
    }
  });
};
