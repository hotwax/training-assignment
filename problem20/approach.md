### Number of Operations: 4

1. shift
2. shift
3. shift
4. modify-overwrite-beta
5. shift

### Explanation:

1. First shift operation will get the keys 'id' and 'name' from the department and employee 'age' and 'name' this create a new list of objects named 'departments' having objects in it having fields 'id', 'name', 'employees' object with 'name' and 'age'

2. Second shift operation structured the 'departments' and create the list inside it by using the values from department's id as key.

3. Third shift operation it structured the 'departments' by getting id and department name in form of list.

4. used modify-overwrite-beta operation, Map the first element of the 'id' field to the 'id' field itself and the first element of the 'name' field to the 'name' field itself.

5. last shift operator shifts the 'departments' list by moving the department to root and shows all the fields in department list.