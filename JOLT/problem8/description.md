### Number of operation : 4
1. shift
2. modify-default-beta
3. shift
4. modify-overwrite-beta

### Explanation
1. This operation restructures the employees list by extracting specific fields and assigning them to new field names. It creates a new list called "employees" and assigns each employee object to an index in that array. It checks the value of the "name" field and based on specific conditions, assigns the value to "employee2[]" or "employees1[]".

2. This operation modifies the employees list by adding new fields and calculating values for each employee. It sets default values for the "salary", "percentage", "calculateBonus", "total", and "bonus" fields within "employees1" and "employee2" map. 

3. This operation further restructures the employees list by extracting specific fields and assigning them to new field names. It extracts the "name", "salary", "department", "bonus", and "total" fields within each employee and assigns them to corresponding locations within the "employees" list.

4. This operation modifies the employees list  by calculating the sum of the "totalBonus" array.