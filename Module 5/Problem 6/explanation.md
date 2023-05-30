## Explanation

Operations: modify-default-beta, shift 

The transformation involves following operation:

* `modify-overwrite-beta:` Added the field address by concatinating the fields of address map. 
* `shift`: Traversed to name and address fields, lifted them 2 levels up in a list, navigated to hobbies objects, accessed the 'names' of hobbies and mapped it to simple list named Hobbies, 4 level up.