package com.proinsalud.sistemas.web.util;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.proinsalud.sistemas.core.documentary_management.model.Trd;
import com.proinsalud.sistemas.core.human_talent.model.Area;
import com.proinsalud.sistemas.core.security.model.Option;
import com.proinsalud.sistemas.core.security.model.Users;

/**
 * @author Andres Santacruz
 * @datetime 3/01/2018 - 8:48:04 a. m.
 *
 */
public class UtilWeb implements Serializable {

	private static final long serialVersionUID = 9171005383443903294L;
	private static final Log LOG = App.getLogger(UtilWeb.class);

	public class Constants {
		public static final String PATH_LOGO_PROINSALUD = "/resources/assets/img/logo_proinsalud.png";
	}
	
	public class Constants2 {
		public static final String PATH_SLIDER_PROINSALUD = "/resources/assets/img/";
	}

	/**
	 * Retorna la url de la opcion
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 8:49:20 a. m.
	 *
	 * @param option
	 * @param url
	 * @return
	 */
	public static String getUrlOption(Option option) {
		return getUrlOption(option, "");
	}

	private static String getUrlOption(Option option, String url) {
		if (option != null) {
			url = option.getDetail() + url;
			url = getUrlOption(option.getOptionFather(), "/" + url);
		}
		return url;
	}

	/**
	 * Crea el arbol segun la lista que es pasada por parametro
	 * 
	 * @author Andres Santacruz
	 * @datetime 26/01/2018 - 9:44:51 a. m.
	 *
	 * @param root
	 *            es el arbol
	 * @param lst
	 *            es la lista de objetos
	 * @param type
	 *            es el tipo que va en '<p:treeNode' en la vista
	 * @param nameMethodGetChilds
	 *            es el nombre del metodo que trae la lista de hijos, ejemplo
	 *            'getOptions'
	 */
	@SuppressWarnings("unchecked")
	public static void loadRootTree(TreeNode root, List<? extends Object> lst, String type, String nameMethodGetChilds) {
		try {
			for (Object o : lst) {
				TreeNode child = new DefaultTreeNode(type, o, root);
				root.getChildren().add(child);
				Method method = o.getClass().getMethod(nameMethodGetChilds);
				List<? extends Object> childs = (List<? extends Object>) method.invoke(o);
				if (!childs.isEmpty()) {
					loadRootTree(child, childs, type, nameMethodGetChilds);
				}
			}
		} catch (Exception e) {
			printError(LOG, e);
		}
	}

	/**
	 * Arma un arbol de areas con items que recibe por parametro
	 * 
	 * @author Andres Santacruz
	 * @datetime 25/01/2018 - 8:02:43 a. m.
	 *
	 * @param root
	 * @param areas
	 */
	public static void loadAreas(TreeNode root, List<Area> areas) {
		for (Area a : areas) {
			TreeNode child = new DefaultTreeNode("area", a, root);
			root.getChildren().add(child);
			List<Area> lstAreas = a.getAreas();
			List<Trd> lstTrds = a.getTrds();
			if (!lstAreas.isEmpty()) {
				loadAreas(child, lstAreas);
			} else if (!lstTrds.isEmpty()) {
				loadRootTree(child, lstTrds, "trd", "getTrds");
			}
		}
	}

	/**
	 * Se encarga de expandir o seleccionar cada item del arbol según los parametros
	 * 
	 * @author Andres Santacruz
	 * @datetime 26/01/2018 - 9:52:40 a. m.
	 *
	 * @param root
	 * @param expandible
	 * @param selected
	 */
	public static void setRoot(TreeNode root, boolean expandible, boolean selected) {
		for (TreeNode tn : root.getChildren()) {
			tn.setExpanded(expandible);
			tn.setSelected(selected);
			setRoot(tn, expandible, selected);
		}
	}

	/**
	 * Encuentra el item con el id pasado por parametro
	 * 
	 * @author Andres Santacruz
	 * @datetime 26/01/2018 - 10:10:24 a. m.
	 *
	 * @param root
	 * @param id
	 *            es el id de la entidad
	 * @param expandirArbol
	 * @param seleccionarBuscado
	 * @return retorna el item encontrado de lo contrario null
	 */
	public static TreeNode findItemRoot(TreeNode root, Long id, boolean expandirArbol, boolean seleccionarBuscado) {
		try {
			for (TreeNode tn : root.getChildren()) {
				Object o = tn.getData();
				Method method = o.getClass().getMethod("getId");
				Long idO = (Long) method.invoke(o);
				if (idO.equals(id)) {
					tn.setSelected(seleccionarBuscado);
					tn.setExpanded(expandirArbol);
					return tn;
				}
				if (tn.getChildren() != null && !tn.getChildren().isEmpty()) {
					TreeNode itemFind = findItemRoot(tn, id, expandirArbol, seleccionarBuscado);
					if (itemFind != null) {
						tn.setExpanded(expandirArbol);
						return itemFind;
					}
				}
			}
		} catch (Exception e) {
			printError(LOG, e);
		}
		return null;
	}

	/**
	 * Este metodo retorna el objeto que se desea comparar de la lista, lo valida
	 * con el id del objeto
	 * 
	 * @precondicion el objeto debe tener el metodo getId()
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 8:39:56 a. m.
	 *
	 * @param lst
	 * @param objectCompare
	 * @return
	 * @throws Exception
	 */
	public static Object loadObject(List<? extends Object> lst, Object objectCompare) throws Exception {
		try {
			if (objectCompare != null && lst != null) {
				String nameMethodGet = "getId";
				Method method = objectCompare.getClass().getMethod(nameMethodGet);
				Long id1 = (Long) method.invoke(objectCompare);
				for (Object obj : lst) {
					Method method2 = obj.getClass().getMethod(nameMethodGet);
					Long id2 = (Long) method2.invoke(obj);
					if (id1.equals(id2)) {
						return obj;
					}
				}
			}
			return objectCompare;
		} catch (Exception e) {
			printError(LOG, e);
			throw new Exception("Error comparando los objetos " + e.getMessage());
		}
	}

	/**
	 * Retorna las opciones de un usuario en concreto
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 9:08:22 a. m.
	 *
	 * @param user
	 * @return
	 */
	public static List<Option> getOptionsUser(Users user) {
		List<Option> options = new ArrayList<>();
		if (user != null) {
			user.getUserOptions().forEach(uo -> options.add(uo.getOption()));
			// for (UserOption userOption : user.getUserOptions()) {
			// options.add(userOption.getOption());
			// }
		}
		return options;
	}

	/**
	 * Retorna la lista de opciones sin jerarquia
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 9:11:10 a. m.
	 *
	 * @param options
	 * @return
	 */
	public static List<Option> getOptionsUnorder(List<Option> options) {
		List<Option> optionsUnorder = new ArrayList<>();
		for (Option option : options) {
			optionsUnorder.add(option);
			if (!option.getOptions().isEmpty()) {
				optionsUnorder.addAll(getOptionsUnorder(option.getOptions()));
			}
		}
		return optionsUnorder;
	}

	public static Option searchOption(List<Option> options, int idModule, Option response) {
		for (Option option : options) {
			if (option.getId() == idModule) {
				return option;
			}
			response = searchOption(option.getOptions(), idModule, response);
			if (response != null) {
				return response;
			}
		}
		return null;
	}

	public static Option searchOptionModule(String appName, String moduleName, List<Option> options, Option response) {
		for (Option option : options) {
			if (option.getDetail().equals(appName)) {
				appName = null;
				response = searchOptionModule(appName, moduleName, option.getOptions(), response);
				if (response != null) {
					return response;
				}
			} else if (option.getDetail().equals(moduleName)) {
				response = option;
				if (response != null) {
					return response;
				}
			}
		}

		return null;
	}

	/**
	 * Este metodo debe ser usado para mostrar las excepciones
	 * 
	 * @author Andres Santacruz
	 * @datetime 11/01/2018 - 9:41:04 a. m.
	 *
	 * @param LOG
	 * @param e
	 */
	public static void printError(Log LOG, Exception e) {
		if (e == null)
			UtilWeb.LOG.error("Exception IS NULL");

		if (LOG == null)
			UtilWeb.LOG.error("LOG IS NULL");

		if (LOG != null)
			LOG.error(e);

		if (e != null)
			e.printStackTrace();
	}

	/**
	 * Actualiza la tabla pasandole el id ejemplo form1:tblItemsTrd
	 * 
	 * @author Andres Santacruz
	 * @datetime 22/01/2018 - 3:22:42 p. m.
	 *
	 * @param idComponent
	 */
	public static void uDataTable(String idComponent) {
		DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(idComponent);
		if (dataTable != null) {
			dataTable.reset();
		}
	}

	public static String getPathBase(String path) {
		return FacesContext.getCurrentInstance().getExternalContext().getRealPath(path != null ? path : "");
	}

}
