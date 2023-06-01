### Number of operation : 4
1. shift
2. modify-overwrite-beta
3. shift
4. modify-overwrite-beta

### Explanation
1. This operation restructures the given structure by creating new list and assigning values to specific positions based on the "orders" field. It creates list with indices corresponding to the "customerId" values and assigns the rest of the fields to those list.

2. This operation modifies the input structure by assigning specific values to the "name" and "amount" fields for different customers ("customer1" and "customer2").

3. This operation further restructures the input structure by extracting specific fields and assigning them to new field names. It moves the values of the nested fields within "items", "totalPrice", and "amount" to the corresponding positions within the "customers" list.

4. This operation modifies the input structure by assigning specific values to the "id", "name", and "totalAmount" fields for each customer. The "id" and "name" values are derived from the existing data, while the "totalAmount" is calculated as the sum of the "amount" values.