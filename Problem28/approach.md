### Number of Operations: 2

1. shift
2. modify-overwrite-beta

### Explanation:

1. used shift operation first to get the 'returnId', and transforming the 'amount' key present inside returnitemAdjustments to 'returnTax' list.

2. In second operation, used modify-overwrite-beta to sum the returnTax values using the 'doubleSum' function to add up all the values of 'returnTax' and overwrites the original 'returnTax' field with the sum.