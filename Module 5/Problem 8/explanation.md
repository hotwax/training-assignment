### Explanation: 


The transformation involves following operations:

* `modify-default-beta:` Modifies the original JSON by adding new fields "bonus" and "tempBonus" to each employee. Missing default values salary, department and bonus of each employee are added manualy.

* `shift`:  creates a list toalBonus outside employees list, containing all the bonuses of employees. employees list is kept as it is.

* `modify-overwrite-beta:` Calculates the sum of values in the "totalBonus" list.
