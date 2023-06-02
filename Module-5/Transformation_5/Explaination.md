## Explaination:
### Operators: shift,modify-overwrite-beta,remove
* ### "modify-overwrite-beta":
    The operation combines the "firstName" and "lastName" properties into a new "fullname" property using the "concat" function.
* ### "shift":
    The operation shifts the "address" property's "city" and "zip" properties to the root level of the output JSON.
    The "*" wildcard captures all other properties and copies them as-is to the output JSON.
* ### "remove":
    The operation removes the "firstName" and "lastName" properties from the output JSON.
