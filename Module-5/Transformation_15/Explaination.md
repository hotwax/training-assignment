## Explaination:
### Operators: shift,modify-overwrite-beta,remove
* ### "shift": Rearranges the data structure by creating the "orders" field under each "customerId".
* ### "shift": Restructures the data by assigning all elements to the "customers" field.
* ### "shift": Further organizes the data by mapping the "orders" field to "customers", while extracting and assigning specific fields like "totalPrice" and "totalAmount".
* ### "modify-overwrite-beta": Modifies the data by calculating the sum of "totalAmount" and assigning it to each customer, assigns the first order's "customerId" as "id", and assigns specific names to the first and second customers.
* ### "remove": Removes the "customerId" field from each order within each customer.
