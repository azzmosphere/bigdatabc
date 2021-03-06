package au.azzmosphere.bigdata.bc.models;

import au.azzmosphere.bigdata.bc.utils.types.Hash;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

import java.math.BigInteger;
import java.util.Date;

/**
 * Single block within the block chain.
 *
 * <p />Reference: https://www.primafelicitas.com/Insights/blockchain-data-structure/
 */
@Value
@Builder(setterPrefix = "with", toBuilder = true)
@JsonDeserialize(builder = Block.BlockBuilder.class)
public class Block {
    /**
     * which indicates the position of the block inside the block chain.
     * The first block is indexed ‘0’, the next ‘1’, and so on.
     */
    private final BigInteger index;

    /**
     * hash function enables the speedy identification of data in the dataset.
     */
    private final Hash hash;

    /**
     * every block in blockchain data structure, is linked with its predecessors.
     * This feature contributes to its immutability as a change in the arrangement of
     * blocks warrants a change in the whole blockchain leading to a whole lot of computation,
     * which is not a feasible option.
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final Block parent;

    /**
     *  stores a count of the number of transactions added in the block.
     */
    private final Integer numTx;

    /**
     * stores the time details of when the block was created.
     */
    private final Date timestamp;

    /**
     * stores the integer (32 or 64bits) that are used in the mining process.
     */
    private final BigInteger nonce;
}
