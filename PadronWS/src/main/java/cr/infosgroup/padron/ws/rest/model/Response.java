package cr.infosgroup.padron.ws.rest.model;

/**
 * 
 * @author lsanabria
 *
 * @param <T>
 */
public class Response<T> extends BaseResponse {
	
	private T data;
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
