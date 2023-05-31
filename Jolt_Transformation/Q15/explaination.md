### Number of Operations: 5

1. shift
2. shift
3. shift
4. modify-overwrite-beta
5. remove

### Explanation:

1.In shift operation,group the data by customerId and put all matched data inside orders list.

2.In second shift operation, remove the customerId with customers list.

3.In third shift operation, change the key name of customerId to idx and totalPrice to totalAmount.

4.In modify-overwrite-beta, we change the value of id to idx list first value and in totalAmount key sum the all value present in a list of totalamount and then inserting the name of customer 1 and customer 2 manullay.

5.In remvoe operation, remove the idx and totalamount keys.