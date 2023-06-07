### Number of Operations: 2

1. modify-overwrite-beta
2. remove

### Explanation:

1. used modify-overwrite-beta operation and created new fields:-

inverse - this is to inverse the returnPrice for multiplication.

Return Amount - dividing the inverse of returnPrice(inverse) and returnQuantity to performed multiplication

Return Total Amount - used doubleSum function to add returnTax and Return Amount

2. used remove operation and removed returnQuantity, returnPrice, returnTax and inverse.