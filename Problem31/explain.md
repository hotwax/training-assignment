Number of operations - 2
1. modify-overwrite-beta
2. shift

Explaintion -
Here we have a map which have a items list, which consist of two entries.
We have to get something like id_currency : value.
To do so we firstly use modify-overwrite-beta operation and use @(1,keyname) to get value of particular key
Then to remove all unwanted and not required feild also to structure according to the output used shift