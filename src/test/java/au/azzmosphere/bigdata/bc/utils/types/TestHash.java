package au.azzmosphere.bigdata.bc.utils.types;

import au.azzmosphere.bigdata.bc.models.Block;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHash {

    @Test
    public void shouldCreateSha256() throws Exception {
        String originalString = "{}";

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        Hash sha256hash = new Hash(digest.digest(originalString.getBytes(StandardCharsets.UTF_8)));

        assertEquals(
                "44136fa355b3678a1146ad16f7e8649e94fb4fc21fe77e8310c060f61caaff8a",
                sha256hash.toString()
        );
    }

    @Test
    public void shouldConvertHexString() throws Exception {
        String hex = "44136fa355b3678a1146ad16f7e8649e94fb4fc21fe77e8310c060f61caaff8a";
        Hash sha256hash = new Hash(hex);

        assertEquals(
                "44136fa355b3678a1146ad16f7e8649e94fb4fc21fe77e8310c060f61caaff8a",
                sha256hash.toString()
        );
    }

    @Test
    public void shouldDeserialize() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("block.1.json");
        ObjectReader reader = new ObjectMapper().readerFor(Block.class);
        Block bc = reader.readValue(resource);
        assertEquals(
                "44136fa355b3678a1146ad16f7e8649e94fb4fc21fe77e8310c060f61caaff8a",
            bc.getHash().toString());
    }

    @Test
    public void shouldCreateSha256_2() throws Exception {
        String originalString = "{\"1\": 1}";

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        Hash sha256hash = new Hash(digest.digest(originalString.getBytes(StandardCharsets.UTF_8)));

        System.out.println(sha256hash);
    }
}
