package cr.infosgroup.padron.bl.adm.interfaces;

import java.util.List;

import cr.infosgroup.padron.bl.exception.BLException;
import cr.infosgroup.padron.dto.DireccionElectoralDTO;

public interface IAdmDireccionElectoral {
	
	public List<DireccionElectoralDTO> getAll() throws BLException;

	public DireccionElectoralDTO getById(Integer id) throws BLException;
		
	public DireccionElectoralDTO guardar(DireccionElectoralDTO dto) throws BLException;
	
	public void eliminar(DireccionElectoralDTO dto) throws BLException;
	
	public List<DireccionElectoralDTO> getByFiltro(DireccionElectoralDTO filtro) throws BLException;
	
}
