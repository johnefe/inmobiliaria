package com.proinsalud.sistemas.web.util.reports;

/**
 * Es la clase para la informacion de los reportes
 * 
 * @author Andres Santacruz
 * @datetime 22/01/2018 - 9:34:25 a. m.
 *
 */
public class RI {

	/**
	 * Es el reporte para el listado de las personas
	 * 
	 * @author Andres Santacruz
	 * @datetime 22/01/2018 - 9:36:04 a. m.
	 *
	 */
	public class ListadoPersonas {
		public static final String NAME = "Listado_personas.jrxml";
		public static final String NAME_FILE = "personas.pdf";
		public static final String REPORT_COUNT = "REPORT_COUNT";
		public static final String NAME_FILE_XLS = "personas.xlsx";
	}
	
	public class Convocatoria{
		public static final String COTIZAR_PRODUCTOS = "cotizar_productos.xlsx";
	}

}
