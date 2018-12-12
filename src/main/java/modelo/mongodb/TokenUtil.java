package modelo.mongodb;

 
import org.bson.Document;

import org.springframework.security.crypto.codec.Hex;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

 
public class TokenUtil {
 
	public static final String MAGIC_KEY = "AvengersLive";
 
	public static String createToken(Document e) {
		long expires = System.currentTimeMillis() + 1000L * 60 * 60;
		return e.get("email") + ":" + expires + ":" + computeSignature(e, expires);
	}
 
	public static String computeSignature(Document userDetails, long expires) {
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(userDetails.get("email")).append(":");
		signatureBuilder.append(userDetails.get("ip")).append(":");
		signatureBuilder.append(expires).append(":");
		signatureBuilder.append(userDetails.get("contrasena")).append(":");
		signatureBuilder.append(TokenUtil.MAGIC_KEY);
 
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("No MD5 algorithm available!");
		}
		return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
	}
 
	public static String getUserNameFromToken(String authToken) {
		if (authToken == null) {
			return null;
		}
		String[] parts = authToken.split(":");
		return parts[0];
	}
 
	public static boolean validateToken(String authToken, Document userDetails) {
		String[] parts = authToken.split(":");
		long expires = Long.parseLong(parts[1]);
		String signature = parts[2];
		String signatureToMatch = computeSignature(userDetails, expires);
		return expires >= System.currentTimeMillis() && signature.equals(signatureToMatch);
	}
}
