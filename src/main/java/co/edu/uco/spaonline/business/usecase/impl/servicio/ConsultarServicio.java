package co.edu.uco.spaonline.business.usecase.impl.servicio;

import java.util.List;
import java.util.UUID;

import co.edu.uco.spaonline.business.assembler.entity.impl.ServicioAssemblerEntity;
import co.edu.uco.spaonline.business.domain.ServicioDomain;
import co.edu.uco.spaonline.business.usecase.UseCaseWithReturn;
import co.edu.uco.spaonline.crosscutting.exceptions.custom.BusinessSpaOnlineException;
import co.edu.uco.spaonline.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.edu.uco.spaonline.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.edu.uco.spaonline.crosscutting.helpers.ObjectHelper;
import co.edu.uco.spaonline.crosscutting.helpers.TextHelper;
import co.edu.uco.spaonline.data.dao.factory.DAOFactory;
import co.edu.uco.spaonline.entity.ServicioEntity;
import co.edu.uco.spaonline.entity.TipoServicioEntity;

public class ConsultarServicio implements UseCaseWithReturn<ServicioDomain, List<ServicioDomain>>{

	private DAOFactory factory;
	
	public ConsultarServicio (final DAOFactory factory){
		 if(ObjectHelper.isNull(factory)) {
			 var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);
			 var mensajeTecnico= MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00052);
			 throw new BusinessSpaOnlineException(mensajeUsuario, mensajeTecnico);
		 }
		 this.factory = factory;
	}
	@Override
	public List<ServicioDomain> execute(ServicioDomain data) {
		validarIntegridadDato(data);
		validarServicioMismoNombreMismoTipoServicio(data.getNombre(), data.getTiposervicio().getId());
		var servicioEntityfilter= ServicioAssemblerEntity.getinstace().toEntity(data);
		var resultadosEntity = factory.getServicioDAO().consultar(servicioEntityfilter);
		
		return ServicioAssemblerEntity.getinstace().toDomainCollection(resultadosEntity);
	}
	
	private final void validarServicioMismoNombreMismoTipoServicio (final String nombreservicio, final UUID tipoServicio){
		var servicioEntity = ServicioEntity.build().setNombre(nombreservicio).setTiposervicio(TipoServicioEntity.build(tipoServicio, TextHelper.EMPTY));
		var resultados = factory.getServicioDAO().consultar(servicioEntity);
		if(resultados.isEmpty()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00053);
			throw new BusinessSpaOnlineException(mensajeUsuario);
		}
	}
	
	public void validarIntegridadDato(ServicioDomain dato) {
		if(!ObjectHelper.esNulooVacio(dato)) {
			validarLongitud(dato.getNombre());
		}		
	}
	
	private final void validarLongitud(final String dato) {
		if(!TextHelper.longitudMaximaValida(dato,50)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054);
			throw new BusinessSpaOnlineException(mensajeUsuario);
		}
	}

}
