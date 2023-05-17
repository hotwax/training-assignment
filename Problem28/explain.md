Number of operations - 2
1. shift
2. modify-overwrite-beta

Explaintion -
Here we have to create a list which will have maps having returnId & returnTax.
If returnItemSeqId of maps are same then add amounts in the same.
Display the total amount along with returnID
We simply need to group values of amount in an list along which have same returnItemSeqId.
And then we can add using modify-overwrite-beta method doubleSum()