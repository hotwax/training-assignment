## Number of Opeartion : 2

1. modify-overwrite-beta : string transform
2. remove : remove unwanted keys

## Explaintaion

1. Used `split` function to split the `name` by blank space into `temp1` field and created `first_name` from 0th and `last_name` from 1st index of `temp1` field using `firstElement` and `lastElement` function respectively.
2. Removed the `name` and `temp1` entry from object using `remove` operation by setting the value of both to `""`.
