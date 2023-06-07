## Number of operation 2
1. modify-overwrite-beta (1 times)
2. shift (1 times)

## Explanation

### The "modify-overwrite-beta" operation is used to add and calculate new properties:

* The "Return Id" property is set to the value of "returnId" from the input.
* The "temp" property is calculated by dividing 1 by the value of "returnQuantity".
* The "Return Amount" property is calculated by dividing the value of "returnPrice" by "temp".
* The "Return Total Amount" property is calculated by summing the values of "Return Amount" and "returnTax".

### The "modify-overwrite-beta" operation also replaces the values of the "country" property:

* The value of "country" in the first object ("[0]") is replaced with "United States of America".
* The value of "country" in the second object ("[1]") is replaced with "Canada".
* The value of "country" in the third object ("[2]") is replaced with "NA".

### The "shift" operation is used to restructure the JSON:

* The property names are shifted to create a new array of objects with the desired property names ("Return Id", "Return Amount", "Return Total Amount", "Country").