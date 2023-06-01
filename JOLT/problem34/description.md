### Number of operation : 2
1. shift
2. shift 

### Explanation
1. This operation is used to maps the value of the "name" field to a new field called "name". It also maps the values of fields that start with "hobby_" to a new field called "name" within an map. Similarly, it maps the values of fields that start with "level_" to a new field called "level" within the same map.

2. This operation is used to maps the value of the "name" field to a new field called "name" in the output map. It then maps all other fields using the wildcard "*" to a new list called "Hobbies", resulting in the extraction of the previous objects created in the first step and placing them in the "Hobbies" list.