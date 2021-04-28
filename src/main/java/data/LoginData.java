package data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * @author Jenni
 * This class is used when the user wants to log in to admin's page. 
 * Here CheckPasswords method checks that the password provided by the user is correct.
 * Crypt method crypts the admin's real password and the password provided by the user and later those crypted passwords will be compared.
 */
public class LoginData {
	/**
	 * This method checks if the password provided by the user is correct
	 * @param password1 is the correct password
	 * @param password2 is the password provided by the user
	 * @return boolean value. True if the password is correct and false is the password is incorrect.
	 */
	public static Boolean CheckPasswords (String password1, String password2) {
		boolean passwordCorrect = false;
		
		if(password1.equals(password2)) {
			System.out.println("Password correct");
			passwordCorrect = true;
		} else {
			System.out.println("Password incorrect");
			passwordCorrect = false;
		}
		
		return passwordCorrect;
		
	}
	
	/**
	 * This method crypts the admin's password and the password that the user provides
	 * @param str is the password that needs to be crypted
	 * @return the crypted password
	 */
	public static String crypt(String str) {
	      if (str == null || str.length() == 0) {
	          throw new IllegalArgumentException("String to encript cannot be null or zero length");
	      }

	      MessageDigest digester;
	      try {
	          digester = MessageDigest.getInstance("MD5");

	          digester.update(str.getBytes());
	          byte[] hash = digester.digest();
	          StringBuffer hexString = new StringBuffer();
	          for (int i = 0; i < hash.length; i++) {
	              if ((0xff & hash[i]) < 0x10) {
	                  hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
	              } else {
	                  hexString.append(Integer.toHexString(0xFF & hash[i]));
	              }
	          }
	          return hexString.toString();
	      } catch (NoSuchAlgorithmException e) {
	          e.printStackTrace();
	      }
	      return "";
	  }

}
