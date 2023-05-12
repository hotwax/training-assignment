import java.util.Scanner;

public class customHashMap {

    /*
     * class for creating the hash map link.
     * this class contains a parameterised constructor which takes key and a value as an parameter.
     */
    static class keyValuePair{
        int key;
        int value;
        keyValuePair next;
        keyValuePair(int key, int value){
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    /*
     * This class is created for performing basic operation of hash map.
     */
    static class newHashMap{
        keyValuePair[] container;
        int size;
        // Conteructor for initialization of container.
        // It takes an argument size and then creates a hashMap of that size.
        newHashMap(int size){
            this.size = size;
            this.container = new keyValuePair[size];
            for (int i=0; i<size; i++)this.container[i] = null;
        }

        // Hash fucntion which will provide us the index of the container so that we can store the 
        // store the key value pair there.
        public int hashFunction(int key){
            return key%size;
        }
        /*
         * This function is used for storing/ inserting key, value pair in the hash map.
         * It takes two argument key and value.
         */
        public void insert(int key, int value){
            int indexValue = hashFunction(key);
            keyValuePair newNode = new keyValuePair(key, value);
            
            if(container[indexValue] == null){
                container[indexValue] = newNode;
            }
            else{
                keyValuePair curr = container[indexValue];
                while(curr.next !=null){
                    curr = curr.next;
                }
                curr.next = newNode;
            }
        }

        /*
         * This function is used to get data from the hash map.
         * function accepts a key value and search for the value associted with that key.
         */
        public void getData(int key){
            int indexValue = hashFunction(key);
            System.out.print("Your data is:  ");
            try{
                keyValuePair data = container[indexValue];
                while(data != null){
                    if(data.key == key){
                        System.out.print(data.value);
                        break;
                    }
                    data = data.next;
                }
                System.out.println();

            }catch (Exception e){
                System.out.println("value doex not exists");
            }

        }
        /*
         * This function is used for deleting an element from the hash map
         * function accepts a key and search for the same in the hash map 
         * and then delete that key value pair.
         */
        public boolean delete(int key){
            int indexValue = hashFunction(key);
            keyValuePair data = container[indexValue];
            if(data.next == null && data.key == key){
                container[indexValue] = null;
                return true;
            }
            else if(data.next == null){
                System.out.println("Unable to find the value for provided key");
                return false;
            }
            else if(data.key == key){
                data = data.next;
            }
            while(data!=null){
                if(data.next.key == key){
                    data.next = data.next.next;
                    return true;
                }

            }
            return false;
            
        }

        // This function is used for displaying the complete hash map
        public void displayHashMap(){
            int index = 0;
            for(keyValuePair start : container){
                System.out.print(index+" ");
                while(start !=null){
                    System.out.print(" { "+start.key+":"+start.value+" }"+"-->");
                    start = start.next;
                }
                index +=1;
                System.out.println();
            }
        }
        /*
         * This function is used for searching data with the help of value
         * The complexity of this function is o(n).
         * Function takes one argument value and search in the hash map for that value.
         */
        public void searchByValue(int value){
             for(keyValuePair start: container){
                while(start !=null){
                    if(start.value == value){
                        System.out.println("value found "+start.key + " : "+ start.value);
                        return ;
                    }
                    start=start.next;
                }
             }
             System.out.println("provided value is not present in the hash map.");
             return;
        }

    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the size of hash map.");
        int size = input.nextInt();
	    newHashMap obj = new newHashMap(size);
        int option;
        // Menu option for the user.
        System.out.println("1 : Insert ");
        System.out.println("2 : Delete ");
        System.out.println("3 : Get value of the key.");
        System.out.println("4 : Display the Hashmap.");
        System.out.println("5 : Search an element");
        System.out.println("6 : Exit");
        System.out.println("Enter your choice : ");
        while(true){
            option = input.nextInt();
            if(option == 1){
                System.out.print("Enter key : ");
                int key = input.nextInt();
                System.out.print("Enter a value: ");
                int value = input.nextInt();
                obj.insert(key, value);
                System.out.println("Data inserted");
            }
            else if(option == 2){
                System.out.println("Enter the key: ");
                int key = input.nextInt();
                boolean resp = obj.delete(key);
                if(resp){
                    System.out.println("Element deleted successfully.");
                }

            }
            else if (option == 3){
                System.out.println("Enter a key: ");
                int key = input.nextInt();
                obj.getData(key);
            }
            else if(option == 4){
                obj.displayHashMap();
            }
            else if(option == 5){
                System.out.print("Enter the value: ");
                int value = input.nextInt();
                obj.searchByValue(value);
            }
            else if(option == 6){
                break;
            }
            else{
                System.out.println("Please enter a valid option. ");
            }
            System.out.print("choose an option again: ");
        }
    }
}