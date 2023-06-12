### Explanation: 
**8_a:**


The transformation involves following operations:

* `modify-default-beta:` Modifies the original JSON by adding new fields "bonus" to each employee. Missing default values salary, department and bonus of each employee are added manualy.

* `shift`:  creates a list toalBonus outside employees list, containing all the bonuses of employees. employees list is kept as it is.

* `modify-overwrite-beta:` Calculates the sum of values in the "totalBonus" list.


**8_c:**


* `default:` Adds the missing 'department' and 'salaries' into the missing employees objects.
* `shift` and `modify-overwrite-beta`:  calculates the bonues for emplyee department wise 2.5% for marketing and 2% for others.

* `shift` Creates a list totalBonus while keeping other field as it is
`modify-overwrite-beta`:Converts bonus to integer and sums the bonus array into a new field called 'totalBonus'
