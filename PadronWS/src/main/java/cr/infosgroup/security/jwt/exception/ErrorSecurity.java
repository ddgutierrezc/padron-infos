package cr.infosgroup.security.jwt.exception;

/**
 * 
 * @author lsanabria
 *
 */
public enum ErrorSecurity {
		
	//Errores de seguridad ( ErrorSecurity_<NUMERO> )
	ErrorSecurity_Exception("ErrorSecurity_Exception","Error_Generico"),
	ErrorSecurity_ClaimsIDRequired("ErrorSecurity_ClaimsIDRequired","ErrorSecurity_ClaimsIDRequired"),
	ErrorSecurity_UnsupportedJwtException("ErrorSecurity_UnsupportedJwtException","ErrorSecurity_UnsupportedJwtException"),
	ErrorSecurity_MalformedJwtException("ErrorSecurity_MalformedJwtException","MalformedJwt"),
	ErrorSecurity_SignatureException("ErrorSecurity_SignatureException","ErrorSecurity_SignatureException"),
	ErrorSecurity_ExpiredJwtException("ErrorSecurity_ExpiredJwtException","ExpiredJwt");
	;
	//
	
	private String secCode;
	private String secTechMessage;
	
	private ErrorSecurity(String secCode,String secTechMessage) {
		this.secCode = secCode;
		this.secTechMessage = secTechMessage;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("secCode: ");
		str.append(this.secCode);
		str.append(" secTechMessage: ");
		str.append(this.secTechMessage);
		return str.toString();
	}

	public String getSecCode() {
		return secCode;
	}

	public void setSecCode(String secCode) {
		this.secCode = secCode;
	}

	public String getSecTechMessage() {
		return secTechMessage;
	}

	public void setSecTechMessage(String secTechMessage) {
		this.secTechMessage = secTechMessage;
	}
}
