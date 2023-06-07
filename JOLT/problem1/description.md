### Number of operation : 4
1. modify-overwrite-beta
2. shift
3. modify-overwrite-beta
4. remove

### Explanation
1. This operation modifies the "orders" structure by adding or updating certain fields. It adds the "fullName" field by concatenating the "firstName" and "lastName" fields of the "customer". It also adds the "address" field by concatenating the "street", "city", "state", and "zip" fields of the "address". It calculates the "totals" price of each item.

2. This operation restructures the "orders" data by moving certain fields to a new location. It creates a new list called "orders" and assigns each orders map to an index in that list. It also moves the "items" list to the corresponding order map and also move the "totals" field to the order map.

3. This operation modifies the "orders" structure again by calculating the sum of the "total" field for each order.

4. This operation removes certain fields from the "orders" structure. It removes the "firstName" and "lastName" fields from the "customer" object and removes the "totals" and "totalquan" fields from each item.