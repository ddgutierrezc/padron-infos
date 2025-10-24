package cr.infosgroup.padron.bl.adm;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cr.infosgroup.padron.bl.adm.interfaces.IAdmDireccionElectoral;
import cr.infosgroup.padron.bl.exception.BLException;
import cr.infosgroup.padron.bl.exception.ErrorBL;
import cr.infosgroup.padron.dal.dao.DireccionElectoralDAO;
import cr.infosgroup.padron.dal.entity.DireccionElectoral;
import cr.infosgroup.padron.dto.DireccionElectoralDTO;

@Component("admDireccionElectoral")
@Transactional("transactionManager")
public class AdmDireccionElectoral extends BaseAdm implements IAdmDireccionElectoral{
	
	/**
	 * Manejo de logs
	 */
	//private static final Logger log = LogManager.getLogger(AdmDireccionElectoral.class);
	
    @Autowired
    private DireccionElectoralDAO DireccionElectoralDAO;
    
    @Autowired
    private ModelMapper modelMapper;

	private DireccionElectoral insertOrUpdate(DireccionElectoral entidad) throws BLException {
		return super.ejecutarConManejoDeErroresBL(AdmDireccionElectoral.class, () -> {
			if (entidad == null) {
				throw new BLException(ErrorBL.ErrorBL_Gen_Adm_0);
			}
			
			DireccionElectoral entidadBD = (entidad.getCodDirElectoral() != null) ? this.DireccionElectoralDAO.findById(entidad.getCodDirElectoral()) : null;

			DireccionElectoral respuesta;
			if (entidadBD == null) {
				respuesta = this.DireccionElectoralDAO.insert(entidad);
			} else {
				entidad.setFecCreacion(entidadBD.getFecCreacion());
				entidad.setUsrCreacion(entidadBD.getUsrCreacion());
				respuesta = this.DireccionElectoralDAO.update(entidad);
			}

			return respuesta;
		});
	}
	
	private void remove(DireccionElectoral entidad) throws BLException {
		super.ejecutarSinResultadoConManejoDeErroresBL(AdmDireccionElectoral.class, () -> {
			if (entidad == null) {
				throw new BLException(ErrorBL.ErrorBL_Gen_Adm_0);
			}

			DireccionElectoral DireccionElectoralBD = (entidad.getCodDirElectoral() != null) ? this.DireccionElectoralDAO.findById(entidad.getCodDirElectoral()) : null;

			if (DireccionElectoralBD != null) {
				this.DireccionElectoralDAO.remove(entidad);
			}
		});
	}
    
    
	@Override
	public List<DireccionElectoralDTO> getAll() throws BLException {
		return super.ejecutarConManejoDeErroresBL(AdmDireccionElectoral.class, () -> {
			List<DireccionElectoral> respuesta = DireccionElectoralDAO.findAll();
			return respuesta.stream().map(c -> modelMapper.map(c, DireccionElectoralDTO.class)).collect(Collectors.toList());
		});
	}

	@Override
	public DireccionElectoralDTO getById(Integer id) throws BLException {
		return super.ejecutarConManejoDeErroresBL(AdmDireccionElectoral.class, () -> {
			DireccionElectoral resultado = DireccionElectoralDAO.findById(id);
			if (resultado == null) {
				throw new BLException(ErrorBL.ErrorBL_Gen_Ent_0);
			}
			return modelMapper.map(resultado, DireccionElectoralDTO.class);
		});
	}
	
	@Override
	public DireccionElectoralDTO guardar(DireccionElectoralDTO dto) throws BLException {
		return super.ejecutarConManejoDeErroresBL(AdmDireccionElectoral.class, () -> {
			if (dto == null) {
				throw new BLException(ErrorBL.ErrorBL_Gen_DTO);
			}

			//DEMAS VALIDACIONES SI APLICA
			
			//Convertir el dto en entidad
			DireccionElectoral entidad = modelMapper.map(dto, DireccionElectoral.class);
			
			//Realiza la operacion
			DireccionElectoral resultado = this.insertOrUpdate(entidad);
			
			//Retorna DTO
			return modelMapper.map(resultado, DireccionElectoralDTO.class);
		});
	}
	
	@Override
	public void eliminar(DireccionElectoralDTO dto) throws BLException {
		super.ejecutarSinResultadoConManejoDeErroresBL(AdmDireccionElectoral.class, () -> {
			if (dto == null) {
				throw new BLException(ErrorBL.ErrorBL_Gen_DTO);
			}

			//Convertir el dto en entidad
			DireccionElectoral entidad = modelMapper.map(dto, DireccionElectoral.class);
			
			//Realiza la operacion
			this.remove(entidad);
		});
	}

	@Override
	public List<DireccionElectoralDTO> getByFiltro(DireccionElectoralDTO filtro) throws BLException {
		return super.ejecutarConManejoDeErroresBL(AdmDireccionElectoral.class, () -> {
			List<DireccionElectoral> respuesta = super.getFinderDireccionElectoral().findByFiltro(filtro);
			return respuesta.stream().map(c -> modelMapper.map(c, DireccionElectoralDTO.class)).collect(Collectors.toList());
		});
	}

}
