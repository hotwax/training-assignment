### Explanation: 

The transformation involves following operations:

* `modify-overwrite-beta:`It calculates the "Return Amount" field by multiplying returnPrice and returnQuantity. and then a field named 'ReturnTotalAmount' is created by adding returnTax and returnAmount,and the value of key 'returnId' is kept in new key called'ReturnId'. Also the full form of country is added manually for each index.


* `shift:` It removes the original fields from each object in the input array, including "returnId", "returnQuantity", "returnPrice", "temp", "returnTax" and adds the  value of field 'Country' by using if else statements and '#' wildcard for adding default values.
