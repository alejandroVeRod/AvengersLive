package modelo.mongodb;

public class GeneradorContrasena {
		
	final static String NUMEROS = "0123456789";	 
	final static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz"; 
	//public static String ESPECIALES = "ñÑ";
 
	public static String getPinNumber() {
		return getContrasenaAleatoria(NUMEROS, 4);
	}
 
	public static String getContrasenaAleatoria() {
		return getContrasenaAleatoria(8);
	}
 
	public static String getContrasenaAleatoria(int longitudContrasena) {
		return getContrasenaAleatoria(NUMEROS + MAYUSCULAS + MINUSCULAS, longitudContrasena);
	}
 
	public static String getContrasenaAleatoria(String requisito, int longitudContrasena) {
		String contrasena = "";
 
		for (int i = 0; i < longitudContrasena; i++) {
			contrasena += (requisito.charAt((int)(Math.random() * requisito.length())));
		}
 
		return contrasena;
	}

}
