package cr.infosgroup.comun.json.diff;

public class JsonDiffResponse {
	
	private String jsonDiffAnterior;
	private String jsonDiffNuevo;
	
	public JsonDiffResponse() {
		this.jsonDiffAnterior = "";
		this.jsonDiffNuevo = "";
	}
	
	public String getJsonDiffAnterior() {
		return jsonDiffAnterior;
	}
	public void setJsonDiffAnterior(String jsonDiffAnterior) {
		this.jsonDiffAnterior = jsonDiffAnterior;
	}
	public String getJsonDiffNuevo() {
		return jsonDiffNuevo;
	}
	public void setJsonDiffNuevo(String jsonDiffNuevo) {
		this.jsonDiffNuevo = jsonDiffNuevo;
	}
}
