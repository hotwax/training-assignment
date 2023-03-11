/*

Testcase 1: User entered wrong choice

Output: Please choose a valid option from the menu

Testcase 2: User entered invalid input

Output: Please enter a valid input

Testcase 3: Inserting an edge between 2 nodes.

Input: 1 2 3

Output: Edge inserted successfully

0: 0 0 0 0 0 
1: 0 0 0 0 0 
2: 0 0 0 1 0 
3: 0 0 1 0 0 
4: 0 0 0 0 0

Testcase 4: Deleting an edge between 2 nodes.

Input: 2 2 3

Output: Edge deleted successfully

0: 0 0 0 0 0
1: 0 0 0 0 0
2: 0 0 0 0 0
3: 0 0 0 0 0
4: 0 0 0 0 0

Testcase 5: User trying to delete an edge between 2 nodes which are not connected.

Input: 2 2 3

Output: No edge between 2 and 3

Testcase 6: User trying to insert an edge between 2 nodes which are already connected.

Input: 1 2 3

Output: Edge already exists between 2 and 3

Testcase 7: User trying to insert an edge outside the range of nodes.

Input: 1 8 6

Output: Index 5 out of bounds for length 5

Testcase 8: User entered choice 3 to display the graph using DFS algorithm.

Input: 3

Output: 
0: 0 0 0 0 0
1: 0 0 0 0 0
2: 0 0 0 0 0
3: 0 0 0 0 0
4: 0 0 0 0 0

Testcase 9: User entered choice 4 to display the graph using BFS algorithm.

Input: 4

Output:
0: 0 0 0 0 0
1: 0 0 0 0 0
2: 0 0 0 0 0
3: 0 0 0 0 0
4: 0 0 0 0 0

*/