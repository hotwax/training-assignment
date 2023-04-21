import readline from 'readline';
import { exec as execute } from 'child_process';

const read = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

function showMenu() {
  console.log("\nOptions: ");
  console.log("1. Withdraw with AccountOverdrawDemo method");
  console.log("2. Withdraw with AccountOverdrawSafeDemo method");
  console.log("3. Exit\n");

  read.question("Choose: ", (choice) => {
    switch (choice) {
      case "1":
        execute('node AccountOverDrawDemo.js', (err, stdout, stderr) => {
            if (err) {
              console.error(err);
              return;
            }
            console.log(stdout);
          });

        setTimeout(showMenu, 10000);
        break;

      case "2":
        execute('node AccountOverdrawSafeDemo.js', (err, stdout, stderr) => {
            if (err) {
              console.error(err);
              return;
            }
            console.log(stdout);
          });
        setTimeout(showMenu, 10000);
        break;

      case "3":
        read.close();
        process.exit(0);

      default:
        console.log("Please enter a valid choice\n");
        showMenu();
    }

  })
}

showMenu()