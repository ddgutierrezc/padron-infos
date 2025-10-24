package cr.infosgroup.integraciones.liferay.ctl;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;

import cr.infosgroup.comun.json.JsonUtil;
import cr.infosgroup.integraciones.liferay.exception.LiferayJsonApiException;
import cr.infosgroup.integraciones.liferay.exception.RestHttpException;
/**
 * Clase para clientes de servicios REST
 * Implementa el acceso HTTP a un servidor REST
 * 
 * @author lsanabria
 *
 */
public class BaseCtl{
	
	/**
	 * Log de eventos
	 */
	private static final Logger log = LogManager.getLogger(BaseCtl.class);
	
	/**
	 * 
	 */
    protected static String ROOT_API_JSONWS = "/api/jsonws/";

	/**
	 * 
	 */
    protected static String ROOT_API_TAXONOMY = "/o/headless-admin-taxonomy/v1.0/";
    
	/**
	 * 
	 */
    protected static String ROOT_API_DELIVERY = "/o/headless-delivery/v1.0/";    
    
	
	//Operaciones HTTP
	
	/**
	 * Operacion GET
	 */
	public static String GET = "GET";
	
	/**
	 * Operacion POST
	 */
	public static String POST = "POST";
	
	/**
	 * Operacion PUT
	 */
	public static String PUT = "PUT";

	/**
	 * Operacion DELETE
	 */
	public static String DELETE = "DELETE";
	
	/**
	 * Contenido JSON
	 */
	public static final String JSON_CONTENT_TYPE = "application/json";
	
	/**
	 * Contenido multiparte
	 */
	public static final String  FORM_DATA_CONTENT_TYPE = "multipart/form-data";	
	
	/**
	 * Url del servidor liferay
	 */
	private String urlRootLiferay;
	
	/**
	 * Nombre del header donde se envia la informacion de autorizacion
	 */
	private static final String AUTHORIZATION_HEADER = "Authorization";
	
	/**
	 * Autorizacion enviada en cada peticion
	 */
	private String authorization = "";
		
	/**
	 * 
	 * @param urlRootLiferay
	 */
	public BaseCtl(String urlRootLiferay, String userLiferay, String passwordLiferay) {
		//Almancena la direccion de liferay
		this.urlRootLiferay = urlRootLiferay;
		
		//Construye la autorizacion por defecto
		this.authorization = this.buildAuthorization(userLiferay, passwordLiferay);
	}
	

	protected String buildAuthorization(String usuario, String password) {
		String credenciales = usuario + ":" + password;
		String credencialesBase64 = Base64.getEncoder().encodeToString(credenciales.getBytes());
		return "Basic " + credencialesBase64;
	}
	
	protected String httpGet(String path)throws RestHttpException {
		try {
			String jsonResponse = this.http(null,BaseCtl.GET, path, null,null);						
			
			return jsonResponse;
		}
		catch(RestHttpException httpException) {
			//Log del error
			log.error(httpException);
			throw httpException;
		}
		catch (Exception e) {
			log.error(e);
			throw new RestHttpException(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
	}

	protected String httpGet(String authorization, String path)throws RestHttpException {
		try {
			String response = this.http(authorization,BaseCtl.GET, path, null,null);						
			//Retorna la respuesta
			return response;
		}
		catch(RestHttpException httpException) {
			//Log del error
			log.error(httpException);
			throw httpException;
		}
		catch (Exception e) {
			log.error(e);
			throw new RestHttpException(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
	}	
	
	protected String httpPost(String path,String contentType,Object content)throws RestHttpException{
		try {			
			String jsonResponse = this.http(null,BaseCtl.POST, path,contentType, content);
			//Retorna la respuesta
			return jsonResponse;
		}
		catch(RestHttpException httpException) {
			//Log del error
			log.error(httpException);
			throw httpException;
		}
		catch (Exception e) {
			log.error(e);
			throw new RestHttpException(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
	}

	protected String httpPost(String authorization, String path, String contentType, Object content)throws RestHttpException {
		try {
			String jsonResponse = this.http(authorization,BaseCtl.POST, path, contentType, content);
			return jsonResponse;
		}
		catch(RestHttpException httpException) {
			//Log del error
			log.error(httpException);
			throw httpException;
		}
		catch (Exception e) {
			log.error(e);
			throw new RestHttpException(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
	}
	
	protected String httpPut(String path,String contentType,Object content)throws RestHttpException{
		try {			
			String jsonResponse = this.http(null,BaseCtl.PUT, path,contentType, content);
			//Retorna la respuesta
			return jsonResponse;
		}
		catch(RestHttpException httpException) {
			//Log del error
			log.error(httpException);
			throw httpException;
		}
		catch (Exception e) {
			log.error(e);
			throw new RestHttpException(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
	}

	protected String httpPut(String authorization, String path, String contentType, Object content)throws RestHttpException {
		try {
			String jsonResponse = this.http(authorization,BaseCtl.POST, path, contentType, content);
			return jsonResponse;
		}
		catch(RestHttpException httpException) {
			//Log del error
			log.error(httpException);
			throw httpException;
		}
		catch (Exception e) {
			log.error(e);
			throw new RestHttpException(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
	}
	
	protected String httpDelete(String path,String contentType,Object content)throws RestHttpException{
		try {			
			String jsonResponse = this.http(null,BaseCtl.DELETE, path,contentType, content);
			//Retorna la respuesta
			return jsonResponse;
		}
		catch(RestHttpException httpException) {
			//Log del error
			log.error(httpException);
			throw httpException;
		}
		catch (Exception e) {
			log.error(e);
			throw new RestHttpException(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
	}

	protected String httpDelete(String authorization, String path, String contentType, Object content)throws RestHttpException {
		try {
			String jsonResponse = this.http(authorization,BaseCtl.DELETE, path, contentType, content);
			return jsonResponse;
		}
		catch(RestHttpException httpException) {
			//Log del error
			log.error(httpException);
			throw httpException;
		}
		catch (Exception e) {
			log.error(e);
			throw new RestHttpException(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
	}
	
	private String http(String authorization, String method, String path, String contentType, Object content) throws RestHttpException {
		HttpURLConnection httpURLConnection = null;
		try {
			//URL del servicio a invocar
			String strUrl = this.urlRootLiferay + path;
			System.out.println(strUrl);
			// Crea la url
			URL url = new URL(strUrl);
			
			// Establece conexion
			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod(method);
			
			//Verifica si se envia autorizacion
			if(authorization != null) {
				// Agrega la autorizacion
				httpURLConnection.setRequestProperty(AUTHORIZATION_HEADER, authorization);	
			}
			else {
				//Envia la autorizacion por defecto
				// Agrega la autorizacion
				httpURLConnection.setRequestProperty(AUTHORIZATION_HEADER, this.authorization);
			}
			
			//Si hay contenido lo envia
			if(contentType != null && content != null) {

				httpURLConnection.setRequestProperty("Accept", contentType);
				httpURLConnection.setDoOutput(true);
				
				if(BaseCtl.JSON_CONTENT_TYPE.equalsIgnoreCase(contentType)) {
					String jsonContent = JsonUtil.getJson(content);
					this.sendRaw(httpURLConnection,contentType,jsonContent);	
				}
				
				if(BaseCtl.FORM_DATA_CONTENT_TYPE.equalsIgnoreCase(contentType)) {
					this.sendFormData(httpURLConnection,contentType, content);	
				}
				
			}
			
			//Realiza la peticion
			int responseCode = httpURLConnection.getResponseCode();

			//Verifica el resultado
			if (responseCode != HttpURLConnection.HTTP_OK) {
				throw new RestHttpException(responseCode);
			}
			
			String jsonResponse = this.read(httpURLConnection.getInputStream());
			
			return jsonResponse;
		} 
		catch(RestHttpException httpException) {
			//Log del error
			log.error(httpException);
			throw httpException;
		}
		catch (Exception e) {
			log.error(e);
			throw new RestHttpException(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}
		finally {
			try {httpURLConnection.disconnect();} catch (Exception ee) {log.warn(ee);}
		}		
	}
	
	/**
	 * Envia datos
	 * @param con
	 * @param data
	 * @throws IOException
	 */
    private void sendRaw(HttpURLConnection con, String contentType, String data) throws IOException {
        DataOutputStream wr = null;
        try {
        	con.setRequestProperty("Content-Type", contentType);
            wr = new DataOutputStream(con.getOutputStream());
            wr.write(data.getBytes());
            wr.flush();
            wr.close();
        } catch(IOException exception) {
            throw exception;
        } finally {
            this.closeQuietly(wr);
        }
    }

    //https://codereview.stackexchange.com/questions/108222/getting-a-list-of-all-fields-in-an-object
    //https://stackoverflow.com/questions/1378920/how-can-i-make-a-multipart-form-data-post-request-using-java
    private void sendFormData(HttpURLConnection con, String contentType, Object data) throws IOException,  IllegalArgumentException,  IllegalAccessException {
    	String CAMBIO_DE_LINEA = "\r\n";
    	PrintWriter wr = null;
    	StringBuilder sb = new StringBuilder();
        try {
			String boundary = Long.toHexString(System.currentTimeMillis());
			con.setRequestProperty("Content-Type", contentType + "; boundary=" + boundary);
        	
        	Field[] fields = data.getClass().getDeclaredFields();
        	
        	wr = new PrintWriter(con.getOutputStream(),true);
        	
        	for (Field field : fields) {
        		wr.append(boundary).append(CAMBIO_DE_LINEA);
        		sb.append(boundary).append(CAMBIO_DE_LINEA);
        		wr.append("Content-Disposition: form-data; name=\"" + field.getName() + "\"").append(CAMBIO_DE_LINEA).append(CAMBIO_DE_LINEA);
        		sb.append("Content-Disposition: form-data; name=\"" + field.getName() + "\"").append(CAMBIO_DE_LINEA).append(CAMBIO_DE_LINEA);
        		//IF ACA
        		if (String.class.isAssignableFrom(field.getType()) ) {
        			wr.append("\'" + (String)field.get(data) + "\'").append(CAMBIO_DE_LINEA);
        			sb.append("\'" + (String)field.get(data) + "\'").append(CAMBIO_DE_LINEA);
        		}
        		
        		if (long.class.isAssignableFrom(field.getType())) {
        			wr.append(String.valueOf((long)field.get(data))).append(CAMBIO_DE_LINEA);
        			sb.append(String.valueOf((long)field.get(data))).append(CAMBIO_DE_LINEA);
        		}
        		
        		if (int.class.isAssignableFrom(field.getType())) {
        			wr.append(String.valueOf((int)field.get(data))).append(CAMBIO_DE_LINEA);
        			sb.append(String.valueOf((int)field.get(data))).append(CAMBIO_DE_LINEA);
        		}
        		
        		if (boolean.class.isAssignableFrom(field.getType())) {
        			wr.append(String.valueOf((boolean)field.get(data))).append(CAMBIO_DE_LINEA);
        			sb.append(String.valueOf((boolean)field.get(data))).append(CAMBIO_DE_LINEA);
        		}
        		
        		if (field.get(data) == null && !(long[].class.isAssignableFrom(field.getType()) 
        				|| Locale.class.isAssignableFrom(field.getType()))
        				) {
        			wr.append(null).append(CAMBIO_DE_LINEA);
        			sb.append("null").append(CAMBIO_DE_LINEA);
        		}
        		
        		if (long[].class.isAssignableFrom(field.getType())) {
        			wr.append("").append(CAMBIO_DE_LINEA);
        			sb.append("").append(CAMBIO_DE_LINEA);
        		}
        		
        		if (long[].class.isAssignableFrom(field.getType())) {
        			wr.append("").append(CAMBIO_DE_LINEA);
        			sb.append("").append(CAMBIO_DE_LINEA);
        		}
        		
        		
        	}
//        	wr.append(CAMBIO_DE_LINEA);
        	wr.append(boundary).append(CAMBIO_DE_LINEA);
        	sb.append(boundary).append(CAMBIO_DE_LINEA);
            wr.flush();
            wr.close();
        } catch(IOException | IllegalArgumentException | IllegalAccessException exception) {
        	throw exception;
        } finally {
            this.closeQuietly(wr);
        }
    }    
    
    
	public String toString() {
		StringBuilder str = new StringBuilder();
		
		str.append(this.getClass().getSimpleName());
		str.append(" [ ");
		
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			str.append(field.getName());
			str.append(":");
			try {
				str.append(field.get(this));
			} catch (Exception e) {
				str.append("*");
			}
			str.append(" ");
		}
		str.append("]");
	
		return str.toString();
	}
    
    
    /**
     * Lee datos
     * @param is
     * @return
     * @throws IOException
     */
    private String read(InputStream is) throws IOException {
        BufferedReader in = null;
        String inputLine;
        StringBuilder body;
        try {
            in = new BufferedReader(new InputStreamReader(is));

            body = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                body.append(inputLine);
            }
            in.close();

            return body.toString();
        } catch(IOException ioe) {
            throw ioe;
        } finally {
            this.closeQuietly(in);
        }
    }	
	
    /**
     * Close
     * @param closeable
     */
    private void closeQuietly(Closeable closeable) {
        try {
            if( closeable != null ) {
                closeable.close();
            }
        } catch(IOException ex) {
        	log.warn(ex);
        }
    }
    
    /**
     * Método genérico para obtener un objeto desde una respuesta JSON.
     */
    protected <T> T deleteObjectFromJson(String path, Class<T> clazz) throws LiferayJsonApiException {
        try {
        	String jsonResponse = this.httpDelete(path,BaseCtl.JSON_CONTENT_TYPE,clazz);
            return JsonUtil.getObject(jsonResponse, clazz);
        } catch (RestHttpException restEx) {
            log.error("Error HTTP al consumir JSONWS: {}", restEx.getStatusCode(), restEx);
            throw new LiferayJsonApiException(restEx.getStatusCode());
        } catch (Exception e) {
            log.error("Error al procesar respuesta JSONWS", e);
            throw new LiferayJsonApiException();
        }
    }    
    
    /**
     * Método genérico para obtener un objeto desde una respuesta JSON.
     */
    protected <T> T putObjectFromJson(String path, Class<T> clazz) throws LiferayJsonApiException {
        try {
        	String jsonResponse = this.httpPut(path,BaseCtl.JSON_CONTENT_TYPE,clazz);
            return JsonUtil.getObject(jsonResponse, clazz);
        } catch (RestHttpException restEx) {
            log.error("Error HTTP al consumir JSONWS: {}", restEx.getStatusCode(), restEx);
            throw new LiferayJsonApiException(restEx.getStatusCode());
        } catch (Exception e) {
            log.error("Error al procesar respuesta JSONWS", e);
            throw new LiferayJsonApiException();
        }
    }
    
    /**
     * Método genérico para obtener un objeto desde una respuesta JSON.
     */
    protected <T> T getObjectFromJson(String path, Class<T> clazz) throws LiferayJsonApiException {
        try {
            String jsonResponse = this.httpGet(path);
            return JsonUtil.getObject(jsonResponse, clazz);
        } catch (RestHttpException restEx) {
            log.error("Error HTTP al consumir JSONWS: {}", restEx.getStatusCode(), restEx);
            throw new LiferayJsonApiException(restEx.getStatusCode());
        } catch (Exception e) {
            log.error("Error al procesar respuesta JSONWS", e);
            throw new LiferayJsonApiException();
        }
    }
    

    
    
    /**
     * Método genérico para obtener una lista desde una respuesta JSON.
     */
    protected <T> List<T> getListFromJson(String path, TypeReference<List<T>> typeRef) throws LiferayJsonApiException {
        try {
            String jsonResponse = this.httpGet(path);
            return JsonUtil.getObject(jsonResponse, typeRef);
        } catch (RestHttpException restEx) {
            log.error("Error HTTP al consumir JSONWS: {}", restEx.getStatusCode(), restEx);
            throw new LiferayJsonApiException(restEx.getStatusCode());
        } catch (Exception e) {
            log.error("Error al procesar respuesta JSONWS", e);
            throw new LiferayJsonApiException();
        }
    }
    
    ////
    ///
    ///
    ///
    ///
        

	

	
    
}
