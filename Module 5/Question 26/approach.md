## Number of operation 2
1. modify-default-beta (1 times)
2. remove (1 times)

## Explanation:

* It modifies the JSON structure by adding new properties and modifying existing properties.
* It calculates the values for the new properties based on the existing properties.
* It removes the existing properties that are no longer needed.
### Here's a breakdown of the transformation steps:

* The "modify-default-beta" operation is used to add and calculate new properties:
* The "Return Id" property is set to the value of "returnId" from the input.
* The "temp" property is calculated by dividing 1 by the value of "returnQuantity".
* The "Return Amount" property is calculated by dividing 2 by the value of "returnPrice" and "temp".
* The "Return Total Amount" property is calculated by summing the values of "Return Amount" and "returnTax".
* The "remove" operation is used to remove the existing properties that are no longer needed:
        "returnId", "returnQuantity", "returnPrice", "returnTax", and "temp" properties are removed from each object.