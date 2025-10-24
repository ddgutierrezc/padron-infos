package cr.infosgroup.padron.ws.rest.ctl;

import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.infosgroup.padron.dto.CiudadanoDTO;
import cr.infosgroup.padron.ws.rest.BaseCtl;
import cr.infosgroup.padron.ws.rest.model.ListResponse;
import cr.infosgroup.padron.ws.rest.model.Response;
//import cr.infosgroup.padron.ws.util.HttpUtil;
//import cr.infosgroup.security.jwt.ISeguridadJWT;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(BaseCtl.API_ROOT + "/ciudadano")
public class CiudadanoCtl extends BaseCtl  {
	
//    private static final Logger log = LogManager.getLogger(CiudadanoCtl.class);

//    @Autowired
//    private ISeguridadJWT seguridadJWT;    


    @GetMapping("/all")
    public ResponseEntity<ListResponse<CiudadanoDTO>> getAll(HttpServletRequest request) {
        ListResponse<CiudadanoDTO> respuesta = new ListResponse<>();
        try {
			//Log se hace en AutenticationFilterJWT
			//Token
			//String token = this.seguridadJWT.getTokenFromRequest(request);
			//UserName
			//String userName = this.seguridadJWT.getClaimId(token);
			//Canal
			//String claimCanal = this.seguridadJWT.getClaimCanal(token);
			//IP
			//String ip = HttpUtil.getClientIp(request);
        	
            List<CiudadanoDTO> data = super.getAdmCiudadano().getAll();
            respuesta.setData(data);
            return ResponseEntity.ok(respuesta);
        } 
        catch (Exception e) {
            return super.manejarExcepcionWS(CiudadanoCtl.class,respuesta, e);
        }
    }

    @GetMapping("/{id}")
	public ResponseEntity<Response<CiudadanoDTO>> getById(
													@PathVariable("id") final Integer id,
													HttpServletRequest request
													){
		Response<CiudadanoDTO> respuesta = new Response<>();		
		try {
			//Log se hace en AutenticationFilterJWT
			
			//Realiza la consulta
			CiudadanoDTO data = super.getAdmCiudadano().getById(id);
						
			//Asigna a la respuesta
			respuesta.setData(data);
			return ResponseEntity.ok(respuesta);
		}
        catch (Exception e) {
            return super.manejarExcepcionWS(CiudadanoCtl.class,respuesta, e);
        }
	}
	

    @PostMapping("/guardar")
	public ResponseEntity<Response<CiudadanoDTO>> guardar(
													@RequestBody CiudadanoDTO dto,
													HttpServletRequest request
													){
		Response<CiudadanoDTO> respuesta = new Response<>();		
		try {
			//Log se hace en AutenticationFilterJWT
			
			//Realiza la operacion
			CiudadanoDTO data = super.getAdmCiudadano().guardar(dto);
						
			//Asigna a la respuesta
			respuesta.setData(data);
			return ResponseEntity.ok(respuesta);
		}
        catch (Exception e) {
            return super.manejarExcepcionWS(CiudadanoCtl.class,respuesta, e);
        }
	}
	
    @PostMapping("/eliminar")
	public ResponseEntity<Response<Boolean>> eliminar(
													@RequestBody CiudadanoDTO dto,
													HttpServletRequest request
													){
		Response<Boolean> respuesta = new Response<>();		
		try {
			//Log se hace en AutenticationFilterJWT
			
			//Realiza la operacion
			super.getAdmCiudadano().eliminar(dto);
						
			//Asigna a la respuesta
			respuesta.setData(true);
			return ResponseEntity.ok(respuesta);
		}
        catch (Exception e) {
            return super.manejarExcepcionWS(CiudadanoCtl.class,respuesta, e);
        }
	}
	
    @PostMapping("/filtro")
	public ResponseEntity<ListResponse<CiudadanoDTO>> getByFiltro(
													@RequestBody CiudadanoDTO filtro,
													HttpServletRequest request
													){
		ListResponse<CiudadanoDTO> respuesta = new ListResponse<>();		
		try {
			//Log se hace en AutenticationFilterJWT
			
			//Realiza la consulta
			List<CiudadanoDTO> data = super.getAdmCiudadano().getByFiltro(filtro);
						
			//Asigna a la respuesta
			respuesta.setData(data);
			return ResponseEntity.ok(respuesta);
		}
        catch (Exception e) {
            return super.manejarExcepcionWS(CiudadanoCtl.class,respuesta, e);
        }	
    }    
}