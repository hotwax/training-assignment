## Explanation

Operations: modify-default-beta, shift 

The transformation involves following operation:

* `modify-default-beta:` Adds a new field named fullname by concatinating firstName and lastName

* `shift`: It  maps the "city" and "zip" fields within the "address" object to "city" and "zip" fields in the output JSON, while keeping fullname and email as it is, using the wildcard '&'.