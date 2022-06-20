package au.azzmosphere.bigdata.bc.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with", toBuilder = true)
public class MerkleTreeContainer {
    private final Block genesis;
    private final Block lastNode;
}
