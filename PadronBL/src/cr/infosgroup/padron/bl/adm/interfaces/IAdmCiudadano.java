package cr.infosgroup.padron.bl.adm.interfaces;

import java.util.List;

import cr.infosgroup.padron.bl.exception.BLException;
import cr.infosgroup.padron.dto.CiudadanoDTO;

public interface IAdmCiudadano {

	public List<CiudadanoDTO> getAll() throws BLException;

	public CiudadanoDTO getById(Integer id) throws BLException;
		
	public CiudadanoDTO guardar(CiudadanoDTO dto) throws BLException;
	
	public void eliminar(CiudadanoDTO dto) throws BLException;
	
	public List<CiudadanoDTO> getByFiltro(CiudadanoDTO filtro) throws BLException;
}
