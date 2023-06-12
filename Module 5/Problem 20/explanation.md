### Explanation: 

The transformation involves following operations:

* `shift:` It restructures the input by extracting the department ID, department name, employee name, and employee age for each employee. It creates a new array called "departments" where each department is represented by its ID, name, and an array of employees.

* `shift:`  It further restructures the "departments" array by extracting each department's ID and creating a new array of department IDs.

* `shift:`  It continues to restructure the "departments" array by extracting the department ID, name, and array of employees for each department.

* `modify-overwrite-beta:` operation: It modifies the "departments" array by rearranging the structure. It assigns the array of employees to the "employees" field of each department, and assigns the first element of the department ID and name arrays to the corresponding fields.

* `shift` operation: It reshapes the "departments" array by moving all elements to the root level.