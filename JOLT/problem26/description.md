### Number of operation : 2
1. modify-overwrite-beta
2. shift

### Explanation
1. This operation modifies the input structure by assigning specific values to the "quantity", "returnPrice", and "returnTotal" fields for each customer. The "returnPrice" is calculate the total price with added tax, while the "totalAmount" is calculated as the sum of the "returnPrice" and "returnTax" values.

2. This operation is restructure the input Structure by by extracting specific fields and assigning them to new field names. It move the value of "returnId", "returnPrice", "returnTotal" to new created list and assign them to "ReturnId", "Return Amount" and "Return Total Amount" fields respectively on each map. 