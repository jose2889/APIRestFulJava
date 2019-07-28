package com.notas.demo.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.notas.demo.converter.Convertidor;
import com.notas.demo.entity.Nota;
import com.notas.demo.repository.NotaRepositorio;
import com.notas.demo.model.MNota;

@Service("servicio")
public class NotaService {

	@Autowired
	@Qualifier("repositorio")
	private NotaRepositorio repositorio;
	
	@Autowired
	@Qualifier("convertidor")
	private Convertidor convertidor;
	
	private static final Log logger = LogFactory.getLog(NotaService.class);
	
	public boolean crear(Nota nota) {
		logger.warn("CREANDO NOTA");
		try {
			repositorio.save(nota);
			logger.info("NOTA CREADA");
			return true;
		}catch(Exception e){
			logger.error("HUBO UN ERROR");
			return false;
		}
	}
	
	public boolean actualizar(Nota nota) {
		logger.warn("ACTUALIZANDO NOTA");
		try {
			repositorio.save(nota);
			logger.info("NOTA ACTUALIZADA");
			return true;
		}catch(Exception e){
			logger.error("HUBO UN ERROR");
			return false;
		}
	}
	
	public boolean borrar(String nombre, long id) {
		logger.warn("BORRANDO NOTA");
		try {
			Nota nota = repositorio.findByNombreAndId(nombre, id);
			logger.info("NOTA BORRADA");
			repositorio.delete(nota);
			return true;
		}catch(Exception e){
			logger.error("HUBO UN ERROR");
			return false;
		}
	}
	
	public List<MNota> obtener() {
		logger.warn("LISTANDO NOTAS");
		return convertidor.convertirLista(repositorio.findAll());
	}
	
	public MNota obtenerPorNombreTitulo(String nombre, String titulo) {
		
		return new MNota(repositorio.findByNombreAndTitulo(nombre, titulo));
	}
	
	public List<MNota> obtenerTitulo(String titulo) {
		
		return convertidor.convertirLista(repositorio.findByTitulo(titulo));
	}
}
