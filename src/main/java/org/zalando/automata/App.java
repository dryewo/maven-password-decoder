package org.zalando.automata;

import org.sonatype.plexus.components.cipher.DefaultPlexusCipher;
import org.sonatype.plexus.components.cipher.PlexusCipherException;
import org.sonatype.plexus.components.sec.dispatcher.DefaultSecDispatcher;

public class App
{
    public static void main(String... args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java -jar maven-password-decoder.jar MASTER_PASSWORD PASSWORD");
            System.exit(1);
            return;
        }
        final String masterPassword = decodeMasterPassword(args[0]);
        final String password = decodePassword(args[1], masterPassword);
        System.out.println(masterPassword + "\t" + password);
    }

    private static String decodeMasterPassword(String encodedMasterPassword) throws PlexusCipherException {
        return decodePassword(encodedMasterPassword, DefaultSecDispatcher.SYSTEM_PROPERTY_SEC_LOCATION);
    }

    private static String decodePassword(String encodedPassword, String key) throws PlexusCipherException {
        DefaultPlexusCipher cipher = new DefaultPlexusCipher();
        return cipher.decryptDecorated(encodedPassword, key);
    }
}
