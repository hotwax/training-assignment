## Explanation

Operations: modify-default-beta, shift 

The transformation involves following operation:

* `modify-overwrite-beta:` splitted the email of each student object with '@' on a list named 'email-split', added a field named domain and mapped it to the value from 'email-split' list.

* `shift`: Traversed to each student object, accessed the value of name, age and domain fields and mapped them to list 2 level up with capitalized field names.