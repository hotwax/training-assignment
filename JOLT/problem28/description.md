### Number of operations : 2
1. shift
2. modify-overwrite-beta

### Explanation
1. This operation is used to assign the "returnId" value of each object in the input list to a new list at the same index. It also extracts the "amount" value from each nested "returnItemAdjustments" object and maps it to a new list called "returnTax" within the corresponding "returnId" object.

2. This operation is used to calculates the sum of all values in the "returnTax" list and assigns the total sum to each "returnId" object.