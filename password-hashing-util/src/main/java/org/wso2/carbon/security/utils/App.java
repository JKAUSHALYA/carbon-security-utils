package org.wso2.carbon.security.utils;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NoSuchAlgorithmException {

        // Other than the password, all others are optional and default values will be picked up.
        if (args.length < 1) {
            System.out.print("Usage: password [salt] [hash algorithm] [iteration count] [key length]. [] are optional");
        }

        String password = args[0];
        String salt = UUID.randomUUID().toString();
        String hashAlgo = "SHA256";
        int iterationCount = 4096;
        int keyLength = 256;

        if (args.length > 1) {
            salt = args[1];
            if (args.length > 2) {
                hashAlgo = args[2];
            }
        }

        if (args.length > 3) {
            iterationCount = Integer.parseInt(args[3]);
            keyLength = Integer.parseInt(args[4]);
        }

        DefaultPasswordHandler defaultPasswordHandler = new DefaultPasswordHandler();
        defaultPasswordHandler.setIterationCount(iterationCount);
        defaultPasswordHandler.setKeyLength(keyLength);

        System.out.println("Password: " + defaultPasswordHandler.hashPassword(password.toCharArray(), salt, hashAlgo));
        System.out.println("Salt: " + salt);
    }
}
