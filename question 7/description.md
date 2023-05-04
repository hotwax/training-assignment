We want a list which contains maps which contains name, age and Domain.
[#2] is used to create list.
we need to extract domain from email, thats why we have used "*\\@*" i.e. we need only content after 2nd * that why we have used "$(0,2)" and then put that domain into outermost list in field Domain.