### Number of Operations: 6

1. shift
2. shift
3. shift
4. modify-overwrite-beta 
5. remove
6. sort

### Explanation:
1. first shift operation structured the the value of customersId and having the orders list in it.

2. using second shift operation the renames the root level key to "customers".

3. third shift operation, arranges the customer list by getting each orders id, date and totalPrice fields as 'totalAmount' in form of an array.

4. used modify-overwrite-beta, it modifies the customers by updating the 'totalAmount' using intSum function and also updates customerId. The first customer is assigned the name "John Doe", and the second customer is assigned the name "Jane Doe".

5. Used remove operation is used to remove the "customerId" key from each order object under the 'customers' list.

6. used sort to sort the customers list in ascending order.