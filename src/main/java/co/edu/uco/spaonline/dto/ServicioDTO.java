package co.edu.uco.spaonline.dto;


import java.util.UUID;

import co.edu.uco.spaonline.crosscutting.helpers.NumHelper;
import co.edu.uco.spaonline.crosscutting.helpers.TextHelper;
import co.edu.uco.spaonline.crosscutting.helpers.UUIDHelper;

public final class ServicioDTO {
	
	private UUID id;
	private String nombre;
	private String descipcion;
	private TipoServicioDTO tiposervicio;
	private Long tarifa;
	
	private ServicioDTO() {
		setId(UUIDHelper.generarUUIDDefecto());
		setNombre(TextHelper.EMPTY);
		setDescipcion(TextHelper.EMPTY);
		setTiposervicio(TipoServicioDTO.build());
		setTarifa(NumHelper.NUM_DEFECT);
	}
	
	private ServicioDTO(final UUID id,final String nombre,final String descipcion,final TipoServicioDTO tiposervicio,final Long tarifa) {
		setId(id);
		setNombre(nombre);
		setDescipcion(descipcion);
		setTiposervicio(tiposervicio);
		setTarifa(tarifa);
	}
	

	public static final ServicioDTO build() {
		return new ServicioDTO();
	}
	
	public final UUID getId() {
		return id;
	}
	
	public final ServicioDTO setId(UUID id) {
		this.id = id;
		return this;
	}
	
	public final String getNombre() {
		return nombre;
	}
	
	public final ServicioDTO setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public final String getDescipcion() {
		return descipcion;
	}
	
	public final ServicioDTO setDescipcion(String descipcion) {
		this.descipcion = descipcion;
		return this;
	}
	
	public final TipoServicioDTO getTiposervicio() {
		return tiposervicio;
	}
	
	public final ServicioDTO setTiposervicio(TipoServicioDTO tiposervicio) {
		this.tiposervicio = tiposervicio;
		return this;
	}
	
	public final Long getTarifa() {
		return tarifa;
	}
	
	public final ServicioDTO setTarifa(Long tarifa) {
		this.tarifa = tarifa;
		return this;
	}
	
}
