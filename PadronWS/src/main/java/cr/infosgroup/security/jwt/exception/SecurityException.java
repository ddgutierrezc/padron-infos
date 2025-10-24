package cr.infosgroup.security.jwt.exception;

/**
 * 
 * @author lsanabria
 *
 */
public class SecurityException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6418275306551040607L;
	
	private ErrorSecurity errorSecurity;
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("errorSecurity: ");
		str.append(errorSecurity);
		str.append(" throwable: ");
		str.append(super.toString());
		return str.toString();
	}
	
	public SecurityException(ErrorSecurity errorSecurity) {
		super();
		this.errorSecurity = errorSecurity;
	}
		
	public SecurityException(ErrorSecurity errorSecurity,Throwable throwable) {
		super(throwable);
		this.errorSecurity = errorSecurity;
	}

	public ErrorSecurity getErrorSecurity() {
		return errorSecurity;
	}
}