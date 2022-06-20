# ABSTRACT

Attempt to create a blockchain big data project.  The project will replicate big data meta-stores over different 
geological zones.

## Transactions

Process for big data is as follows (within each geographical zone)

* Add data to Data Lake (raw format)
* Curate data to AVRO format and add it to Datalake, data is registered to a AVRO schema registry and checked for backward compatibility.
* Notify blockchain of proposed block.
* Proposed block is verified by other meta-stores,  verification adds data to all other meta-store. 

# REFERENCES

General Documents on Block Chains
https://github.com/blockchaineering/DLT-design/blob/master/Bitcoin-A-Peer-to-Peer-Electronic-Cash-System-Nakamoto.pdf

Merkle Tree Explanation.
https://www.geeksforgeeks.org/introduction-to-merkle-tree/

https://stackoverflow.com/questions/56386764/a-simple-merkle-tree-implementation-in-java
https://github.com/SimoneStefani/merkle-tree-java

https://www.pranaybathini.com/2021/05/merkle-tree.html