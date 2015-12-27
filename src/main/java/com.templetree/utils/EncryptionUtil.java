package com.templetree.utils;

import com.templetree.application.TempletreeApplication;
import com.templetree.exception.TempletreeException;
import com.templetree.model.Role;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.StringFixedSaltGenerator;
import org.joda.time.DateTime;
import org.slf4j.Logger;

import javax.ws.rs.core.Response;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static java.lang.Long.parseLong;
import static java.net.URLEncoder.encode;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Lalith Mannur
 */
public final class EncryptionUtil {
    private static final Logger LOGGER = getLogger(EncryptionUtil.class);
    private static StandardPBEStringEncryptor userEncryptor = null;
    private static final String UTF8_ENCODING = "UTF-8";

    private EncryptionUtil() {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    // SHA-256 base hashing to encode the passwords
    public static String getHash(String password) {
        String hashedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(password.getBytes());
            hashedPassword = org.apache.commons.codec.binary.Base64.encodeBase64String(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return hashedPassword;
    }

    public static String encryptString(String toEncrypt, Properties appProperties) {
        StandardPBEStringEncryptor userEncryptorInstance = getUserEncryptorInstance(appProperties);
        return org.apache.commons.codec.binary.Base64.encodeBase64String((userEncryptorInstance.encrypt(toEncrypt)).getBytes());
    }

    public static String decryptString(String encryptedString, Properties appProperties) {
        StandardPBEStringEncryptor userEncryptorInstance = getUserEncryptorInstance(appProperties);
        byte[]  decodedString = org.apache.commons.codec.binary.Base64.decodeBase64(encryptedString.getBytes());

        return userEncryptorInstance.decrypt(new String (decodedString));
    }

    public static String generateAccessToken(String username, Role role, String delim, Properties appProperties)
            throws TempletreeException {
        try {
            String unencryptedString = username + delim + new Date().getTime()+ delim + role;
            String templetreeAuthToken = encryptString(unencryptedString, appProperties);
            return encode(templetreeAuthToken, UTF8_ENCODING);
        } catch (Exception ex) {
            LOGGER.error("Error during Token generation : ", ex);
            //throw new TempletreeException(ex.getMessage(), ex);
            throw new TempletreeException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "TEST", "GenerateAccessTokenError",
                    "GenerateAccessTokenError");
        }
    }

    private static StandardPBEStringEncryptor getUserEncryptorInstance(Properties appProperties) {
        LOGGER.info("getUserEncryptorInstance method  :: entry");
        try {
            if (userEncryptor == null) {
                userEncryptor = new StandardPBEStringEncryptor();
                // Set password from property file
                userEncryptor.setPassword(appProperties.getProperty("app.userEncryptor.password"));
                // Set salt value from property file
                StringFixedSaltGenerator salt = new StringFixedSaltGenerator(appProperties.getProperty("app.userEncryptor.salt"));
                userEncryptor.setSaltGenerator(salt);
            }
        } catch (Exception ex) {
            LOGGER.error("getUserEncryptorInstance Error {}", ex);
        }
        LOGGER.info("getUserEncryptorInstance method  :: exit");
        return userEncryptor;
    }

    public static boolean isTokenValid(String tokenIssuedTime, Integer tokenTimeout) {

        double seconds = (DateTime.now().getMillis() - new Long(tokenIssuedTime)) / 1000d;

        // If difference between NOW and last token issued time is greater than token timeout, token is no longer valid
        if(seconds > tokenTimeout)
            return false;
        else
            return true;

    }

}
