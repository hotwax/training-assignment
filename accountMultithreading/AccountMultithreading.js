const AccountOverdrawDemo = require("./AccountOverdrawDemo")
const AccountOverdrawSafeDemo = require("./AccountOverdrawSafeDemo")

const read = require("readline").createInterface({
  input: process.stdin,
  output: process.stdout,
});

function enterChoice() {
  console.log("\nOptions: ");
  console.log("1. Withdrawal with AccountOverdrawDemo method");
  console.log("2. Withdrawal with AccountOverdrawSafeDemo method");
  console.log("3. Exit the program");

  read.question("Choose: ", (choice) => {
    switch (choice) {
      case "1":
        console.log();
        AccountOverdrawDemo.run();
        break;

      case "2":
        console.log();
        AccountOverdrawSafeDemo.run();
        break;

      case "3":
        read.close();
        process.exit(1);

      default:
        console.log("Please enter a valid choice\n");
        enterChoice();
    }
  })
}

enterChoice()