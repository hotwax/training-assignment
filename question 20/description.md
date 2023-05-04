we want to create a list department which contains maps and that maps conatins id and name of department and a list named employee which contains maps, indide that we have name and age of employee.
In first shift operation, I have created a list called employee and added the name and age of employees and added that list into a map also added the id and name of department inside that map.
the map created previously is grouped by using id of department, i.e, that map key is id of that department.
After that we have grouped according to department thats why we got id and name as list wich contains same dta of department at every index. thats why I have used cardinaloty operation and converted that list into into single key-value pair.
Then in new operation, I have removed null values commming  in employee list.
then I have cretead a department list and put every index map into that list.