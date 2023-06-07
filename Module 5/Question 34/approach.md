## Number of operation 2

1. shift (2 times)

## Explanation:

The transformation involves following operations:


* `shift` :  It selects the fields starting with the key "hobby_" and splits it into two parts after underscore, &(0,1) is used to access the 2nd part of splitted string and make it a key under hobbies array, same procedure is followed with the words starting  with the level_.

* `shift` : Another shift operation is used to remove indexes 0,1 from the hobbies list.