### Number of operations: 2
1. shift
2. modify-overwrite-beta


### Approach
1. The shift operation is used to transform the structure of the given input json into the required output json by including only the required fields and a list is created with the amounts of each id as elements in the list .
2. In the second operation the doublSum function is used to calculate the sum of all the amounts in the returnTax list and it is overwritten with the modify-overwrite-beta operation.