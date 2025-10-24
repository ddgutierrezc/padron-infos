package cr.infosgroup.security.jwt.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncriptarPassword {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		String strAdmin = "admin";
		String strAdminEncriptado = bCryptPasswordEncoder.encode(strAdmin);
		System.out.println(strAdmin + " " + strAdminEncriptado);
		
		String strGuest = "guest";
		String strGuestEncriptado = bCryptPasswordEncoder.encode(strGuest);
		System.out.println(strGuest + " " + strGuestEncriptado);
		
		
		String strUser = "user";
		String strUserEncriptado = bCryptPasswordEncoder.encode(strUser);
		System.out.println(strUser + " " + strUserEncriptado);
		
	}

}
