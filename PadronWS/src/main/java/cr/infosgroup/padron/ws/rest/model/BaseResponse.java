package cr.infosgroup.padron.ws.rest.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author lsanabria
 *
 */
public class BaseResponse {
	
	private boolean success;
	private List<Error> errors;

	public BaseResponse() {
		this.success = true;
		this.errors = new ArrayList<Error>();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void addError(String code, String techMessage) {
		this.errors.add(new Error(code,  techMessage));
	}
	
	public List<Error> getErrors() {
		return errors;
	}
	
	private class Error {
		
		private String code;
		private String techMessage;
				
		public Error(String code, String techMessage) {
			this.code = code;
			this.techMessage = techMessage;
		}
		
		@Override
		public String toString() {
			StringBuilder str = new StringBuilder();
			str.append("code: ");
			str.append(code);
			str.append(" techMessage: ");
			str.append(techMessage);	
			return str.toString();
		}

		@SuppressWarnings("unused")
		public String getCode() {
			return code;
		}

		@SuppressWarnings("unused")
		public String getTechMessage() {
			return techMessage;
		}
	}
}