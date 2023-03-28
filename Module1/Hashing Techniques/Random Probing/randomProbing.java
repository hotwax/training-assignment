import java.util.Random;
import java.util.Scanner;

public class randomProbing {
    static class keyValuePair{
        int key;
        int value;
        keyValuePair next;
        keyValuePair(int value, int key){
            this.value = value;
            this.key = key;
        }
    }

    static class hashing{
        int size;
        Random rand = new Random();
        int randomValue ;
        keyValuePair[] container;
        // Created a variable for counting the number of collision.
        int collision = 0;
        hashing(int size){
            this.size = size;
            this.randomValue = rand.nextInt(size);
            this.container = new keyValuePair[size];
            for(keyValuePair i:container) {
                i = null;
            } 
        }

        // hash function(magic function) for 
        private int hashFunction(int key){
            return key%this.size;
        }

        /*
         * This function is used for inserting value in the hashMap.
         * the function takes key and value as an argument.
         */
        public void insert(int key, int value){
            int indexValue = hashFunction(key);
            keyValuePair start = container[indexValue];
            keyValuePair newNode = new keyValuePair(value,key);
            if(start == null){
                container[indexValue] = newNode;
            }
            else if(container[indexValue].key == key){
                container[indexValue].value = value;
            }
            else{
                int i = indexValue;
                while(container[i] != null ){
                    i = (i+randomValue)%this.size;
                    if(i == hashFunction(key)){
                        System.out.println("your hash Map if full.");
                        return;
                    }
                }
                this.collision +=1;
                container[i] = newNode;
            }
        }

        /*
         * This function is used for deleting value from the hashMap.
         * The function takes key as an argument. and delete that value.
         */
        public void delete(int key){
            int indexValue = hashFunction(key);
            if(container[indexValue]!=null && container[indexValue].key == key){
                container[indexValue] = null;
            }
            else{
                int i = indexValue;
                while(container[indexValue].key != key ){
                    i = (i+randomValue)%size;
                    if(i == indexValue){
                        System.out.println("Unable to find the key provided.");
                        return;
                    }
                }
                container[i] = null;
                this.collision -= 1;
            }
        }

        /*
         * Thsi function is used for retriving value from the hashMap.
         * the function takes key as an argument and return corresponding key value pair.
         */
        public void getValue(int key){
            int indexValue = hashFunction(key);
            if(container[indexValue] != null &&container[indexValue].key == key){
                System.out.println("{"+container[indexValue].key+" : "+container[indexValue].value +"}");
                return ;
            }
            else {
                int index = indexValue;
                while(container[index] != null &&container[index].key != key){

                    index = (index+randomValue)%this.size;
                    if(index == hashFunction(key)){
                        System.out.println("Key not found.");
                        return;
                    }
                }
                if( container[index] !=null &&container[index].key == key){
                    System.out.println("{"+container[index].key+" : "+container[index].value +"}");
                    return ;
                }
                else{
                    System.out.println("Key not found.");
                }
            }
        }

        //This function is used for displaying all data of hashMap.
        public void showData(){
            int count = 0;
            for(keyValuePair start:container){
                System.out.print(count+" --> ");
                if(start!=null){
                    System.out.print("{"+start.key+" : "+start.value +"}");
                }
                count+=1;
                System.out.println();
            }
        }
    }

    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        System.out.print("Please Enter the size of the HashMap: ");
        int size = input.nextInt();
        hashing obj = new hashing(size);
        System.out.println("Select an option from the menu given below.");
        System.out.println("1 : Insert  ");
        System.out.println("2 : Delete ");
        System.out.println("3 : Display the Hashmap");
        System.out.println("4 : Number of Collisions");
        System.out.println("5 : Get Value of Key");
        System.out.println("6 : Exit");
        int option;
        while(true){
            option = input.nextInt();
            if(option == 1){
                long t1 = System.currentTimeMillis();
                System.out.print("Enter key: ");
                int key = input.nextInt();
                System.out.print("Enter value: ");
                int value = input.nextInt();
                obj.insert(key, value);
                long t2 = System.currentTimeMillis();
                System.out.println("Time taken in insertion is: "+ (t2-t1));
            }

            else if(option == 2){
                long t1 = System.currentTimeMillis();
                System.out.print("Enter key: ");
                int key = input.nextInt();
                obj.delete(key);
                long t2 = System.currentTimeMillis();
                System.out.println("Time taken in deletion: "+ (t2-t1));
            }

            else if(option == 3){
                // long t1 = System.currentTimeMillis();
                obj.showData();
                // long t2 = System.currentTimeMillis();
                // System.out.println("Time taken in insertion is: "+ (t2-t1));
            }
            else if(option ==4){
                System.out.println("Number of collision occurs: "+obj.collision);
            }

            else if(option == 5){
                long t1 = System.currentTimeMillis();
                System.out.print("Enter key: ");
                int key = input.nextInt();
                obj.getValue(key);
                long t2 = System.currentTimeMillis();
                System.out.println("Time taken in searching: "+ (t2-t1));
            }

            else if(option == 6){
                break;
            }
            else{
                System.out.println("please choose a valid option.");
            }
        }
    }
}
