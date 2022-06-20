package au.azzmosphere.bigdata.bc.services;

import au.azzmosphere.bigdata.bc.models.Block;
import au.azzmosphere.bigdata.bc.models.MerkleTreeContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMerkleTree {

    private MerkleTree merkleTree = new MerkleTreeImpl();

    @Test
    public void shouldCreateBlock() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("transaction.1.json");
        ObjectReader reader = new ObjectMapper().readerFor(List.class);
        List transactions = reader.readValue(resource);

        Block block = merkleTree.createBlock(transactions);

        assertEquals(2, block.getNumTx());
        assertEquals(
                "8202160247f6d74f959a8b78d6699718dfd8afb82d44ab36cbf7ffacfd6bfc72",
                block.getHash().toString());
    }

    @Test
    public void shouldCreateTree() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("transaction.1.json");
        ObjectReader reader = new ObjectMapper().readerFor(List.class);
        List transactions = reader.readValue(resource);

        Block block = merkleTree.createBlock(transactions);

        MerkleTreeContainer merkleTreeContainer = merkleTree.createGenesis(block);

        resource = classLoader.getResource("transaction.2.json");
        transactions = reader.readValue(resource);
        block = merkleTree.createBlock(transactions);
        merkleTreeContainer = merkleTree.addNode(block, merkleTreeContainer);

        assertEquals(BigInteger.ONE, merkleTreeContainer.getLastNode().getIndex());
        assertEquals(3, merkleTreeContainer.getLastNode().getNumTx());
        assertEquals(
                "836788b54d8452b754bc735f0620852ff8d840d1aa0e733ec07cb14c51178e3b",
                merkleTreeContainer.getLastNode().getHash().toString()
        );
        System.out.println(merkleTreeContainer.getLastNode().getHash());
    }
}
