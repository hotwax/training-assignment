## Explanation

Opeartions: modify-overwrite-beta, shift , remove

The transformation involves following operation:

* **`modify-overwrite-beta`** : Adds "customer.address" and "customer.fullname" fields to each order by concatenating address fields and customer names, also created a copy of items in each order and naming it itemTemp.

* **`modify-overwrite-beta`** : Adds "inverseValue" and "temp" fields to each item by dividing 1 by the price effictively resulting in multipication of price and quantity and storing it in variable named temp, inside itemTemp lsit.

* **`shift`**: Restructures the JSON by moving the temp value (total amount of each order) into a list named total containing the price of each order, and placing it on each order object.

* **`modify-overwrite-beta`**" : Computes the sum of "total" values within "orders" and assigns it to each order's "total" field.

* `"remove"`: Removes "firstName", and "lastName" fields from each order.