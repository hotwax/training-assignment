Number of operations - 4
1. shift
2. cardinality
3. modify-overwrite-beta
4. shift

Explaintion -
From the input json we have to create a map which department list in it
Which will have name and id in it, along with employees present over there.
Using 1st shift operations we will divide the information according to the department id.
We will apply cardinality one one on id and name.
After that we will use modify-overwrite-beta's recursivelySquashNulls to remove null values
Then using another shift operation we will add these values to a department list with required levels which is 2