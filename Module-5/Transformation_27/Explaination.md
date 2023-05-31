## Explaination:
### Operations:modify-overwrite-beta,remove 
* ### "modify-overwrite-beta" operation:

    * Calculates and modifies specific fields in the JSON data based on provided formulas and mappings.
    * Calculates the "temp" field by dividing 1 by the value of "returnQuantity".
    * Calculates the "Return Amount" field by dividing 2 by the value of "returnPrice" using the calculated "temp" value.
    * Calculates the "Return Total Amount" field by summing the "Return Amount" and "returnTax" fields.
    * Copies the value of "returnId" to the "ReturnId" field.
    * Specifies specific values for the "Country" field based on different indexes.
* ### "remove" operation:

    * Removes specific fields from the JSON, including "returnId", "returnQuantity", "returnPrice", "temp", "returnTax", and "country".