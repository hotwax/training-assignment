### Explanation: 


The transformation involves following operations:

* `modify-overwrite-beta:` The field 'fullname' is splitted with space and the the values of generated lists are assigned to 'first_name' and 'last_name' using firstElement and lastElement methods, also the size of  list 'phone_numbers' is calculated using size function and is mapped to key 'phone_number_count'.

* `shift`:  Restructures the output by keeping the required fields as is and not modifying the unwanted fields 'fullname' and 'name', hence getting them removed.
