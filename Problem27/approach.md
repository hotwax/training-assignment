### Number of Operations: 2

1. modify-overwrite-beta
2. shift
3. modify-overwrite-beta

### Explanation:

1. In first operation, used modify-overwrite-beta and created new fields -

quantity - inverse the returnQuantity using divide function for multiplication. Again using divide function and divide returnPrice to quantity(inverse of returnQuantity) to perform multiplication and updated the returnPrice field.

returnTotal - to store the returnPrice in this field.

2. used shift operation to get the required fields by changing name to ReturnId, Return Amount, Return Total Amount and country full forms using # and NA where no country name needed. 

3. Used modify-overwrite-beta in third operation and used doubleSum function to sum the Return Total Amount.
