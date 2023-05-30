## Explaination
### Operations: shift.
* ### "shift": 

    This operation maps the values of specific fields in the input JSON to new field names and possibly new parent field names as well. In this case, it performs the following mappings:

    * The value of the "name" field is mapped to the "Name" field in the output JSON.
    * The value of the "age" field is mapped to the "Age" field in the output JSON.
    * The value of the "city" field is mapped to the "City" field under the "Contact" parent field in the output JSON.
    * The value of the "phone" field is mapped to the "Phone" field under the "Contact" parent field in the output JSON.
