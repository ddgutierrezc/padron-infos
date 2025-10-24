package cr.infosgroup.padron.ws.rest.model;

import java.util.List;

/**
 * 
 * @author lsanabria
 *
 * @param <T>
 */
public class ListResponse<T> extends BaseResponse{

	private List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
