# Description

EHITS, is an inventory tracking system that supplies information about hospital inventory to prevent 
loss of valuable equipment. It keeps records of all Equipment Identification Number (EIN). Where 
each EIN is unique and consists of 8-digit code (e.g. # EIN: 89700035). EHITS has different types of 
lists that are local for small city hospitals where the number of equipment counts a few hundreds. 
Other lists are for equipment at the provincial/state level hospitals, that is n counts tens of thousands 
or more, or even at country levels, that is n counts millions or more. Furthermore, to track the hospital
inventory status, it is important to have access to the equipment history. The historical record for the 
Equipment Identification Number should be kept in reverse chronological order (i.e. from most 
recent). EHITS, requires a design for a smart and flexible ADT for “Equipment history Report 
Listing”, called ElasticERL. Keys of ElasticERL entries are equipment identification numbers, that 
are 8-digit codes, and one can retrieve the key of a ElasticERL or access a single element by its key. 
Additionally, similar to sequences, given an equipment identification number in a ElasticERL, one 
can access its predecessor or successor (if it exists). 
In general, the ElasticERL should adapt to its usage and the dynamic content that it operates on (hence 
the name Elastic), and it must keep a balance between memory and runtime requirements. For instance, 
if an ElasticERL contains only a small number of entries (e.g., few hundreds), it might use less memory 
overhead but slower (sorting) algorithms. On the other hand, if the number of contained entries is large 
(greater than 1000 or even in the range of tens of thousands of elements or more), it might have a 
higher memory requirement but faster (sorting) algorithms. ElasticERL might be almost constant in 
size or might grow and/or shrink dynamically. Ideally, operations applicable to a single ElasticERL
entry should be between O(1) and O(log n) but never worse than O(n). Operations applicable to a 
complete ElasticERL should not exceed O(n2).

## Methods Used

SetEINThreshold(Size): where 100 ≤ Size ≤ ~500,000 is an integer number that defines 
the size of the list. This size is very important as it will determine what data types or data 
structures will be used (i.e. a Tree, Hash Table, AVL tree, binary tree, sequence, etc.)

generate(): randomly generates new non-existing key of 8 digits

allKeys(ElasticERL): return all keys in ElasticERL as a sorted sequence

add(ElasticERL, key, value): add an entry for the given key and value

remove(ElasticERL, key): remove the entry for the given key

getValues(ElasticERL, key): return the values of the given key

nextKey(ElasticERL, key): return the key for the successor of key

prevKey(ElasticERL, key): return the key for the predecessor of key

rangeKey(key1, key2): returns the number of keys that are within the specified range of 
the two keys key1 and key2

## Data Structures Implemented

Doubly Linked List for small to medium amount of data

Hash Table for medium to large amount of data

AVL Tree for large amount of data
