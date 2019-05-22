package domain.utils;

import javax.ws.rs.core.HttpHeaders;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public final class AuthenticationUtils {

    /**
     * Returns SHA-256 encoded string
     * @param password - the string to be encoded
     * @return SHA-256 encoded string
     * @throws UnsupportedEncodingException if UTF-8 is not supported by the system
     * @throws NoSuchAlgorithmException if SHA-256 is not supported by the system
     */
    public static String encodeSHA256(String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printBase64Binary(digest).toString();
    }

    public static String getTokenFromHeader(HttpHeaders headers) {
        List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
        String authSchema = "Bearer ";

        if (authHeaders.isEmpty())
            return null;

        // check if header is present and has the auth schema
        String authHeader = authHeaders.get(0);
        if (authHeader.isEmpty() || !authHeader.contains(authSchema))
            return null;

        // remove auth schema from token
        return authHeader.replaceAll(authSchema, "");
    }

}