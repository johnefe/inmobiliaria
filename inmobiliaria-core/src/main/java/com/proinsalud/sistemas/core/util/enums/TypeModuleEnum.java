package com.proinsalud.sistemas.core.util.enums;

/**
 * @author Mauricio Pinchao
 * @datetime 9/02/2018 - 4:24:58 p. m.
 *
 */
public enum TypeModuleEnum {
	ModuloAtencion("Módulo Atención"), 
	ModuloAyuda("Módulo Ayuda"), 
	ModuloTv("Módulo Tv");
	
	private  String label;
	
	private TypeModuleEnum(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
