package au.azzmosphere.bigdata.bc.services;

import au.azzmosphere.bigdata.bc.exceptions.BcJsonProcessingException;
import au.azzmosphere.bigdata.bc.exceptions.BcNoSuchAlgorithmException;
import au.azzmosphere.bigdata.bc.models.Block;
import au.azzmosphere.bigdata.bc.models.MerkleTreeContainer;
import au.azzmosphere.bigdata.bc.utils.types.Hash;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * Service to create merkle tree specific to this application.
 */
public class MerkleTreeImpl implements MerkleTree {
    private static final ObjectWriter writer = new ObjectMapper().writer();
    private static final String HASH_ALGO = "SHA-256";

    /**
     * Create the initial block.
     *
     * @param genesisNode initial node.
     * @return the merkleTree.
     */
    @Override
    public final MerkleTreeContainer createGenesis(final Block genesisNode) {
        final Block node = genesisNode.toBuilder().withIndex(new BigInteger("0")).build();
        return MerkleTreeContainer.builder()
                .withLastNode(node)
                .withGenesis(node).build();
    }

    /**
     * newly created node, that has not been added to tree.
     *
     * @param node node to add.
     * @param merkleTree current merkle tree
     * @return tree with node added.
     */
    @Override
    public final MerkleTreeContainer addNode(final Block node, final MerkleTreeContainer merkleTree) {
        final Block parent = merkleTree.getLastNode();
        final Hash nodeHash = node.getHash();
        final Hash conHash = concatenate(parent.getHash(), nodeHash);
        final BigInteger index = parent.getIndex().add(BigInteger.valueOf(1));

        final Block newNode = node.toBuilder()
                .withIndex(index)
                .withParent(parent)
                .withHash(conHash).build();

        return merkleTree.toBuilder()
                .withLastNode(newNode)
                .build();
    }

    /**
     * Create an unverified node based upon the transactions.
     *
     * @param transactions list of transactions to add.
     * @return potential block to add.
     */
    public final Block createBlock(final List transactions) {
        Block block;
        try {
            final String originalString = writer.writeValueAsString(transactions);
            final MessageDigest digest = MessageDigest.getInstance(HASH_ALGO);
            final Hash sha256hash = new Hash(digest.digest(originalString.getBytes(StandardCharsets.UTF_8)));

            block = Block.builder()
                    .withHash(sha256hash)
                    .withTransaction(transactions)
                    .withTimestamp(new Date())
                    .withNumTx(transactions.size())
                    .build();
        } catch (NoSuchAlgorithmException ex) {
            throw new BcNoSuchAlgorithmException("unable to create block", ex);
        } catch (JsonProcessingException ex) {
            throw new BcJsonProcessingException("transactions could not be read", ex);
        }
        return block;
    }

    /**
     * Concatenate two hashes.
     *
     * @param a hash to concatenate
     * @param b second hash to concatenate
     * @return concatenated byte array
     */
    public final Hash concatenate(final Hash a, final Hash b) {
        final byte[] c = new byte[a.getHash().length + b.getHash().length];
        System.arraycopy(a.getHash(), 0, c, 0, a.getHash().length);
        System.arraycopy(b.getHash(), 0, c, a.getHash().length, b.getHash().length);
        return new Hash(c);
    }
}
