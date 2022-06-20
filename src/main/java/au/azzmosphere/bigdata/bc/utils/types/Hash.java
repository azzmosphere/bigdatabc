package au.azzmosphere.bigdata.bc.utils.types;

import lombok.Data;
import java.io.Serializable;

/**
 * Contains Hex that is created by MessageDigest classes.
 *
 * <p />Hex strings can be of any length, but must be lowercase and must contain characters within
 * the range of 0-9 or a-f,  the total length should also be even number,  since they are
 * represented in two character blocks.  For instance 00 0a ff etc.
 */
@Data
public class Hash implements Serializable {
    /**
     * Hex is the byte values of the hash.
     */
    private final byte[] hash;

    /**
     * Given a standard HEX string, convert it to a byte array.
     *
     * @param digest original hex string
     * @return Hex converted to byte array.
     */
    public Hash(String digest) {
        byte hex[] = new byte[digest.length() / 2];
        int l = digest.length() /2;
        for (int i = 0; i < l; i ++) {
            int p = i *2;
            char[] chars = new char[2];
            digest.getChars(p, p +2, chars, 0);
            hex[i] = (byte) Integer.parseInt(String.copyValueOf(chars), 16);
        }
        this.hash = hex;
    }

    public Hash(byte[] digest) {
        this.hash = digest;
    }

    /**
     * Overrides toString() class to convert bytes to hex string.
     * @return stringified value.
     */
    @Override
    public String toString() {
        StringBuilder hexString = new StringBuilder(2 * hash.length);

        for(int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
