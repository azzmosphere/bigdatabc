package au.azzmosphere.bigdata.bc.services;

import au.azzmosphere.bigdata.bc.models.Block;
import au.azzmosphere.bigdata.bc.models.MerkleTreeContainer;
import au.azzmosphere.bigdata.bc.utils.types.Hash;

import java.util.List;

/**
 *
 */
public interface MerkleTree {
    // read tree from file,

    // create tree from genesis node.
    MerkleTreeContainer createGenesis(Block genesisNode);

    /**
     * add a new node to the merkle tree.
     *
     * @param node node to add.
     * @param merkleTree current merkle tree
     * @return merkle tree with node added.
     */
    MerkleTreeContainer addNode(Block node, MerkleTreeContainer merkleTree);

    Block createBlock(List transactions);

    Hash concatenate(Hash a, Hash b);
}
