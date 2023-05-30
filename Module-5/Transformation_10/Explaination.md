## Explaination:
### Operators:modify-overwrite-beta,remove 
* ### "modify-overwrite-beta":

    This operation modifies the data by overwriting certain fields based on the specified transformations.
    The transformations applied in this operation are as follows:
    * "fullname": It splits the value of the "name" field into an array using a space as the separator, and assigns it to the "fullname" field.
    * "first_name": It assigns the first element of the "fullname" array to the "first_name" field.
    * "last_name": It assigns the last element of the "fullname" array to the "last_name" field.
    * "phone_no_count": It determines the size (number of elements) of the "phone_numbers" array and assigns it to the "phone_no_count" field.
* "remove":
    * This operation removes the specified fields from the data.
    * The fields to be removed are "name" and "fullname".