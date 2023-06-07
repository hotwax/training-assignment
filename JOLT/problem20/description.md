### Number of operations : 3
1. shift
2. shift
3. modify-overwrite-beta

### Explanation
1. This operation is used to assign the values of the "name" and "age" fields within the "employees" maps to a new map. It also assigns the values of the "id" and "name" fields within the nested "department" maps to new list called "id" and "dename" respectively. The resulting structure includes nested maps under "employees" grouped by the corresponding "department.id".

2. This operation assign the values of the "id" and "dename" list to a new list called "department", creating maps where each map represents a department. The "employees" field is mapped to the corresponding "department" map.

3. This operation is used to removes null values from the transformed data structure by recursively squashing null fields.