package au.azzmosphere.bigdata.bc.models;

import lombok.Builder;
import lombok.Data;

/**
 * Tree container is a reference to the tree,  it contains the genesis node and the last node
 * added.  Using the container transversal of the tree is possible.
 */
@Data
@Builder(setterPrefix = "with", toBuilder = true)
public class MerkleTreeContainer {
    private final Block genesis;
    private final Block lastNode;
}
