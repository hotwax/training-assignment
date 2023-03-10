/* 

Customers:
+------+----------+---------+--------+------+
| CNUM | CNAME    | CITY    | RATING | SNUM |
+------+----------+---------+--------+------+
| 2001 | Hoffman  | London  |    100 | 1001 |
| 2002 | Giovanni | Rome    |    200 | 1003 |
| 2003 | Liu      | SanJose |    200 | 1002 |
| 2004 | Grass    | Berlin  |    300 | 1002 |
| 2006 | Clemens  | London  |    100 | 1001 |
| 2007 | Pereira  | Rome    |    100 | 1004 |
| 2008 | Cisneros | SanJose |    300 | 1007 |
+------+----------+---------+--------+------+

Orders:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3001 |   18.69 | 1990-03-10 | 2008 | 1007 |
| 3002 | 1900.10 | 1990-03-10 | 2007 | 1004 |
| 3003 |  767.19 | 1990-03-10 | 2001 | 1001 |
| 3005 | 5160.45 | 1990-03-10 | 2003 | 1002 |
| 3006 | 1098.16 | 1990-03-10 | 2008 | 1007 |
| 3007 |   75.75 | 1990-04-10 | 2004 | 1002 |
| 3008 | 4723.00 | 1990-05-10 | 2006 | 1001 |
| 3009 | 1713.23 | 1990-04-10 | 2002 | 1003 |
| 3010 | 1309.95 | 1990-06-10 | 2004 | 1002 |
| 3011 | 9891.88 | 1990-06-10 | 2006 | 1001 |
+------+---------+------------+------+------+

Salespeople:
+------+---------+-----------+------+
| SNUM | SNAME   | CITY      | COMM |
+------+---------+-----------+------+
| 1001 | Peel    | London    |   12 |
| 1002 | Serres  | SanJose   |   13 |
| 1003 | AxelRod | New York  |   10 |
| 1004 | Motika  | London    |   11 |
| 1007 | Rifkin  | Barcelona |   15 |
| 1008 | Fran    | London    |   25 |
+------+---------+-----------+------+

===============================================================================================

1) List all the columns of the Salespeople table. 

Expected Output: Only the names of the columns of the Salespeople table.

Approch: We can use table description or we can use show column.

Actual Output:
+-------+-------------+------+-----+---------+-------+
| Field | Type        | Null | Key | Default | Extra |
+-------+-------------+------+-----+---------+-------+
| SNUM  | int(11)     | NO   | PRI | NULL    |       |
| SNAME | varchar(45) | YES  |     | NULL    |       |
| CITY  | varchar(45) | YES  |     | NULL    |       |
| COMM  | int(11)     | YES  |     | NULL    |       |
+-------+-------------+------+-----+---------+-------+

===============================================================================================

2) List all customers with a rating of 100.

Approch: We can use where clause.

Expected Output: Query should give Hoffman, Clemens, Pereira data because they have 100 rating.

Actual Output: 
+------+---------+--------+--------+------+
| CNUM | CNAME   | CITY   | RATING | SNUM |
+------+---------+--------+--------+------+
| 2001 | Hoffman | London |    100 | 1001 |
| 2006 | Clemens | London |    100 | 1001 |
| 2007 | Pereira | Rome   |    100 | 1004 |
+------+---------+--------+--------+------+

===============================================================================================(doubt)

3) Find the largest order taken by each salesperson on each date.

Expected Output: Query should give three columns amount, date and saleperson_number in which list all the dates and the largest order by each salesperson if they ordered multiple times on same date.

Approch: We have to group the data by dates and filter the data by using max aggregate function. 

Actual Output: 

===============================================================================================

4) Arrange the Order table by descending customer number.

Expected Output: Query should give the data in descending order of customer number.

Approch: We can use order by clause.

Actual Output:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3001 |   18.69 | 1990-03-10 | 2008 | 1007 |
| 3006 | 1098.16 | 1990-03-10 | 2008 | 1007 |
| 3002 |  1900.1 | 1990-03-10 | 2007 | 1004 |
| 3008 | 4723.00 | 1990-05-10 | 2006 | 1001 |
| 3011 | 9891.88 | 1990-06-10 | 2006 | 1001 |
| 3007 |   75.75 | 1990-04-10 | 2004 | 1002 |
| 3010 | 1309.95 | 1990-06-10 | 2004 | 1002 |
| 3005 | 5160.45 | 1990-03-10 | 2003 | 1002 |
| 3009 | 1713.23 | 1990-04-10 | 2002 | 1003 |
| 3003 |  767.19 | 1990-03-10 | 2001 | 1001 |
+------+---------+------------+------+------+

===============================================================================================

5) Find which salespeople currently have orders in the order table.

Expected Output: Query should give the salesperson number and name who have orders in the order table.

Approch: We can use join clause.

Actual Output:
+------+---------+
| SNUM | SNAME   |
+------+---------+
| 1007 | Rifkin  |
| 1004 | Motika  |
| 1001 | Peel    |
| 1002 | Serres  |
| 1003 | AxelRod |
+------+---------+

===============================================================================================

6) List names of all customers matched with the salespeople serving them.

Expected Output: Query should give two columns SNAME and CNAME from Customers table join with Salespeople table.

Approch: We can use self join.

Actual Output:
+----------+---------+
| CNAME    | SNAME   |
+----------+---------+
| Hoffman  | Peel    |
| Giovanni | AxelRod |
| Liu      | Serres  |
| Grass    | Serres  |
| Clemens  | Peel    |
| Pereira  | Motika  |
| Cisneros | Rifkin  |
+----------+---------+

===============================================================================================

7) Find the names and numbers of all salespeople who have more than one customer.

Expected Output: Query should give two columns SNAME and SNUM from Salespeople table join with Customers table.

Approch: We can use Inner Join or Subquery with condition that counts the number of customer for salesperson whose count is greater than 1.

Actual Output:
+---------+------+
| SNAME   | SNUM |
+---------+------+
| Peel    | 1001 |
| Serres  | 1002 |
+---------+------+

===============================================================================================

8) Count the orders of each of the salespeople and output the results in descending order.

Expected Output: Query should give two columns SNUM and number of orders ordered in descending order.

Approch: We can use group by clause and order by clause.

Actual Output:
+--------------+------+
| NO_OF_ORDERS | SNUM |
+--------------+------+
|            3 | 1002 |
|            3 | 1001 |
|            2 | 1007 |
|            1 | 1003 |
|            1 | 1004 |
+--------------+------+

Edge Case: We can also list the salespeople who have 0 orders.

===============================================================================================(doubt)

9) List the customer table if and only if one or more of the customers in the Customer table are located in SanJose.

===============================================================================================

10) Match salespeople to customers according to what city they live in.

Expected Output: Query should give threa column SNAME, CNAME, CITY from Salespeople and Customers table.

Approch: We can inner join to get from both table by matching the cities.

Actual Output:
+--------+----------+---------+
| SNAME  | CNAME    | CITY    |
+--------+----------+---------+
| Peel   | Hoffman  | London  |
| Motika | Hoffman  | London  |
| Fran   | Hoffman  | London  |
| Serres | Liu      | SanJose |
| Peel   | Clemens  | London  |
| Motika | Clemens  | London  |
| Fran   | Clemens  | London  |
| Serres | Cisneros | SanJose |
+--------+----------+---------+

Edge Case: Not matched Salespeople and Customers are not included.

===============================================================================================

11) Find all the customers in SanJose who have a rating above 200.

Expected Output: Query should give two columns CNUM and CNAME.

Approch: We can use where clause.

Actual Output:
+------+----------+
| CNUM | CNAME    |
+------+----------+
| 2008 | Cisneros |
+------+----------+

===============================================================================================

12) List the names and commissions of all salespeople in London.

Expected Output: Query should give two columns SNAME and COMM from Salespeople table.

Approch: We can use where clause.

Actual Output:
+--------+------+
| SNAME  | COMM |
+--------+------+
| Peel   |   12 |
| Motika |   11 |
| Fran   |   25 |
+--------+------+

===============================================================================================

13) List all the orders of Salesperson Motika from the orders table.    

Expected Output: Query should give all the columns from Orders table.

Approch: We can use where clause and a subquery to get SNUM of Motika form Salespeople table.

Actual Output:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3002 | 1900.10 | 1990-03-10 | 2007 | 1004 |
+------+---------+------------+------+------+

===============================================================================================

14) Find all customers who booked orders on October 3.

Expected Output: Query should give two columns CNUM and CNAME.

Approch: We can use inner join and like condition to match date pattern.

Actual Output:
+------+----------+
| CNUM | CNAME    |
+------+----------+
| 2008 | Cisneros |
| 2007 | Pereira  |
| 2001 | Hoffman  |
| 2003 | Liu      |
| 2008 | Cisneros |
+------+----------+

===============================================================================================(multiple ways)

15) Give the sums of the amounts from the Orders table, grouped by date, eliminating all those dates where the SUM was not at least 2000 above  the maximum Amount.

Expected Output: Empty Set because there is not any total of amount which is 2000 more than max order amount.

Approch: We can use group by clause and having clause.

Actual Output:
Empty set (0.00 sec)

===============================================================================================

16) Select all orders that had amounts that were greater than at least one of the orders from October 6.

Expected Output: Query should give one column ONUM.

Approch: We can use subquery and where clause.

Actual Output:
+------+
| ONUM |
+------+
| 3002 |
| 3005 |
| 3008 |
| 3009 |
| 3011 |
+------+

===============================================================================================

17) Write a query that uses the EXISTS operator to extract all salespeople who have customers with a rating of 300.

Expected Output: Query should give two columns SNUM and SNAME.

Approch: We can use exists operator and inner join and exist operator and subquery.

Actual Output:
+------+--------+-----------+------+
| SNUM | SNAME  | CITY      | COMM |
+------+--------+-----------+------+
| 1002 | Serres | SanJose   |   13 |
| 1007 | Rifkin | Barcelona |   15 |
+------+--------+-----------+------+

===============================================================================================

18) Find all customers whose cnum is 1000 above the snum of Serres.

Expected Output: Query should give two columns CNUM and CNAME.

Approch: We can use where clause and subquery or inner join.

Actual Output:
+----------+------+
| CNAME    | CNUM |
+----------+------+
| Liu      | 2003 |
| Grass    | 2004 |
| Clemens  | 2006 |
| Pereira  | 2007 |
| Cisneros | 2008 |
+----------+------+

===============================================================================================(Wrong Question)

19) Give the salespeople’s commissions as percentages instead of decimal numbers.

Approach: Currently data given in the table is already in percentage but if they are in decimal than we can multiple each value with 100 and can show.

===============================================================================================(doubt)

20) Find the largest order taken by each salesperson on each date, eliminating those Maximum orders, which are less than 3000.

===============================================================================================

21) List all the largest orders for October 3, for each salesperson.

Expected Output: Query should give three columns SNAME, SNUM and AMT.

Approch: We can use group by clause and where clause.

Actual Output:
+----------+------+
| MAX(AMT) | SNUM |
+----------+------+
|   767.19 | 1001 |
|  5160.45 | 1002 |
|  1900.10 | 1004 |
|  1098.16 | 1007 |
+----------+------+

===============================================================================================

22) Find all customers located in cities where Serres has customers.

Expected Output: Query should give two columns CNUM and CNAME.

Approch: We can use where clause and subquery.

Actual Output:
+------+----------+
| CNUM | CNAME    |
+------+----------+
| 2003 | Liu      |
| 2008 | Cisneros |
| 2004 | Grass    |
+------+----------+

===============================================================================================

23) Select all customers with a rating above 200. 

Expected Output: Query should give two columns CNUM and CNAME.

Approch: We can use where clause.

Actual Output:
+------+----------+
| CNUM | CNAME    |
+------+----------+
| 2004 | Grass    |
| 2008 | Cisneros |
+------+----------+

===============================================================================================

24) Count the number of salespeople currently having orders in the orders table.

Expected Output: Query should give one column COUNT.

Approch: We can use count function and distinct function.

Actual Output:
+-----------------------+
| COUNT( DISTINCT SNUM) |
+-----------------------+
|                     5 |
+-----------------------+

===============================================================================================

25) Write a query that produces all customers serviced by salespeople with a commission above 12%. Output the customer’s name, salesperson’s name and the salesperson’s rate of commission.

Expected Output: Query should give three columns CNAME, SNAME and COMM.

Approch: We can use where clause and inner join.

Actual Output:
+----------+--------+------+
| CNAME    | SNAME  | COMM |
+----------+--------+------+
| Liu      | Serres |   13 |
| Grass    | Serres |   13 |
| Cisneros | Rifkin |   15 |
+----------+--------+------+

===============================================================================================

26) Find salespeople who have multiple customers.

Expected Output: Query should give two columns SNUM and SNAME.

Approch: We can use group by clause and subquery.

Actual Output:
+------+--------+---------+------+
| SNUM | SNAME  | CITY    | COMM |
+------+--------+---------+------+
| 1001 | Peel   | London  |   12 |
| 1002 | Serres | SanJose |   13 |
+------+--------+---------+------+

===============================================================================================

27) Find salespeople with customers located in their own cities.

Expected Output: Query should give two columns SNUM and SNAME.

Approch: We can use where clause and inner join.

+------+--------+---------+------+---------+
| SNUM | SNAME  | CITY    | CNUM | CNAME   |
+------+--------+---------+------+---------+
| 1001 | Peel   | London  | 2001 | Hoffman |
| 1002 | Serres | SanJose | 2003 | Liu     |
| 1001 | Peel   | London  | 2006 | Clemens |
+------+--------+---------+------+---------+

===============================================================================================

28) Find all salespeople whose name starts with ‘P’ and fourth character is ‘I’.

Expected Output: Query should give empty set because no name is like this pattern exist in table.

Approch: We can use where clause and like operator.

Actual Output:
Empty set

===============================================================================================

29) Write a query that uses a subquery to obtain all orders for the customer named ‘Cisneros’.

Expected Output: Query should give one column ONUM.

Approch: We can use where clause and subquery.

Actual Output:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3001 |   18.69 | 1990-03-10 | 2008 | 1007 |
| 3006 | 1098.16 | 1990-03-10 | 2008 | 1007 |
+------+---------+------------+------+------+

===============================================================================================

30) Find the largest orders for Serres and Rifkin.

Expected Output: Query should give three columns SNAME and AMT.

Approch: We can use group by clause, max function, join and where clause.

Actual Output:
+--------+---------+
| SNAME  | MAX_AMT |
+--------+---------+
| Serres | 5160.45 |
| Rifkin | 1098.16 |
+--------+---------+

===============================================================================================

31) Sort the salespeople table in the following order: snum, sname, commission, city.

Expected Output: Query should give four columns SNUM, SNAME, COMM and CITY.

Approch: We can use order by clause.

Actual Output:
+------+---------+-----------+------+
| SNUM | SNAME   | CITY      | COMM |
+------+---------+-----------+------+
| 1001 | Peel    | London    |   12 |
| 1002 | Serres  | SanJose   |   13 |
| 1003 | AxelRod | New York  |   10 |
| 1004 | Motika  | London    |   11 |
| 1007 | Rifkin  | Barcelona |   15 |
| 1008 | Fran    | London    |   25 |
+------+---------+-----------+------+

===============================================================================================

32) Select all customers whose names fall in between ‘A’ and ‘G’ alphabetical range.

Expected Output: Query should give two columns CNUM and CNAME.

Approch: We can use where clause and between operator.

Actual Output:
+------+----------+
| CNUM | CNAME    |
+------+----------+
| 2006 | Clemens  |
| 2008 | Cisneros |
+------+----------+

===============================================================================================

33) Select all the possible combinations of customers you can assign.

Expected Output: Query should give two columns CNAME and CNAME;

Approch: We can use where clause and self join.

Actual Output:
+---------+---------+
| CNAME   | CNAME   |
+---------+---------+
| Clemens | Hoffman |
| Grass   | Liu     |
| Liu     | Grass   |
| Hoffman | Clemens |
+---------+---------+

===============================================================================================

34) Select all orders that are greater than the average for October 4.

Expected Output: Query should give one column ONUM.

Approch: We can use where clause and subquery.

Actual Output:
+------+
| ONUM |
+------+
| 3002 |
| 3005 |
| 3006 |
| 3008 |
| 3009 |
| 3010 |
| 3011 |
+------+

===============================================================================================

35) Write a select command using correlated subquery that selects the names and numbers of all customers with ratings equal to the maximum for their city.

Expected Output: Query should give two columns CNAME and CNUM.

Approch: We can use where clause and subquery.

Actual Output:
+------+----------+--------+---------+
| CNUM | CNAME    | RATING | CITY    |
+------+----------+--------+---------+
| 2001 | Hoffman  |    100 | London  |
| 2002 | Giovanni |    200 | Rome    |
| 2004 | Grass    |    300 | Berlin  |
| 2006 | Clemens  |    100 | London  |
| 2008 | Cisneros |    300 | SanJose |
+------+----------+--------+---------+

===============================================================================================

36) Write a query that totals the orders for each day and places the results in descending order.

Expected Output: Query should give two columns ODATE and TOTAL.

Approch: We can use group by clause and order by clause.

Actual Output:
+----------+------------+
| SUM(AMT) | ODATE      |
+----------+------------+
| 11201.83 | 1990-06-10 |
|  8944.59 | 1990-03-10 |
|  4723.00 | 1990-05-10 |
|  1788.98 | 1990-04-10 |
+----------+------------+

===============================================================================================

37) Write a select command that produces the rating followed by the name of each customer in SanJose.

Expected Output: Query should give two columns RATING and CNAME.

Approch: We can use where clause.

Actual Output:
+--------+----------+
| RATING | CNAME    |
+--------+----------+
|    200 | Liu      |
|    300 | Cisneros |
+--------+----------+

===============================================================================================

38) Find all orders with amounts smaller than any amount for a customer in SanJose. 

Expected Output: Query should give two columns ONUM and AMT.

Approch: We can use where clause and subquery.

Actual Output:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3001 |   18.69 | 1990-03-10 | 2008 | 1007 |
| 3002 | 1900.10 | 1990-03-10 | 2007 | 1004 |
| 3003 |  767.19 | 1990-03-10 | 2001 | 1001 |
| 3006 | 1098.16 | 1990-03-10 | 2008 | 1007 |
| 3007 |   75.75 | 1990-04-10 | 2004 | 1002 |
| 3008 | 4723.00 | 1990-05-10 | 2006 | 1001 |
| 3009 | 1713.23 | 1990-04-10 | 2002 | 1003 |
| 3010 | 1309.95 | 1990-06-10 | 2004 | 1002 |
+------+---------+------------+------+------+

===============================================================================================

39) Find all orders with above average amounts for their customers.

Expected Output: Query should give two columns ONUM and AMT.

Approch: We can use where clause and subquery.

Actual Output:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3005 | 5160.45 | 1990-03-10 | 2003 | 1002 |
| 3008 | 4723.00 | 1990-05-10 | 2006 | 1001 |
| 3011 | 9891.88 | 1990-06-10 | 2006 | 1001 |
+------+---------+------------+------+------+

===============================================================================================

40) Write a query that selects the highest rating in each city. 

Expected Output: Query should give two columns CITY and MAX_RATING.

Approch: We can use group by clause and max aggregate function.

Actual Output:
+-------------+---------+
| MAX(RATING) | CITY    |
+-------------+---------+
|         300 | Berlin  |
|         100 | London  |
|         200 | Rome    |
|         300 | SanJose |
+-------------+---------+

===============================================================================================

41) Write a query that calculates the amount of the salesperson’s commission on each order by a customer with a rating above 100.00.

Expected Output: Query should give two columns ONUM and COMM.

Approch: We can use where clause and subquery.

Actual Output:
+---------+-----------------+---------+------------+------+
| SNAME   | COMMISSION IN % | AMOUNT  | COMMISSION | ONUM |
+---------+-----------------+---------+------------+------+
| Rifkin  |              15 |   18.69 |   2.803500 | 3001 |
| Serres  |              13 | 5160.45 | 670.858500 | 3005 |
| Rifkin  |              15 | 1098.16 | 164.724000 | 3006 |
| Serres  |              13 |   75.75 |   9.847500 | 3007 |
| AxelRod |              10 | 1713.23 | 171.323000 | 3009 |
| Serres  |              13 | 1309.95 | 170.293500 | 3010 |
+---------+-----------------+---------+------------+------+

===============================================================================================

42) Count the customers with ratings above SanJose’s average. 

Expected Output: Query should give one column COUNT.

Approch: We can use where clause and subquery.

Actual Output:
+-------------+
| COUNT(CNUM) |
+-------------+
|           2 |
+-------------+

===============================================================================================

43) Find all salespeople that are located in either Barcelona or London.

Expected Output: Query should give data from Salespeople table.

Approch: We can use where clause and subquery.

Actual Output:
+------+--------+-----------+------+
| SNUM | SNAME  | CITY      | COMM |
+------+--------+-----------+------+
| 1001 | Peel   | London    |   12 |
| 1004 | Motika | London    |   11 |
| 1007 | Rifkin | Barcelona |   15 |
| 1008 | Fran   | London    |   25 |
+------+--------+-----------+------+

===============================================================================================

44) Find all salespeople with only one customer. 

Expected Output: Query should give two columns SNAME and SNUM.

Approch: We can use where clause and subquery.

Actual Output:
+------+---------+----------+------+
| SNUM | SNAME   | CITY     | COMM |
+------+---------+----------+------+
| 1003 | AxelRod | New York |   10 |
| 1004 | Motika  | London   |   11 |
+------+---------+----------+------+

===============================================================================================

45) Write a query that joins the Customer table to itself to find all pairs or customers served by a single salesperson.

Expected Output: Query should give two columns CNAME and CNAME in which we have pairs of customers serving by same salesperson.

Approch: We can use where clause and Self Join.

Actual Output:
+---------+---------+
| CNAME   | CNAME   |
+---------+---------+
| Clemens | Hoffman |
| Grass   | Liu     |
| Liu     | Grass   |
| Hoffman | Clemens |
+---------+---------+

===============================================================================================

46) Write a query that will give you all orders for more than $1000.00.

Expected Output: Query should give two columns ONUM and AMT.

Approch: We can use where clause.

Actual Output:
+------+---------+
| ONUM | AMT     |
+------+---------+
| 3002 | 1900.10 |
| 3005 | 5160.45 |
| 3006 | 1098.16 |
| 3008 | 4723.00 |
| 3009 | 1713.23 |
| 3010 | 1309.95 |
| 3011 | 9891.88 |
+------+---------+

===============================================================================================

47) Write a query that lists each order number followed by the name of the customer who made that order.

Expected Output: Query should give two columns ONUM and CNAME.

Approch: We can use where clause and inner join.

Actual Output:
+------+----------+
| ONUM | CNAME    |
+------+----------+
| 3001 | Cisneros |
| 3002 | Pereira  |
| 3003 | Hoffman  |
| 3005 | Liu      |
| 3006 | Cisneros |
| 3007 | Grass    |
| 3008 | Clemens  |
| 3009 | Giovanni |
| 3010 | Grass    |
| 3011 | Clemens  |
+------+----------+

===============================================================================================

48) Write a query that selects all the customers whose ratings are equal to or greater than ANY(in the SQL sense) of ‘Serres’. 

Expected Output: Query should give data from Customers table using subquery tha give data from Salespeople table.

Approch: We can use where clause, any and subquery.

Actual Output:
+------+----------+---------+--------+------+
| CNUM | CNAME    | CITY    | RATING | SNUM |
+------+----------+---------+--------+------+
| 2002 | Giovanni | Rome    |    200 | 1003 |
| 2003 | Liu      | SanJose |    200 | 1002 |
| 2004 | Grass    | Berlin  |    300 | 1002 |
| 2008 | Cisneros | SanJose |    300 | 1007 |
+------+----------+---------+--------+------+

===============================================================================================

49) Write two queries that will produce all orders taken on October 3 or October 4.

Expected Output: Query should give data from Orders table.

Approch: We can use where clause to match date and use union to merge the data coming from two queries.

Actual Output:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3007 |   75.75 | 1990-04-10 | 2004 | 1002 |
| 3009 | 1713.23 | 1990-04-10 | 2002 | 1003 |
| 3001 |   18.69 | 1990-03-10 | 2008 | 1007 |
| 3002 | 1900.10 | 1990-03-10 | 2007 | 1004 |
| 3003 |  767.19 | 1990-03-10 | 2001 | 1001 |
| 3005 | 5160.45 | 1990-03-10 | 2003 | 1002 |
| 3006 | 1098.16 | 1990-03-10 | 2008 | 1007 |
+------+---------+------------+------+------+

===============================================================================================

50) Find only those customers whose ratings are higher than every customer in Rome. 

Expected Output: Query should give data from Customers table.

Approch: We can use where clause and subquery. First we get the maximum rating of customers living in Rome then compare it with others.

Actual Output:
+------+----------+---------+--------+------+
| CNUM | CNAME    | CITY    | RATING | SNUM |
+------+----------+---------+--------+------+
| 2004 | Grass    | Berlin  |    300 | 1002 |
| 2008 | Cisneros | SanJose |    300 | 1007 |
+------+----------+---------+--------+------+

===============================================================================================

51) Write a query on the Customers table whose output will exclude all customers with a rating <= 100.00, unless they are located in Rome. 

Expected Output: Query should give data from Customers table.

Approch: We can use where clause by which we apply this two conditions of question.

Actual Output:
+------+----------+---------+--------+------+
| CNUM | CNAME    | CITY    | RATING | SNUM |
+------+----------+---------+--------+------+
| 2002 | Giovanni | Rome    |    200 | 1003 |
| 2003 | Liu      | SanJose |    200 | 1002 |
| 2004 | Grass    | Berlin  |    300 | 1002 |
| 2007 | Pereira  | Rome    |    100 | 1004 |
| 2008 | Cisneros | SanJose |    300 | 1007 |
+------+----------+---------+--------+------+

===============================================================================================

52) Find all rows from the customer’s table for which the salesperson number is 1001. 

Expected Output: Query should give data from Customers table.

Approch: We can use where clause for comparing SNUM.

Actual Output:
+------+---------+--------+--------+------+
| CNUM | CNAME   | CITY   | RATING | SNUM |
+------+---------+--------+--------+------+
| 2001 | Hoffman | London |    100 | 1001 |
| 2006 | Clemens | London |    100 | 1001 |
+------+---------+--------+--------+------+

===============================================================================================

53) Find the total amount in orders for each salesperson where their total of amounts are greater than the amount of the largest order in the table. 

Expected Output: Query should give data from Orders table.

Approch: We can use where clause and subquery. First we get the maximum amount of order then compare it with others.

Actual Output:
+----------+------+
| Total    | SNUM |
+----------+------+
| 15382.07 | 1001 |
+----------+------+

===============================================================================================

54) Write a query that selects all orders save those with zeroes or NULL in the amount file. 

Expected Output: Query should give data from Orders table if any row contains 0 or null in AMT column.

Approch: We can use where clause and isnull function.

Actual Output:
Empty set

===============================================================================================(doubt)

55) Produce all combinations of salespeople and customer names such that the former precedes the latter alphabetically, and the latter has a rating of less than 200. 

===============================================================================================

56) Find all salespeople name and commission. 

Expected Output: Query should give data from Salespeople table.

Approch: We can use select command directly to get this two column form Saleseople table.

Actual Output:
+---------+------+
| SNAME   | COMM |
+---------+------+
| Peel    |   12 |
| Serres  |   13 |
| AxelRod |   10 |
| Motika  |   11 |
| Rifkin  |   15 |
| Fran    |   25 |
+---------+------+

===============================================================================================

57) Write a query that produces the names and cities of all customers with the same rating as Hoffman. Write the query using Hoffman’s cnum rather than his rating, so that it would still be usable if his rating is changed.

Expected Output: Query should give data from Customers table and give two columns CNAME and CITY.

Approch: We can use where clause and subquery.

Actual Output:
+---------+--------+
| CNAME   | CITY   |
+---------+--------+
| Hoffman | London |
| Clemens | London |
| Pereira | Rome   |
+---------+--------+

===============================================================================================

58) Find all salespeople for whom there are customers that follow them in alphabetical order. 

Expected Output: Query should give two columns SNAME and CNAME which shows CNAME are followed by SNAME alphabetically.

Approch: We can use where clause and inner join.

Actual Output:
+---------+----------+
| SNAME   | CNAME    |
+---------+----------+
| AxelRod | Giovanni |
| Motika  | Pereira  |
+---------+----------+

===============================================================================================

59) Write a query that produces the names and ratings of all customers who have average orders. 

Expected Output: Query should give data from Customers table and give two columns CNAME and RATING.

Approch: We can use where clause and a  subquery to get average of AMT from order table.

Actual Output:
+---------+--------+
| CNAME   | RATING |
+---------+--------+
| Liu     |    200 |
| Clemens |    100 |
+---------+--------+

===============================================================================================

60) Find the SUM of all Amounts from the orders table. 

Expected Output: Query should give data from Orders table and give one column AMT.

Approch: We can use SUM aggregate function to get total of all amounts of order table.

Actual Output:
+----------+
| SUM(AMT) |
+----------+
| 26658.40 |
+----------+

===============================================================================================

61) Write a SELECT command that produces the order number, amount, and the date from rows in the order table. 

Expected Output: Query should give data from Orders table and give three columns ONUM, AMT and ODATE.

Approch: We can use select command directly to get this three column form Orders table.

Actual Output:
+------+---------+------------+
| ONUM | AMT     | ODATE      |
+------+---------+------------+
| 3001 |   18.69 | 1990-03-10 |
| 3002 | 1900.10 | 1990-03-10 |
| 3003 |  767.19 | 1990-03-10 |
| 3005 | 5160.45 | 1990-03-10 |
| 3006 | 1098.16 | 1990-03-10 |
| 3007 |   75.75 | 1990-04-10 |
| 3008 | 4723.00 | 1990-05-10 |
| 3009 | 1713.23 | 1990-04-10 |
| 3010 | 1309.95 | 1990-06-10 |
| 3011 | 9891.88 | 1990-06-10 |
+------+---------+------------+

===============================================================================================

62) Count the number of non NULL rating fields in the Customers table (including repeats). 

Expected Output: Query should give data from Customers table and give one column COUNT.

Approch: We can use COUNT aggregate function to get total of all non null rating fields from Customers table.

Actual Output:
+---------------+
| COUNT(RATING) |
+---------------+
|             7 |
+---------------+

===============================================================================================

63) Write a query that gives the names of both the salesperson and the customer for each order after the order number. 

Expected Output: Query should give data from all tables and give three columns ONUM, SNAME and CNAME.

Approch: We can use where clause and left join to compare table with CNUM and SNUM as common column.

+------+------------+---------+----------+
| ONUM | ODATE      | SNAME   | CNAME    |
+------+------------+---------+----------+
| 3001 | 1990-03-10 | Rifkin  | Cisneros |
| 3002 | 1990-03-10 | Motika  | Pereira  |
| 3003 | 1990-03-10 | Peel    | Hoffman  |
| 3005 | 1990-03-10 | Serres  | Liu      |
| 3006 | 1990-03-10 | Rifkin  | Cisneros |
| 3007 | 1990-04-10 | Serres  | Grass    |
| 3008 | 1990-05-10 | Peel    | Clemens  |
| 3009 | 1990-04-10 | AxelRod | Giovanni |
| 3010 | 1990-06-10 | Serres  | Grass    |
| 3011 | 1990-06-10 | Peel    | Clemens  |
+------+------------+---------+----------+

===============================================================================================

64) List the commissions of all salespeople servicing customers in London.

Expected Output: Query should give data from Salespeople table and give two columns SNAME and COMM.

Approch: We can use where clause and inner join to compare table with SNUM and SNUM as common column.

Actual Output:
+-------+------+
| SNAME | COMM |
+-------+------+
| Peel  |   12 |
+-------+------+

===============================================================================================

65) Write a query using ANY or ALL that will find all salespeople who have no customers located in their city. 

Expected Output: Query should give data from Salespeople table and give two columns SNAME and CITY.

Approch: We can use where clause, all to compare and to get the required data.

Actual Output:
+------+---------+
| SNUM | SNAME   |
+------+---------+
| 1003 | AxelRod |
| 1004 | Motika  |
| 1007 | Rifkin  |
| 1008 | Fran    |
+------+---------+

===============================================================================================

66) Write a query using the EXISTS operator that selects all salespeople with customers located in their cities who are not assigned to them. 

Expected Output: Query should give data from Salespeople table by using exist condition on data get by a subquery.

Approch: We can use where clause, exists to compare and a subquery.

Actual Output:
+------+--------+---------+------+
| SNUM | SNAME  | CITY    | COMM |
+------+--------+---------+------+
| 1002 | Serres | SanJose |   13 |
| 1004 | Motika | London  |   11 |
| 1008 | Fran   | London  |   25 |
+------+--------+---------+------+

===============================================================================================

67) Write a query that selects all customers serviced by Peel or Motika. (Hint: The snum field relates the 2 tables to one another.) 

Expected Output: Query should give data from Customers table and give two columns CNAME and CNUM.

Approch: We can use where clause and inner join to compare table with SNUM and SNUM as common column.

Actual Output:
+------+---------+--------+--------+------+
| CNUM | CNAME   | CITY   | RATING | SNUM |
+------+---------+--------+--------+------+
| 2001 | Hoffman | London |    100 | 1001 |
| 2006 | Clemens | London |    100 | 1001 |
| 2007 | Pereira | Rome   |    100 | 1004 |
+------+---------+--------+--------+------+

===============================================================================================

68) Count the number of salespeople registering orders for each day. (If a salesperson has more than one order on a given day, he or she should be counted only once.) 

Expected Output: Query should give data from Orders table and give two columns ODATE and COUNT.

Approch: We can use Count and distinct function to get required data and group by date.

Actual Output:
+----------------------+------------+
| COUNT(DISTINCT SNUM) | ODATE      |
+----------------------+------------+
|                    4 | 1990-03-10 |
|                    2 | 1990-04-10 |
|                    1 | 1990-05-10 |
|                    2 | 1990-06-10 |
+----------------------+------------+

===============================================================================================

69) Find all orders attributed to salespeople who are located in London.

Expected Output: Query should give data from Salespeople table by gettind data of Salespeople from order table.

Approch: We can use subquery to get SNUM of peopel who live in London then can get data from order table.

Actual Output:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3002 | 1900.10 | 1990-03-10 | 2007 | 1004 |
| 3003 |  767.19 | 1990-03-10 | 2001 | 1001 |
| 3008 | 4723.00 | 1990-05-10 | 2006 | 1001 |
| 3011 | 9891.88 | 1990-06-10 | 2006 | 1001 |
+------+---------+------------+------+------+

===============================================================================================

70) Find all orders with amounts smaller than any amount for a customer in SanJose.

Expected Output: Query should give data from Orders table and give two columns ONUM and AMT.

Approch: We can use where clause, any and subquery to get maximum amount by customer live in Sanjose.

Actual Output:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3001 |   18.69 | 1990-03-10 | 2008 | 1007 |
| 3002 | 1900.10 | 1990-03-10 | 2007 | 1004 |
| 3006 | 1098.16 | 1990-03-10 | 2008 | 1007 |
| 3007 |   75.75 | 1990-04-10 | 2004 | 1002 |
| 3009 | 1713.23 | 1990-04-10 | 2002 | 1003 |
| 3010 | 1309.95 | 1990-06-10 | 2004 | 1002 |
+------+---------+------------+------+------+

===============================================================================================

71) Find all salespeople who have customers with more than one current order. 

Expected Output: Query should give data from Salespeople table.

Approch: We can use where clause, group by and having or subqueries to get required data.

Actual Output:
+------+--------+-----------+------+
| SNUM | SNAME  | CITY      | COMM |
+------+--------+-----------+------+
| 1002 | Serres | SanJose   |   13 |
| 1001 | Peel   | London    |   12 |
| 1007 | Rifkin | Barcelona |   15 |
+------+--------+-----------+------+

===============================================================================================

72) Write a query that extracts from the customer’s table every customer assigned to a salesperson, who is currently having at least one another customer(besides the customer being selected) with orders in the Orders Table.

Expected Output: Query should give data from Customers table and give two columns CNAME and CNUM.

Approch: We can use sub queries to get data of salespeople who have more then one customers in order table and then can select data from customers tale.

Actual Output:
+------+---------+---------+--------+------+
| CNUM | CNAME   | CITY    | RATING | SNUM |
+------+---------+---------+--------+------+
| 2001 | Hoffman | London  |    100 | 1001 |
| 2006 | Clemens | London  |    100 | 1001 |
| 2003 | Liu     | SanJose |    200 | 1002 |
| 2004 | Grass   | Berlin  |    300 | 1002 |
+------+---------+---------+--------+------+

===============================================================================================

73) Write a query on the customer’s table that will find the highest rating in each city. Put the output in this form: for the city (city), the highest rating is (rating). 

Expected Output: Query should give data from Customers table and give two columns CITY and RATING.

Approch: We can use where clause, group by and max function to get data from Customers table.

Actual Output:
+--------------+--------------------+
| For the city | The highest rating |
+--------------+--------------------+
| Berlin       |                300 |
| London       |                100 |
| Rome         |                200 |
| SanJose      |                300 |
+--------------+--------------------+

===============================================================================================

74) Write a query that will produce the snum values of all salespeople with orders, having amt greater than 1000 in the Orders Table(without repeats).

Expected Output: Query should give data from Salespeople table and give one column SNUM.

Approch: We can directly use select statement with where clause with distinct function to get data from order table.

Actual Output:
+------+
| SNUM |
+------+
| 1004 |
| 1002 |
| 1007 |
| 1001 |
| 1003 |
+------+

===============================================================================================

75) Write a query that lists customers in a descending order of rating. Output the rating field first, followed by the customer’s names and numbers. 

Expected Output: Query should give data from Customers table and give three columns RATING, CNAME and CNUM.

Approch: We can use order by clause to arrange data by rating and get data from Customers table.

Actual Output:
+--------+----------+------+
| RATING | CNAME    | CNUM |
+--------+----------+------+
|    300 | Grass    | 2004 |
|    300 | Cisneros | 2008 |
|    200 | Giovanni | 2002 |
|    200 | Liu      | 2003 |
|    100 | Hoffman  | 2001 |
|    100 | Clemens  | 2006 |
|    100 | Pereira  | 2007 |
+--------+----------+------+

===============================================================================================

76) Find the average commission for salespeople in London. 

Expected Output: Query should give data from Salespeople table and give one column COMM.

Approch: We can use avg function and where clause to get data from salespeople table.

Actual Output:
+-----------+
| AVG(COMM) |
+-----------+
|   16.0000 |
+-----------+

===============================================================================================

77) Find all orders credited to the same salesperson who services Hoffman.(cnum 2001). 

Expected Output: Query should give data from Orders table and give two columns ONUM and AMT.

Approch: We can use in and subquery to get data from order table.

Actual Output:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3003 |  767.19 | 1990-03-10 | 2001 | 1001 |
| 3008 | 4723.00 | 1990-05-10 | 2006 | 1001 |
| 3011 | 9891.88 | 1990-06-10 | 2006 | 1001 |
+------+---------+------------+------+------+

===============================================================================================

78) Find all salespeople whose commission is in between 0.10 and 0.12(both inclusive). 

Expected Output: Query should give data from Salespeople table.

Approch: We can use where clause and between operator to get data from salespeople table.

Actual Output:
+------+---------+----------+------+
| SNUM | SNAME   | CITY     | COMM |
+------+---------+----------+------+
| 1001 | Peel    | London   |   12 |
| 1003 | AxelRod | New York |   10 |
| 1004 | Motika  | London   |   11 |
+------+---------+----------+------+

===============================================================================================

79) Write a query that will give you the names and cities of all salespeople in London with a commission above 0.10.

Expected Output: Query should give data from Salespeople table and give two columns SNAME and CITY.

Approch: We can use where clause and and operator to get data from salespeople table.

Actual Output:
+--------+--------+
| SNAME  | CITY   |
+--------+--------+
| Peel   | London |
| Motika | London |
| Fran   | London |
+--------+--------+

===============================================================================================

80) Write a query that selects each customer’s smallest order. 

Expected Output: Query should give data from order table with two column minimum amount and CNUM.

Approch: We can use where clause, group by and min function to get data from order table.

Actual Output:
+----------+------+
| MIN(AMT) | CNUM |
+----------+------+
|   767.19 | 2001 |
|  1713.23 | 2002 |
|  5160.45 | 2003 |
|    75.75 | 2004 |
|  4723.00 | 2006 |
|  1900.10 | 2007 |
|    18.69 | 2008 |
+----------+------+

===============================================================================================

81) Write a query that selects the first customer in alphabetical order whose name begins with ‘G’. 

Expected Output: Query should give data from Customers table and give one column CNAME.

Approch: We can use min function to get lexicolgraphical smallest name startw with G.

Actual Output:
+------------+
| MIN(CNAME) |
+------------+
| Giovanni   |
+------------+

===============================================================================================

82) Write a query that counts the number of different non NULL city values in the customers table. 

Expected Output: Query should give data from Customers table and give one column Count of distinct CITY.

Approch: We can use count function and distinct operator to get data from Customers table.

Actual Output:
+----------------------+
| COUNT(DISTINCT CITY) |
+----------------------+
|                    4 |
+----------------------+

===============================================================================================

83) Find the average amount from the Orders Table. 

Expected Output: Query should give data from Orders table and give one column AVG(AMT).

Approch: We can use avg function to get data from Orders table.

Actual Output:
+-------------+
| AVG(AMT)    |
+-------------+
| 2665.840000 |
+-------------+

===============================================================================================

84) Find all customers who are not located in SanJose and whose rating is above 200. 

Expected Output: Query should give data from Customers table and give detail of customers.

Approch: We can use where clause and and operator to get data from Customers table.

Actual Output:
+------+-------+--------+--------+------+
| CNUM | CNAME | CITY   | RATING | SNUM |
+------+-------+--------+--------+------+
| 2004 | Grass | Berlin |    300 | 1002 |
+------+-------+--------+--------+------+

===============================================================================================

85) Give a simpler way to write this query.SELECT snum, sname, city, comm FROM salespeople WHERE (comm > 0.12 OR comm < 0.14); 

Expected Output: Query should give data from Salespeople table and give detail of salespeople.

Approch: We can use where asteric to make this query simpler.

Actual Output:
+------+---------+-----------+------+
| SNUM | SNAME   | CITY      | COMM |
+------+---------+-----------+------+
| 1001 | Peel    | London    |   12 |
| 1002 | Serres  | SanJose   |   13 |
| 1003 | AxelRod | New York  |   10 |
| 1004 | Motika  | London    |   11 |
| 1007 | Rifkin  | Barcelona |   15 |
| 1008 | Fran    | London    |   25 |
+------+---------+-----------+------+

===============================================================================================

86) Which salespersons attend to customers not in the city they have been assigned to? 

Expected Output: Query should give data from Salespeople table and give detail of salespeople.

Approch: We can use a subquery which will give us data from customers table in which which we can check SNUM to get data from salespeople table.

Actual Output:
+------+---------+-----------+------+
| SNUM | SNAME   | CITY      | COMM |
+------+---------+-----------+------+
| 1003 | AxelRod | New York  |   10 |
| 1002 | Serres  | SanJose   |   13 |
| 1004 | Motika  | London    |   11 |
| 1007 | Rifkin  | Barcelona |   15 |
+------+---------+-----------+------+

===============================================================================================

87) Which salespeople get commission greater than 0.11 are serving customers rated less than 250? 

Expected Output: Query should give data from Salespeople table and give detail of salespeople.

Approch: We can use where clause, in, and and operator to get data from Salespeople table.

Actual Output:
+------+--------+---------+------+
| SNUM | SNAME  | CITY    | COMM |
+------+--------+---------+------+
| 1001 | Peel   | London  |   12 |
| 1002 | Serres | SanJose |   13 |
+------+--------+---------+------+

===============================================================================================(doubt)

88) Which salespeople have been assigned to the same city but get different commission percentages? 

===============================================================================================(doubt)

89) Which salesperson has earned the maximum commission? 

Expected Output: Query should give data from Salespeople table.

Approch: We can use max function to get maximum commision from Salespoeple table. We can also do one more thing, we can get total of amt form order table griuped by SNUM and then calculate comission amount from Salespeople tabel.

Actual Output:
+------+-------+--------+------+
| SNUM | SNAME | CITY   | COMM |
+------+-------+--------+------+
| 1008 | Fran  | London |   25 |
+------+-------+--------+------+

===============================================================================================

90) Does the customer who has placed the maximum number of orders have the maximum rating? 

Expected Output: Query should give data yes or no as output for which we get the count of orders and ratings of custoemrs.

Approch: We can use max function to get maximum rating from customers table using inner join and count function to get count of orders from order table.

Actual Output:

+--------------+------+--------+
| NO_OF_ORDERS | CNUM | RATING |
+--------------+------+--------+
|            2 | 2004 |    300 |
|            2 | 2008 |    300 |
|            2 | 2006 |    100 |
|            1 | 2003 |    200 |
|            1 | 2002 |    200 |
|            1 | 2001 |    100 |
|            1 | 2007 |    100 |
+--------------+------+--------+

===============================================================================================

91) List all customers in descending order of customer rating. 

Expected Output: Query should give data from Customers table and give detail of customers.

Approch: We can use order by clause to get data in descentding order of rating from Customers table.

Actual Output:
+------+----------+---------+--------+------+
| CNUM | CNAME    | CITY    | RATING | SNUM |
+------+----------+---------+--------+------+
| 2004 | Grass    | Berlin  |    300 | 1002 |
| 2008 | Cisneros | SanJose |    300 | 1007 |
| 2002 | Giovanni | Rome    |    200 | 1003 |
| 2003 | Liu      | SanJose |    200 | 1002 |
| 2001 | Hoffman  | London  |    100 | 1001 |
| 2006 | Clemens  | London  |    100 | 1001 |
| 2007 | Pereira  | Rome    |    100 | 1004 |
+------+----------+---------+--------+------+

===============================================================================================

92) On which days has Hoffman placed orders? 

Expected Output: Query should give data from Orders table and give two  columns ONUM and ODATE.

Approch: We can use subquery or inner join to compare CNUM for order table and customers table.

Actual Output:
+------+------------+
| ONUM | ODATE      |
+------+------------+
| 3003 | 1990-03-10 |
+------+------------+

===============================================================================================

93) Which salesmen have no orders between 10/03/1990 and 10/05/1990? 

Expected Output: Query should give data from order table and give one column SNUM by which we can get the required data.

Approch: We can use where clause and not in and between operator to get the required data.

Actual Output:
+------+
| SNUM |
+------+
| 1002 |
| 1001 |
+------+

===============================================================================================

94) How many salespersons have succeeded in getting orders? 

Expected Output: Query should give data from order table and give one column and one value which is the count of distinct SNUM in orde rtable.

Approch: We can use count function and distinct operator to get the required data.
+------------------------------+
| NO_OF_SALESPEOPLE_GET_ORDERS |
+------------------------------+
|                            5 |
+------------------------------+

===============================================================================================

95) How many customers have placed orders? 

Expected Output: Query should give data from order table and give one column and one value which is the count of distinct CNUM in order table.

Approch: We can use count function and distinct operator to get the required data.

Actual Output:
+-------------------------------+
| NO_OF_CUSTOMERS_PLACED_ORDERS |
+-------------------------------+
|                             7 |
+-------------------------------+

===============================================================================================

96) On which date has each salesman booked an order of maximum value? 

Expected Output: Query should give data from order table and give thre columns which are AMT, ODATE and SNUM.

Approch: We can use max function and group by operator for two value which is ODATE and SNUM.

Actual Output:
+----------+------------+------+
| MAX(AMT) | ODATE      | SNUM |
+----------+------------+------+
|   767.19 | 1990-03-10 | 1001 |
|  5160.45 | 1990-03-10 | 1002 |
|  1900.10 | 1990-03-10 | 1004 |
|  1098.16 | 1990-03-10 | 1007 |
|    75.75 | 1990-04-10 | 1002 |
|  1713.23 | 1990-04-10 | 1003 |
|  4723.00 | 1990-05-10 | 1001 |
|  9891.88 | 1990-06-10 | 1001 |
|  1309.95 | 1990-06-10 | 1002 |
+----------+------------+------+

===============================================================================================(doubt)

97) Who is the most successful salesperson? 

===============================================================================================

98) Which customers have the same rating? 

Expected Output: Query should give data from Customers table and give pairs of customers who have same rating.

Approch: We can use self join and where clause to get the required data.

Actual Output:
+----------+----------+
| CNAME    | CNAME    |
+----------+----------+
| Clemens  | Hoffman  |
| Pereira  | Hoffman  |
| Liu      | Giovanni |
| Giovanni | Liu      |
| Cisneros | Grass    |
| Hoffman  | Clemens  |
| Pereira  | Clemens  |
| Hoffman  | Pereira  |
| Clemens  | Pereira  |
| Grass    | Cisneros |
+----------+----------+

===============================================================================================

99) Find all orders greater than the average for October 4th. 

Expected Output: Query should give data from Orders table and details of orders.

Approch: We can use where clause and a sub query with avg function to get the required data.

Actual Output:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3002 | 1900.10 | 1990-03-10 | 2007 | 1004 |
| 3005 | 5160.45 | 1990-03-10 | 2003 | 1002 |
| 3006 | 1098.16 | 1990-03-10 | 2008 | 1007 |
| 3008 | 4723.00 | 1990-05-10 | 2006 | 1001 |
| 3009 | 1713.23 | 1990-04-10 | 2002 | 1003 |
| 3010 | 1309.95 | 1990-06-10 | 2004 | 1002 |
| 3011 | 9891.88 | 1990-06-10 | 2006 | 1001 |
+------+---------+------------+------+------+

===============================================================================================

100) List all customers with ratings above Grass’s Rating. 

Expected Output: Query should give details of customers if they have rating greater than grass's rating.

Approch: We can use select statement with where consition and a subquery to get Rating of Grass.

Actual Output:
Empty set

===============================================================================================

101) Which customers have above average orders?

Expected Output: Query should give order details if there amount is greater than average amount of order table.

Approch: We can use select statement with where consition and a subquery to get average amount of order table.

Actual Output:
+------+---------+------------+------+------+
| ONUM | AMT     | ODATE      | CNUM | SNUM |
+------+---------+------------+------+------+
| 3005 | 5160.45 | 1990-03-10 | 2003 | 1002 |
| 3008 | 4723.00 | 1990-05-10 | 2006 | 1001 |
| 3011 | 9891.88 | 1990-06-10 | 2006 | 1001 |
+------+---------+------------+------+------+

===============================================================================================

102) Select the total amount in orders for each salesperson for which the total is greater than the amount of the largest order in the table. 

Expected Output: Query should give total amount of orders for each salesperson if total amount is greater than largest amount of order table.

Approch: We can use select statement with where condition, SUM function, INNER JOIN and a subquery to get largest amount of order table and can GROUP BY the data on SNUM.

Actual Output:
+-------+----------+
| SNAME | TOTAL    |
+-------+----------+
| Peel  | 15382.07 |
+-------+----------+

===============================================================================================

103) Give names and numbers of all salespersons that have more than one customer? 

Expected Output: Query should give details of salesperson if they have more than one customer.

Approch: We can use a sub query to get count of CNUM in order table group by SNUM and then select data frm Salespeople table.

Actual Output:
+------+--------+
| SNUM | SNAME  |
+------+--------+
| 1001 | Peel   |
| 1002 | Serres |
+------+--------+

===============================================================================================

104) Select all salespeople by name and number who have customers in their city whom they don’t service. 

Expected Output: Query should give details of salesperson and gove two columns SNAME and SNUM.

Approch: We can use inner join between  Salespeople and Customer table to compare CITY and SNUM between both tables.

Actual Output:
+--------+------+
| SNAME  | SNUM |
+--------+------+
| Motika | 1001 |
| Fran   | 1001 |
| Serres | 1007 |
+--------+------+

===============================================================================================(doubt)

105) Does the total amount in orders by customer in Rome and London, exceed the commission paid to salesperson in London, and New York by more than 5 times? 

===============================================================================================(doubt)

106) Which are the date, order number, amt and city for each salesperson (by name) for themaximum order he has obtained? 

===============================================================================================

107) Which salesperson is having lowest commission? 

Excpected Output: Query should give details of salesperson who has lowest commission.

Approch: We can use select statement with where condition and a subquery to get lowest commission of salesperson.

Actual Output:
+------+---------+------+
| SNUM | SNAME   | COMM |
+------+---------+------+
| 1003 | AxelRod |   10 |
+------+---------+------+

===============================================================================================








*/