package cr.infosgroup.padron.bl.adm;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cr.infosgroup.padron.bl.adm.interfaces.IAdmCiudadano;
import cr.infosgroup.padron.bl.exception.BLException;
import cr.infosgroup.padron.bl.exception.ErrorBL;
import cr.infosgroup.padron.dal.dao.CiudadanoDAO;
import cr.infosgroup.padron.dal.entity.Ciudadano;
import cr.infosgroup.padron.dto.CiudadanoDTO;

@Component("admCiudadano")
@Transactional("transactionManager")
public class AdmCiudadano extends BaseAdm implements IAdmCiudadano{
	
	/**
	 * Manejo de logs
	 */
//	private static final Logger log = LogManager.getLogger(AdmCiudadano.class);
	
    @Autowired
    private CiudadanoDAO ciudadanoDAO;
    
    @Autowired
    private ModelMapper modelMapper;

	private Ciudadano insertOrUpdate(Ciudadano entidad) throws BLException {
		return super.ejecutarConManejoDeErroresBL(AdmCiudadano.class, () -> {
			if (entidad == null) {
				throw new BLException(ErrorBL.ErrorBL_Gen_Adm_0);
			}
			
			Ciudadano entidadBD = (entidad.getCedula() != null) ? this.ciudadanoDAO.findById(entidad.getCedula()) : null;

			Ciudadano respuesta;
			if (entidadBD == null) {
				respuesta = this.ciudadanoDAO.insert(entidad);
			} else {
				entidad.setFecCreacion(entidadBD.getFecCreacion());
				entidad.setUsrCreacion(entidadBD.getUsrCreacion());
				respuesta = this.ciudadanoDAO.update(entidad);
			}

			return respuesta;
		});
	}
	
	private void remove(Ciudadano entidad) throws BLException {
		super.ejecutarSinResultadoConManejoDeErroresBL(AdmCiudadano.class, () -> {
			if (entidad == null) {
				throw new BLException(ErrorBL.ErrorBL_Gen_Adm_0);
			}

			Ciudadano ciudadanoBD = (entidad.getCedula() != null) ? this.ciudadanoDAO.findById(entidad.getCedula()) : null;

			if (ciudadanoBD != null) {
				this.ciudadanoDAO.remove(entidad);
			}
		});
	}
    
    
	@Override
	public List<CiudadanoDTO> getAll() throws BLException {
		return super.ejecutarConManejoDeErroresBL(AdmCiudadano.class, () -> {
			List<Ciudadano> respuesta = ciudadanoDAO.findAll();
			return respuesta.stream().map(c -> modelMapper.map(c, CiudadanoDTO.class)).collect(Collectors.toList());
		});
	}

	@Override
	public CiudadanoDTO getById(Integer id) throws BLException {
		return super.ejecutarConManejoDeErroresBL(AdmCiudadano.class, () -> {
			Ciudadano resultado = ciudadanoDAO.findById(id);
			if (resultado == null) {
				throw new BLException(ErrorBL.ErrorBL_Gen_Ent_0);
			}
			return modelMapper.map(resultado, CiudadanoDTO.class);
		});
	}
	
	@Override
	public CiudadanoDTO guardar(CiudadanoDTO dto) throws BLException {
		return super.ejecutarConManejoDeErroresBL(AdmCiudadano.class, () -> {
			if (dto == null) {
				throw new BLException(ErrorBL.ErrorBL_Gen_DTO);
			}

			//DEMAS VALIDACIONES SI APLICA
			
			//Convertir el dto en entidad
			Ciudadano entidad = modelMapper.map(dto, Ciudadano.class);
			
			//Realiza la operacion
			Ciudadano resultado = this.insertOrUpdate(entidad);
			
			//Retorna DTO
			return modelMapper.map(resultado, CiudadanoDTO.class);
		});
	}
	
	@Override
	public void eliminar(CiudadanoDTO dto) throws BLException {
		super.ejecutarSinResultadoConManejoDeErroresBL(AdmCiudadano.class, () -> {
			if (dto == null) {
				throw new BLException(ErrorBL.ErrorBL_Gen_DTO);
			}

			//Convertir el dto en entidad
			Ciudadano entidad = modelMapper.map(dto, Ciudadano.class);
			
			//Realiza la operacion
			this.remove(entidad);
		});
	}

	@Override
	public List<CiudadanoDTO> getByFiltro(CiudadanoDTO filtro) throws BLException {
		return super.ejecutarConManejoDeErroresBL(AdmCiudadano.class, () -> {
			List<Ciudadano> respuesta = super.getFinderCiudadano().findByFiltro(filtro);
			return respuesta.stream().map(c -> modelMapper.map(c, CiudadanoDTO.class)).collect(Collectors.toList());
		});
	}

}
