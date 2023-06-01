### Number of operation : 2
1. modify-overwrite-beta
2. shift

### Explanation
1. This operation modifies the given structure by concatenating the values of the "firstName" and "lastName" fields within the "employee" map and assigning the result to the "name" field. It also concatenates the values of the "street", "city", "state", and "zip" fields within the "address" map and assigns the result to the "address" field.

2. This operation restructures the given structure by extracting specific fields and assigning them to new field names. It moves the value of the "name" field within the "employee" map to "employee.name", the value of the "address" field to "employee.address", and the value of the "phones" field to "employee.phoneNumbers".