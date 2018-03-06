package com.proinsalud.sistemas.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.model.UploadedFile;

/**
 * @author Mauricio Pinchao
 * @datetime 24/01/2018 - 9:10:28 a. m.
 *
 */
public class LoadFileExcel {

	private static final Log LOG = App.getLogger(LoadFileExcel.class);

	private static final String DIR_TEMPS = "temps";
	private static String EXT_FILE = "xlsx";

	private static String rutaFinalExcel = "";

	public LoadFileExcel() {
		super();
	}

	public String getPathBase() {
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/secured/reportes/") + File.separator;
	}

	private String getPathBaseTemps() {
		return getPathBase() + DIR_TEMPS + File.separator;
	}

	public File crearArchivo(UploadedFile uFile) {
		File fileTemp = null;
		File dir = null;
		try {
			String archivoCargado = uFile.getFileName();
			InputStream in = uFile.getInputstream();

			String ext = archivoCargado.split("\\.")[1];
			if (EXT_FILE.equals(ext)) {
				String rutaCarpetaTemp = getPathBaseTemps();
				File carpeta = new File(rutaCarpetaTemp);
				if (!carpeta.exists()) {
					carpeta.mkdirs();
				}

				dir = new File(rutaCarpetaTemp + System.currentTimeMillis());
				while (dir.exists()) {
					dir = new File(rutaCarpetaTemp + System.currentTimeMillis() + Math.random());
				}
				dir.mkdirs();
				String directorioExcel = dir.getPath() + File.separator;
				fileTemp = new File(directorioExcel + archivoCargado);
				rutaFinalExcel = fileTemp.getPath();
				if (fileTemp.exists()) {
					fileTemp.delete();
				}
				OutputStream out = new FileOutputStream(fileTemp);
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = in.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				in.close();
				out.flush();
				out.close();
				System.out.println("New file created!");
			}
			return fileTemp;

		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			return null;
		}
	}

	/**
	 * Explanation of the method by which we read the excel file we pass as
	 * parameter if exists, we return the excel file values in an ArrayList<>.
	 * Explicación del método con el que leemos el fichero excel que pasamos como
	 * parámetro si existe, devolvemos los valores de la hoja excel en un
	 * ArrayList<>.
	 * <h3>Example (Ejemplo)</h3>
	 * 
	 * <pre>
	 * JavaPoiUtils javaPoiUtils = new JavaPoiUtils();
	 * javaPoiUtils.readExcelFile(new File("/home/xules/codigoxules/apachepoi/PaisesIdiomasMonedas.xls"));
	 * </pre>
	 *
	 * @param excelFile
	 *            <code>String</code> excel File we are going to read. Fichero excel
	 *            que vamos a leer.
	 * @return <code>ArrayList<String[]></code> we return the excel file values in
	 *         an ArrayList<> (devolvemos los valores de la hoja excel en un
	 *         ArrayList<>).
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<ArrayList<String[]>> readExcelFileToArray(UploadedFile uploadFile) {
		ArrayList<ArrayList<String[]>> datosHojas = new ArrayList<>();
		InputStream excelStream = null;
		try {
			File createdExcel = crearArchivo(uploadFile);
			excelStream = new FileInputStream(createdExcel);
			XSSFWorkbook workbook = new XSSFWorkbook(excelStream);
			int numeroHojas = workbook.getNumberOfSheets();
			for (int i = 0; i < numeroHojas; i++) {
				ArrayList<String[]> datosFilasPorHoja = new ArrayList<>();
				XSSFSheet sheetTemp = workbook.getSheetAt(i);
				for (Row row : sheetTemp) {
					String[] datosFila = new String[row.getLastCellNum()];
					for (Cell cell : row) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							if (DateUtil.isCellDateFormatted(cell)) {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								datosFila[cell.getColumnIndex()] = cell.getStringCellValue();
							} else {
								cell.setCellType(Cell.CELL_TYPE_STRING);
								datosFila[cell.getColumnIndex()] = cell.getStringCellValue();
							}
							break;
						case Cell.CELL_TYPE_STRING:
							datosFila[cell.getColumnIndex()] = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							datosFila[cell.getColumnIndex()] = "" + cell.getBooleanCellValue();
							break;
						case Cell.CELL_TYPE_BLANK:
							datosFila[cell.getColumnIndex()] = "BLANK";
							break;
						case Cell.CELL_TYPE_FORMULA:
							datosFila[cell.getColumnIndex()] = "FORMULA";
							break;
						case Cell.CELL_TYPE_ERROR:
							datosFila[cell.getColumnIndex()] = "ERROR";
							break;
						}
					}
					datosFilasPorHoja.add(datosFila);
				}
				datosHojas.add(datosFilasPorHoja);
				workbook.close();
			}
			deleteFileTemps();
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		} finally {
			try {
				excelStream.close();
			} catch (Exception e) {
				UtilWeb.printError(LOG, e);
			}
		}
		return datosHojas;
	}

	public static void deleteFileTemps() {
		File fi = new File(rutaFinalExcel);
		eliminarExcelTemp(fi.getParentFile());
	}

	public static void eliminarExcelTemp(File directorio) {
		if (directorio != null) {
			File[] ficheros = directorio.listFiles();
			if (ficheros != null) {
				for (int i = 0; i < ficheros.length; i++) {
					if (ficheros[i].isDirectory()) {
						eliminarExcelTemp(ficheros[i]);
					}
					ficheros[i].delete();
				}
			}
			if (directorio.exists()) {
				directorio.delete();
			}
		}
	}
}
