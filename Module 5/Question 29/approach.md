## Number of operation 2

1. shift (2 times)

## Explanation:
* The first "shift" operation extracts all properties inside the "person" object and prefixes their names with "person-". For example, "name" becomes "person-name" and "age" becomes "person-age".

* The second "shift" operation copies all properties from the root level and appends them to the resulting JSON. Additionally, it extracts properties inside the "person-address" object and prefixes their names with "person-address-".