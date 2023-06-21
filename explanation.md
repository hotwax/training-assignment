1.

Firstly, find "totalPrice" by multiplicating quantity with price.
Then concatenate firstName and lastName to form fullName and join all address field's values with ','.

Put "totalPrice" of each item outside the "items" list and then calculate its sum in cases where a list of totalPrices is formed.

Remove unnecessary fields.

Note- We can also use & in place of # (in 2nd approach) while referencing the items of a list. 
The former considers present level as 0 and latter considers the present level as 1.

------------------------------------------------------------------------------

2. 

Here we just have to capitalize every field's name first letter. We can do it with "shift" operation which overwrites every field's name with the new one (eg- name->Name).

------------------------------------------------------------------------------

3.

Here we have to capitalize every field's name first letter. We can do it with "shift" operation which overwrites every field's name with the new one (eg- name->Name).

Then we put "city" and "phone" into a new object "Contact" with dot-notation (eg- city->Contact.city).

------------------------------------------------------------------------------

4.

Here we have to capitalize every field's name first letter. We can do it with "shift" operation which overwrites every field's name with the new one (eg- name->Name).

And then put all the items of "hobbies" list into a "Hobbies" list with item as the value of a
field "Name".

------------------------------------------------------------------------------

5.

Concatenate "firstName" and "lastName" to form "fullName". 

Use shift to get only required fields.

------------------------------------------------------------------------------

6. 

Concatenate the address map all values and place it in "Address".

Traverses the "hobbies" list. For each item, put the value of "name" field into a "Hobbies" list as its item. This is the classic example of Arrays where we want to put different values to a same destination.

------------------------------------------------------------------------------

7.

Approach 1-

First split the email with '@' and store its second part in "Domain" field.

Use shift to get only required fields.

Approach 2-

Use * to make a pattern for email like "*\\@*" and then use $(0,2) to get the second part 'Domain' as required. 

------------------------------------------------------------------------------

8.

Approach 1-

Use "modify-default-beta" to insert default values for "department" and "salary" in objects where they are not present.

Insert indexwise values for "bonus" as given in the output spec with # on LHS and shift operation.

Make a duplicate value of bonus "bonusDuplicate" so that this value can be placed outside the "employees" list to make an array whose sum (totalBonus) needs to be calculated.

Place the "bonusDuplicate" field of all "employees" object outside that object to make a list.

Calculate the sum of "totalBonus" elements.

Approach 2-

The first two steps are same for this approach.

Now, Calculate the "totalBonus" placed outside the "employees" list by individually using bonus of all employees.

Approach 3-

The first two steps are same for this approach.

We need not to create a duplicate bonus value to place it outside the "employees" list and then calculates its sum, rather we first place all employees by using "@": "&" as it is and then copy their bonus value to put them outside the "employees" list and calculate its sum afterwards.

Approach 4-

The first step is same as above.

Here we have noticed that "Sales" and "IT" departments have their bonus as their salary/50 and that of "Marketing" department as salary/40.
So In the second step, we group employees on the basis of their departments. 

Conditionally calculate their bonus by their salary.

Now make the "employees" list as it was before.

and calculate "totalBonus" like we did in the last approach.

------------------------------------------------------------------------------

9.

Just use "remove" operation to remove "state" field of "address".

------------------------------------------------------------------------------

10.

Split "name" into "firstName" and "lastName" and calculate "phone_numbers" list size with "=size" function.

Remove "name" field.

------------------------------------------------------------------------------

11.

Go into each item of the "items" list. Replace "name" and "value" with the new names specified in the output. 

Here, levels play a very important role in referencing the top fields with '&' instead of rewriting them and use '#' to reference top lists of which the present element is a part of.

------------------------------------------------------------------------------

12.

Just Concatenate "name" and "address". Then replace field names with the new field names as given in the output.

------------------------------------------------------------------------------

13.

Firstly we replace all the items of the "employees" list with their corresponding maps where key is the id of that particular employee and value is that employee object itself by using @ and the level (0) of the id.

Then remove "id" from all the employee's maps.

------------------------------------------------------------------------------

14.

Approach 1-

Use "modify-default-beta" to insert two new fields "homePhone" and "workPhone" with their values given respectively in the "phone" list at the "0" and "1" indices.

Then remove the "phone" field.

Approach 2-

Go to "number" field of "phone" list at "0" and "1" indices and put its value in "homePhone" and "workPhone" respectively at the "id" level.

Approach 3-

Go to "number" field of "phone" and place its value at the "id" value with "type" value as its key. 

Then add "Phone" to these two fields' "home" and "work" names.

------------------------------------------------------------------------------

15.

We did it with 3 approaches, 3rd is the most optimized one. 

Approach 3-

Firstly group all the orders on the basis of customerId so that all orders of a customer get placed in a list.

Then place all these customers' orders in a "customers" list and all orders in the "orders" list. Insert "customerId" inside every item of "customers" list with the key we have made in the above step.

Then copy "totalPrice" of all orders outside the "orders" list and calculate its sum.

Insert "name" field to each customer object with values given in the output indexwise.

------------------------------------------------------------------------------

16.

Approach 1-

Copy employee's name inside the skills list for each skill item.

Then place this name inside "level" of the skill which is inside "name" of the skill.

Approach 2-

Go to every item of the "skills" list and use employee's name from the 2nd level to be put in 
"level" of the skill which is inside "name" of the skill. 

------------------------------------------------------------------------------

17.

Use "modify-default-beta" to insert "available": "false" where it is not present (0th index). 

------------------------------------------------------------------------------

18.

Approach 1-

Divide every employee's salary with 10 to get a field "amtToAdd" which we would add in the "salary" to get the required salary.

Approach 2-

We have to perform,
   x = x+ (x/10)
=> x = 11x/10
=> x = x/ (10/11) in division form since we can't multiply 11/10 directly.

10/11 is 0.909090901

So we divide x with 0.909090901 to get the desired value.

------------------------------------------------------------------------------

19.

Put 0th index price outside of the "products" list since we have to leave it as it is.
Then multiply "price" with 2 by dividing it with 0.5.

Again place the 0th index price back into 0th index product object. 

------------------------------------------------------------------------------

20.

Approach 1-

Firstly concat the values of all "department" map.
Then group all the employees on the basis of department so that employees having same department are placed together.
Name the items of "departments" objects as "employees".
Now, split the department field.
Add two new fields "id" and "name" to every item of "departments" map with department[0] and department[1] a their values.
Then remove the department field.

Approach 2-

Group all the employees on the basis of their department id.
Add new fields "id" and "name" to all the items of "departments" with the help of "department" field present in every first object of all items of "departments".
Now either use shift to get only necessary fields or use remove to remove unnecessary fields.

------------------------------------------------------------------------------

21.

Go to the "value" field of "rating" field of "restaurant" and put it in "Restaurant Rating". 

------------------------------------------------------------------------------

22.

Go to the "value" field of "rating" field of "restaurant" and put it in "Restaurant Rating" by using list with # to identify the level.

------------------------------------------------------------------------------

23.

Go to the "value" field of "rating" field of "restaurant" and put it in "Restaurant Rating" and "value" field of "address" of "restaurant" and put it in "Restaurant Country" by using list with # to identify the level.

------------------------------------------------------------------------------

24.

Go to the "value" field of "rating" field of "restaurant" and put it in "Restaurant Rating" and "value" field of "address" of "restaurant" and put it in "Restaurant Country" by using list with # to identify the level.

And go to "itemName" field of all "itemsList" list of "restaurant" and put it in "Dish Name". It will result in a list since "itemsList" contains more than one item and each item puts its "itemName" field to the same destination "Dish Name". 
Similarly visit "itemPrice" field of "itemsList" list of "restaurant" and put it in "Dish Price". It will also result in a list since "itemsList" contains more than one item and each item puts its "itemPrice" field to the same destination "Dish Price". 

------------------------------------------------------------------------------

25.

Follow the same steps as did in 24 question. But this time introduce a new list "Dishes" to put "itemsList" items. "Dish Name" and "Dish Price" won't form a list since they are different items of the list "Dishes". Use # to give levels properly.

------------------------------------------------------------------------------

26.

Create a new field "returnQuantityInv" for every item in the list with "modify-default-beta". It stores inverse of "returnQuantity" field. Then divide "returnPrice" with "returnQuantityInv" to get the amount as "Return Amount". And add this to "returnTax" to get the "Return Total Amount".

Then remove unnecessary fields like "returnQuantityInv" which are no longer needed.

------------------------------------------------------------------------------

27.

Follow the same steps as did in 26 to get "Return Amount" and "Return Total Amount" fields for every item respectively. There is a new field "country" for which we will shift its value indexwise. If value is "USA" then put it in 0th item with the new value as "United States of America". Same for country values "CA" -> "Cananda" and "" -> "NA". 

------------------------------------------------------------------------------

28.

Put "amount" field of every item of "returnItemAdjustments" list outside this list to "returnTax" field. It will form a list. Then in the next step, find the sum of this field to get total return tax.

------------------------------------------------------------------------------

29.

Go to every field of "person" present at different levels and their names by prepending "person" and inner fields like "address" to them with the help of &. For eg- in the "address" field, &2 refers to "person", &1 refers to "address" and & or &0 refers to "city", "state".
While in the "person" field, &1 refers to "person" and & or &0 refers to "name", "age". 

------------------------------------------------------------------------------

30.

Go to "person" map. Put its "name" field outside the map to "name". 
Go to "office" map inside of "employee" map. Put its fields "city" and "state" outside the map "employee" to "office_city" and "office_state" respectively. Use & with different levels to reference field names.

------------------------------------------------------------------------------

31.

Either we can create a new field "key" - "id_currency" for each item of "prices" with "modify-default-beta" and shift "value" field with "key" in the next step. 
Or we can shift "value" field to "idValue.currencyValue" map in the first step. It will separate currencies on the basis of their ids. Then in the next step shift the above field with "id_currencyValue".

------------------------------------------------------------------------------

32.

Just go to "value" field of all items of "data" list and shift it with "xValue.yValue". Items with same xValue will get grouped together.

------------------------------------------------------------------------------

33.

Shift "name" and "level" fields of all items of "hobbies" to "hobby_name" and "hobby_level" in the "hobbies" list.

Add new fields "id" and "name" to all the items of "hobbies" with their values given outside the "hobbies" list with "modify-default-beta".

Then either use "shift" to display only necessary data or "remove" to remove the unwanted data like "id" and "name" present outside the "hobbies" list.

Or we can combine all the above steps in just one step. Use shift to change "name" and "level" field names and "id" and "name" from 2nd level to insert it into all the items of "hobbies".

------------------------------------------------------------------------------

34.

Approach 1-

Firstly, Group all the "hobby" and "level" fields on the basis of the number(1,2) that appends them in a new "hobbies" map.
Then make this "hobbies" map a list by removing these numbers used as keys.
Now use hobby_* to select all the "hobby" fields (hobby_1, hobby_2) and level_* to select all the level fields (level_1, level_2) in the "hobbies" list to make them "name" and "level" fields respectively.

Approach 2-

Firstly we use hobby_* and level_* to select all (hobby_1, hobby_2) and (level_1, level_2) respectively and place them inside a new "hobbies" list by grouping them on the basis of numbers that appends them and making their new names as "name" and "level" .
In the next step, remove these numbers which are used as keys in the "hobbies" list since now they are not required.   

------------------------------------------------------------------------------
