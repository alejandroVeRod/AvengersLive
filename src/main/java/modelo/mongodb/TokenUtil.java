package modelo.mongodb;

 
import org.bson.Document;

import org.springframework.security.crypto.codec.Hex;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

 
public class TokenUtil {
 
	public static final String MAGIC_KEY = "AvengersLive";
	
	public static String createToken(Document e) {
		return e.get("email") +  ":" + computeSignature(e);
	}
	public static boolean saveToken(String Token) {
		if(DAOEmpleado.saveToken(Token)) {
			return true;
		}else {
			return false;
		}
	}

 
	public static String computeSignature(Document userDetails) {
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(userDetails.get("email")).append(":");
		signatureBuilder.append(userDetails.get("ip")).append(":");
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
	
 
	public static boolean validateToken(String authToken) {
		if(DAOEmpleado.compareToken(authToken)) {
			return true;
		}else {
			return false;
		}
	}
}
