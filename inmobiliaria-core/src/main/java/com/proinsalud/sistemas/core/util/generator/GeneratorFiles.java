package com.proinsalud.sistemas.core.util.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * @author Jhon Frey Diaz
 * @datetime 28/11/2017 - 10:33:58 a. m.
 *
 */
public class GeneratorFiles {

	public static final String l = File.separator;
	public static final String S = "\r\n";
	public static final String BASE = l + "src" + l + "main" + l + "java";
	public static final String BASE_PACKAGE = "com" + l + "proinsalud" + l + "sistemas" + l + "core";
	public static final String BASE_PROJECT_DIR = BASE + l + BASE_PACKAGE;
	public static final String DIR = System.getProperty("user.dir") + BASE_PROJECT_DIR;
	public static final String FOLDER_MODEL = "model";
	public static final String FOLDER_DAO = "dao";
	public static final String FOLDER_SERVICE = "service";
	public static final String FOLDER_IMPL = "impl";

	public static final String SERVICE = "Service";
	public static final String SERVICE_IMPL = "service_impl";
	public static final String DAO = "Dao";
	public static final String DAO_IMPL = "dao_impl";

	public static final String EXT = ".java";
	public static final String I = "I";

	public static final String REPLACE_FOLDER = "@FOLDER@";
	public static final String REPLACE_SCHEMA = "@SCHEMA@";
	public static final String REPLACE_MODEL = "@MODEL@";
	public static final String REPLACE_REPOSITORY = "@REPOSITORY@";
	public static final String REPLACE_PHASE = "@PHASE@";

	public static final String TITLE_MSG = "*** Generador de Archivos ***";

	public static final String TEMP_DAO_SERVICE = "package com.proinsalud.sistemas.core.@SCHEMA@.@FOLDER@;\r\n" + "\r\n" + 
			"import java.util.List;\r\n" + "\r\n" + 
			"import com.proinsalud.sistemas.core.@SCHEMA@.model.@MODEL@;\r\n" + "\r\n" + 
			"public interface I@MODEL@@PHASE@ {\r\n" + "\r\n" + 
						"	public @MODEL@ persistEntity(@MODEL@ entity);\r\n" + "	\r\n" + 
						"	public @MODEL@ mergeEntity(@MODEL@ entity);\r\n" + "	\r\n" + 
						"	public void deleteEntity(@MODEL@ entity);\r\n" + "	\r\n" + 
						"	public List<@MODEL@> findAllEntity();\r\n" + "	\r\n" +
						"	public @MODEL@ findEntityById(Long id);\r\n" + "	\r\n" + 
						"	public void persistEntity(List<@MODEL@> entities);\r\n" + "	\r\n" + 
						"	public void mergeEntity(List<@MODEL@> entities);\r\n" + "	\r\n" + 
						"	public void deleteEntity(List<@MODEL@> entities);\r\n" + "	\r\n" + 
						"}";

	public static final String TEMP_DAO_IMPL = "package com.proinsalud.sistemas.core.@SCHEMA@.dao.impl;\r\n" + "\r\n" + 
			"import java.io.Serializable;\r\n" + "import java.util.List;\r\n" + "\r\n" + 
			"import org.springframework.stereotype.Repository;\r\n" + "\r\n"+ 
			"import com.proinsalud.sistemas.core.@SCHEMA@.dao.I@MODEL@Dao;\r\n" + 
			"import com.proinsalud.sistemas.core.@SCHEMA@.model.@MODEL@;\r\n" + 
			"import com.proinsalud.sistemas.core.generic.GenericDao;\r\n" + "\r\n" + 
			"@Repository(value = \"@REPOSITORY@Dao\")\r\n"	+ 
						"public class @MODEL@Dao extends GenericDao<Long, @MODEL@> implements I@MODEL@Dao, Serializable {\r\n" + "\r\n" + 
						"	public @MODEL@ persistEntity(@MODEL@ entity) {\r\n" + 
						"		return super.persist(entity);\r\n" + 
						"	}\r\n" + "\r\n"+ 
						"	public @MODEL@ mergeEntity(@MODEL@ entity) {\r\n" + 
						"		return super.merge(entity);\r\n" + 
						"	}\r\n" + "\r\n" + 
						"	public void deleteEntity(@MODEL@ entity) {\r\n" + 
						"		super.delete(entity);\r\n" + 
						"	}\r\n" + "\r\n" + 
						"	public List<@MODEL@> findAllEntity() {\r\n"+ 
						"		return super.findAll();\r\n" + 
						"	}\r\n" + "\r\n" + 
						"	public @MODEL@ findEntityById(Long id) {\r\n" + 
						"		return super.findById(id);\r\n" + 
						"	}\r\n" + "\r\n" + 
						"	public void persistEntity(List<@MODEL@> entities) {\r\n" + 
						"		super.persistAll(entities);\r\n" + 
						"	}\r\n" + "\r\n" + 
						"	public void mergeEntity(List<@MODEL@> entities) {\r\n" + 
						"		super.mergeAll(entities);\r\n" + 
						"	}\r\n" + "\r\n" + 
						"	public void deleteEntity(List<@MODEL@> entities) {\r\n" + 
						"		super.deleteAll(entities);\r\n" + 
						"	}\r\n" + "\r\n" + 
						"}";

	public static final String TEMP_SERVICE_IMPL = "package com.proinsalud.sistemas.core.@SCHEMA@.service.impl;\r\n" + "\r\n" + 
			"import java.io.Serializable;\r\n" + "import java.util.List;\r\n" + "\r\n" + 
			"import org.springframework.beans.factory.annotation.Autowired;\r\n"+ 			
			"import org.springframework.beans.factory.annotation.Qualifier;\r\n" + 
			"import org.springframework.stereotype.Repository;\r\n" + 
			"import org.springframework.transaction.annotation.Transactional;\r\n" + "\r\n" + 
			"import com.proinsalud.sistemas.core.@SCHEMA@.dao.I@MODEL@Dao;\r\n"+
			"import com.proinsalud.sistemas.core.@SCHEMA@.model.@MODEL@;\r\n" + 
			"import com.proinsalud.sistemas.core.@SCHEMA@.service.I@MODEL@Service;\r\n" + "\r\n" + 
			"@Repository(value = \"@REPOSITORY@Service\")\r\n" + 
			"public class @MODEL@Service implements I@MODEL@Service, Serializable {\r\n"+ "\r\n" + 
			"	@Autowired(required = true)\r\n" + 
			"	@Qualifier(value = \"@REPOSITORY@Dao\")\r\n" + 
			"	private I@MODEL@Dao i@MODEL@Dao;\r\n" + "\r\n" + 
			"	@Transactional\r\n" + 
			"	public @MODEL@ persistEntity(@MODEL@ entity) {\r\n"+ 
			"		return i@MODEL@Dao.persistEntity(entity);\r\n" + 
			"	}\r\n" + "\r\n" + 
			"	@Transactional\r\n" + 
			"	public @MODEL@ mergeEntity(@MODEL@ entity) {\r\n" + 
			"		return i@MODEL@Dao.mergeEntity(entity);\r\n" + 
			"	}\r\n" + "\r\n" + 
			"	@Transactional\r\n"+
			"	public void deleteEntity(@MODEL@ entity) {\r\n" + 
			"		i@MODEL@Dao.deleteEntity(entity);\r\n" + 
			"	}\r\n" + "\r\n" + 
			"	@Transactional\r\n" + 
			"	public List<@MODEL@> findAllEntity() {\r\n" + 
			"		return i@MODEL@Dao.findAllEntity();\r\n" + 
			"	}\r\n" + "\r\n" + 
			"	@Transactional\r\n"+ 
			"	public @MODEL@ findEntityById(Long id) {\r\n" + 
			"		return i@MODEL@Dao.findEntityById(id);\r\n" + 
			"	}\r\n" + "\r\n" + 
			"	@Transactional\r\n"+ 
			"	public void persistEntity(List<@MODEL@> entities) {\r\n" + 
			"		i@MODEL@Dao.persistEntity(entities);\r\n" + 
			"	}\r\n" + "\r\n" + 
			"	@Transactional\r\n"+ 
			"	public void mergeEntity(List<@MODEL@> entities) {\r\n" + 
			"		i@MODEL@Dao.mergeEntity(entities);\r\n" + 
			"	}\r\n" + "\r\n" + 
			"	@Transactional\r\n"+ 
			"	public void deleteEntity(List<@MODEL@> entities) {\r\n" + 
			"		i@MODEL@Dao.deleteEntity(entities);\r\n" + 
			"	}\r\n" + "\r\n" + 
			"}\r\n";

	public GeneratorFiles() {
		System.out.println("#######_______BIENVENIDO AL GENERADOR DE ARCHIVOS_______########");
	}

	public void generate() {
		String nSchema = "";
		while (true) {
			if (nSchema == null) {
				return;
			}
			if (!nSchema.isEmpty()) {
				if (existSchema(nSchema)) {
					break;
				}
				JOptionPane.showMessageDialog(null, "EL ESQUEMA '" + BASE_PACKAGE + l + nSchema + "' NO EXISTE !! ", TITLE_MSG, JOptionPane.ERROR_MESSAGE);
			}
			nSchema = JOptionPane.showInputDialog(null, "DIGITE EL NOMBRE DEL ESQUEMA", TITLE_MSG, JOptionPane.INFORMATION_MESSAGE);
		}

		String nModel = "";
		while (true) {
			if (nModel == null) {
				return;
			}
			if (!nModel.isEmpty()) {
				if (existModel(nSchema, nModel)) {
					break;
				}
				JOptionPane.showMessageDialog(null, "EL MODELO '" + BASE_PACKAGE + l + nSchema + l + FOLDER_MODEL + l + nModel + EXT + "' NO EXISTE !!", TITLE_MSG, JOptionPane.ERROR_MESSAGE);
			}
			nModel = JOptionPane.showInputDialog(null, "DIGITE EL NOMBRE DEL MODELO", TITLE_MSG, JOptionPane.INFORMATION_MESSAGE);
		}

		try {

			List<String> paths = new ArrayList<>();
			paths.add(generateFile(nSchema, nModel, DAO));
			paths.add(generateFile(nSchema, nModel, DAO_IMPL));
			paths.add(generateFile(nSchema, nModel, SERVICE));
			paths.add(generateFile(nSchema, nModel, SERVICE_IMPL));

			String msg = "ESQUEMA: " + nSchema + "\t MODELO: " + nModel + S + "ARCHIVOS GENERADOS (" + paths.size() + ")" + S + S;
			for (String path : paths) {
				msg += path + S;
			}
			JOptionPane.showMessageDialog(null, msg);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "OCURRIO UN ERROR" + S + S + e.getMessage(), TITLE_MSG, JOptionPane.ERROR_MESSAGE);
		}
	}

	public boolean existSchema(String nameSchema) {
		try {
			File schema = new File(DIR + l + nameSchema);
			return schema.exists() && schema.isDirectory();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean existModel(String nameSchema, String nameModel) {
		try {
			File model = new File(DIR + l + nameSchema + l + FOLDER_MODEL + l + nameModel + EXT);
			return model.exists() && model.isFile();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String generateFile(String nameSchema, String nameModel, String typeFile) throws Exception {
		FileWriter fichero = null;
		PrintWriter pw = null;
		File file = null;
		String template = null;
		String nameFile = null;
		try {

			switch (typeFile) {
			case DAO:
				nameFile = buildNameFile(nameSchema, nameModel, typeFile);
				file = new File(nameFile);
				template = TEMP_DAO_SERVICE.replace(REPLACE_MODEL, nameModel).replace(REPLACE_SCHEMA, nameSchema).replace(REPLACE_FOLDER, FOLDER_DAO).replace(REPLACE_PHASE, DAO);
				break;

			case DAO_IMPL:
				nameFile = buildNameFile(nameSchema, nameModel, typeFile);
				file = new File(nameFile);
				template = TEMP_DAO_IMPL.replace(REPLACE_MODEL, nameModel).replace(REPLACE_SCHEMA, nameSchema).replace(REPLACE_REPOSITORY, getNameForRepository(nameModel));
				break;

			case SERVICE:
				nameFile = buildNameFile(nameSchema, nameModel, typeFile);
				file = new File(nameFile);
				template = TEMP_DAO_SERVICE.replace(REPLACE_MODEL, nameModel).replace(REPLACE_SCHEMA, nameSchema).replace(REPLACE_FOLDER, FOLDER_SERVICE).replace(REPLACE_PHASE, SERVICE);
				break;

			case SERVICE_IMPL:
				nameFile = buildNameFile(nameSchema, nameModel, typeFile);
				file = new File(nameFile);
				template = TEMP_SERVICE_IMPL.replace(REPLACE_MODEL, nameModel).replace(REPLACE_SCHEMA, nameSchema).replace(REPLACE_REPOSITORY, getNameForRepository(nameModel));
				break;

			default:
				break;
			}
			fichero = new FileWriter(file);
			pw = new PrintWriter(fichero);
			pw.println(template);
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			try {
				if (null != fichero) {
					pw.close();
					fichero.close();
				}
			} catch (Exception e2) {
				throw new Exception(e2);
			}
		}
		System.out.println("### ARCHIVO GENERADO " + file.getAbsolutePath());
		return file.getAbsolutePath();
	}

	public String buildNameFile(String nameSchema, String nameModel, String typeFile) {
		switch (typeFile) {
		case DAO:
			return DIR + l + nameSchema + l + FOLDER_DAO + l + I + nameModel + DAO + EXT;
		case DAO_IMPL:
			return DIR + l + nameSchema + l + FOLDER_DAO + l + FOLDER_IMPL + l + nameModel + DAO + EXT;
		case SERVICE:
			return DIR + l + nameSchema + l + FOLDER_SERVICE + l + I + nameModel + SERVICE + EXT;
		case SERVICE_IMPL:
			return DIR + l + nameSchema + l + FOLDER_SERVICE + l + FOLDER_IMPL + l + nameModel + SERVICE + EXT;
		default:
			break;
		}
		return null;
	}

	public String getNameForRepository(String nameModel) {
		return nameModel.substring(0, 1).toLowerCase() + nameModel.substring(1);
	}

	public static void main(String[] args) {
		GeneratorFiles gf = new GeneratorFiles();
		gf.generate();
	}
}
