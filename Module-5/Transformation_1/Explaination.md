## Explaination
### Operations: modify-overwrite-beta,shift,remove
* ### "modify-overwrite-beta":
    This operation modifies the "orders" object by overwriting its contents. It extracts the customer's address and concatenates the street, city, state, and zip code values into a single string. It also assigns the "items" value to a new "itemTemp" field.
* ### "modify-overwrite-beta": 
    This operation further modifies the "itemTemp" object within each "order" by calculating the "inverseValue" as the division of 1 by the "price" value and the "temp" as the division and rounding of 2 by the "quantity" and "inverseValue" values.
* ### "shift": 
    This operation rearranges the data structure by shifting the values of the "orders" object. It retains the original order number and all other properties, while assigning the "temp" value from the "itemTemp" object to a new "total" field within each "order".
* ### "modify-overwrite-beta": 
    This operation modifies the "orders" object again by calculating the sum of all "total" values and assigning the result to the "total" field of each "order".
* ### "remove":
    This operation removes the "inverseValue" and "temp" fields within each "item" object under "items" in all "orders".