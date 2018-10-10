import java.io.*;
import java.lang.Math.*;
import java.security.*;

public class SHA{
    public static void main(String arg[]) throws Exception {
        System.out.println(encrypt("abirami"));
    }

    public static byte[] encrypt(String x) throws Exception {
        MessageDigest d = null;
        d = MessageDigest.getInstance("SHA1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }
}