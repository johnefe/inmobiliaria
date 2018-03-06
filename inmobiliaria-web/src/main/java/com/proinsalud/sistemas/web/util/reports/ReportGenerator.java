package com.proinsalud.sistemas.web.util.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author Mauricio Pinchao
 * @datetime 17/01/2018 - 10:34:05 a. m.
 *
 */
public class ReportGenerator {

	private static final Log LOG = App.getLogger(ReportGenerator.class);

	private static final String DIR_TEMPS = "temps";
	private String path = null;
	private static String EXT_ZIP = ".zip";

	public ReportGenerator() {
		super();
		path = createDir();
	}

	public Connection getConnection() {
		Connection conn = null;
		Properties properties = new Properties();
		try {
			properties.load(Messages.class.getClassLoader().getResourceAsStream("application.properties"));
			Class.forName(properties.getProperty("jdbc.driverClassName"));
			conn = DriverManager.getConnection(properties.getProperty("jdbc.url"), properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
			conn.setAutoCommit(false);
			return conn;
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			return null;
		}
	}

	/**
	 * Retorna la ruta donde se encuentran las plantillas de los reportes que se van
	 * a compilar; es la ruta base.
	 * 
	 * @author Mauricio Pinchao
	 * @datetime 22/01/2018 - 11:34:29 a. m.
	 *
	 * @return
	 */
	private String getPathBase() {
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/secured/reportes/") + File.separator;
	}

	/**
	 * Retorna la ruta donde se van a almacenar todos los reportes generados.
	 * 
	 * @author Mauricio Pinchao
	 * @datetime 22/01/2018 - 11:33:48 a. m.
	 *
	 * @return
	 */
	private String getPathBaseTemps() {
		return getPathBase() + DIR_TEMPS + File.separator;
	}

	/**
	 * Este metodo genera el reporte de Personas, recibe los parametros con los que
	 * se va a generar el reporte.
	 * 
	 * @author Mauricio Pinchao
	 * @datetime 22/01/2018 - 11:28:46 a. m.
	 * @param size
	 *            - Es el tamaño de la lista de personas
	 * @return
	 * @throws Exception
	 */
	public String generarReportePersonas(int size) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(RI.ListadoPersonas.REPORT_COUNT, size);
		return generarReporte(parameters, getPathBase() + RI.ListadoPersonas.NAME, RI.ListadoPersonas.NAME_FILE);
	}

	/**
	 * Este metodo se encarga de generar los reportes en PDF. Recibe los parametros
	 * para rellenar el reporte, la ruta y el nombre del archivo que se va a
	 * exportar.
	 * 
	 * @author Mauricio Pinchao
	 * @datetime 19/01/2018 - 7:34:10 a. m.
	 *
	 * @param parameters
	 *            - Mapa de parametros con los que se imprime el reporte.
	 * @param path
	 *            - Es la ruta donde se encuentra el reporte para compilar.
	 * @param nombrepdf
	 *            - Es el nombre del archivo resultante (*.pdf)
	 * @return Retorna la ruta donde se guarda el reporte generado.
	 * @throws Exception
	 */
	private String generarReporte(Map<String, Object> parameters, String path, String nombrepdf) throws Exception {
		String ruta = new String();
		try {
			JasperReport report = JasperCompileManager.compileReport(path);
			JasperPrint print = JasperFillManager.fillReport(report, parameters, getConnection());
			ruta = createDir() + nombrepdf;
			JasperExportManager.exportReportToPdfFile(print, ruta);

			if (getConnection() != null) {
				getConnection().rollback();
				getConnection().close();
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
		return ruta;
	}

	/**
	 * Este metodo genera un reporte en archivo XLS, para ello recibe la cantidad de
	 * registros, los titulos que lleva el archivo y los datos para llenarlo.
	 * 
	 * @author Mauricio Pinchao
	 * @datetime 23/01/2018 - 4:27:07 p. m.
	 * @param size
	 * @param titulos
	 * @param datos
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public String generarReporteExcel(String[] titulos, String[][] datos) throws IOException {
		String rutaArchivo = path + RI.ListadoPersonas.NAME_FILE_XLS;
		File archivo = new File(rutaArchivo);
		Workbook workbook = new XSSFWorkbook();

		Sheet pagina = workbook.createSheet("Listado de personas");
		CellStyle style = workbook.createCellStyle();
		// style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		// style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.BLACK.index);
		style.setFont(font);
		Row fila = pagina.createRow(0);
		for (int i = 0; i < titulos.length; i++) {
			Cell celda = fila.createCell(i);
			celda.setCellStyle(style);
			celda.setCellValue(titulos[i]);
		}
		for (int i = 0; i < datos.length; i++) {
			fila = pagina.createRow(i + 1);
			for (int j = 0; j < datos[i].length; j++) {
				Cell celda = fila.createCell(j);
				celda.setCellValue(datos[i][j]);
				pagina.autoSizeColumn(j, true);
				celda.setCellType(CellType.STRING);
			}
		}
		try {
			FileOutputStream salida = new FileOutputStream(archivo);
			workbook.write(salida);
			workbook.close();
		} catch (FileNotFoundException ex) {
			UtilWeb.printError(LOG, ex);
		}
		return rutaArchivo;
	}

	public String generarReporteExcel(String[][] datos, String nameReport) throws IOException {
		try {
			String rutaArchivo = path + nameReport;
			File archivo = new File(rutaArchivo);
			Workbook workbook = new XSSFWorkbook();
			Sheet pagina = workbook.createSheet(nameReport);
			for (int i = 0; i < datos.length; i++) {
				Row fila = pagina.createRow(i);
				for (int j = 0; j < datos[i].length; j++) {
					Cell celda = fila.createCell(j);
					celda.setCellValue(datos[i][j]);
					pagina.autoSizeColumn(j, true);
					celda.setCellType(CellType.STRING);
				}
			}
			FileOutputStream salida = new FileOutputStream(archivo);
			workbook.write(salida);
			workbook.close();
			return rutaArchivo;
		} catch (FileNotFoundException ex) {
			UtilWeb.printError(LOG, ex);
			return null;
		}
	}

	/**
	 * Este metodo elimina los archivos temporales generados en la ruta que se pasa
	 * como parametro.
	 * 
	 * @author Mauricio Pinchao
	 * @datetime 22/01/2018 - 11:30:57 a. m.
	 * @param path
	 *            - La ruta donde se encuentran los archivos
	 */
	public static void deleteFileTemps(String path) {
		File fi = new File(path);
		eliminarDirectorio(fi.getParentFile());
	}

	/**
	 * Elimina la carpeta que contiene los archivos planos temporales, utilizando
	 * recursividad
	 * 
	 * @descripcion
	 * @autor Andres Santacruz [andressantacruz000@gmail.com]
	 * @fecha 4/05/2016
	 *
	 * @param directorio
	 */
	private static void eliminarDirectorio(File directorio) {
		if (directorio != null) {
			File[] ficheros = directorio.listFiles();
			if (ficheros != null) {
				for (int i = 0; i < ficheros.length; i++) {
					if (ficheros[i].isDirectory()) {
						eliminarDirectorio(ficheros[i]);
					}
					ficheros[i].delete();
				}
			}
			if (directorio.exists()) {
				directorio.delete();
			}
		}
	}

	/**
	 * Metodo que crea un directorio dentro del proyecto y retorna la ruta de dicho
	 * directorio.
	 * 
	 * @author Mauricio Pinchao
	 * @datetime 22/01/2018 - 11:32:29 a. m.
	 * @return path - Es la ruta del directorio creado.
	 */
	private String createDir() {
		File dir = null;
		String pathBase = path == null ? getPathBaseTemps() : path;
		dir = new File(pathBase + System.currentTimeMillis());
		while (dir.exists()) {
			dir = new File(pathBase + System.currentTimeMillis() + Math.random());
		}
		dir.mkdirs();
		return dir.getPath() + File.separator;
	}

	/**
	 * Crea un archivo zip con los archivos pasados en la lista por parametros
	 * 
	 * @author Andres Santacruz
	 * @datetime 22/01/2018 - 10:35:59 a. m.
	 * @param files
	 *            - lista de los archivos
	 * @param nameZip
	 *            - nombre final de archivo zip
	 * @return
	 * @throws ZipException
	 */
	public String createZip(ArrayList<File> files, String nameZip) throws ZipException {
		ZipFile zipFile = new ZipFile(path + nameZip + EXT_ZIP);
		ZipParameters parameters = new ZipParameters();
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		zipFile.addFiles(files, parameters);
		return zipFile.getFile().getPath();
	}

}
