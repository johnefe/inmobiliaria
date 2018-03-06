package com.proinsalud.sistemas.web.application.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.general.model.OptionFavorite;
import com.proinsalud.sistemas.core.general.service.IOptionFavoriteService;
import com.proinsalud.sistemas.core.security.model.Option;
import com.proinsalud.sistemas.core.security.model.OptionAction;
import com.proinsalud.sistemas.core.security.model.Parameter;
import com.proinsalud.sistemas.core.security.model.UserOption;
import com.proinsalud.sistemas.core.security.model.Users;
import com.proinsalud.sistemas.core.security.service.IUserOptionService;
import com.proinsalud.sistemas.web.util.ActionPermits;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * Es el bean principal de la aplicacion
 * 
 * @author Andres Santacruz
 * @datetime 3/01/2018 - 8:10:41 a. m.
 *
 */
@ManagedBean
@ViewScoped
public class ProinsaludBean implements Serializable {

	private static final long serialVersionUID = 2668776902394312039L;
	private static final Log LOG = App.getLogger(ProinsaludBean.class);

	@Autowired
	private IOptionFavoriteService iOptionFavoriteService;
	@Autowired
	private IUserOptionService iUserOptionService;
	@Autowired
	private SessionBean sessionBean;

	private List<Option> options;
	private Users user;

	private boolean isOptionFavorite;
	private OptionFavorite optionFavorite;

	private Date now;

	/********** _ACCIONES_ **********/
	private boolean accionCrear;
	private boolean accionEliminar;
	private boolean accionEditar;
	private boolean accionBuscar;
	private boolean accionHeredar;
	private boolean accionCerrarConvocatoria;
	private boolean accionListar;
	private boolean accionPublicarConvocatoria;

	/******************************/

	public ProinsaludBean() {
		super();
	}

	@PostConstruct
	public void init() {
		try {
			App.initInjectionAutowired(this);
			if (App.isAuthenticated()) {
				user = App.getUser();
				options = App.getOptionsUser();
				loadActions();
				isOptionCurrentFavorite();
				initDate();
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	@SuppressWarnings("deprecation")
	public void initDate() {
		now = new Date();
		now.setHours(0);
		now.setMinutes(0);
		now.setSeconds(0);
	}

	/******** VERIFICAR PERMISOS A ACCIONES *********/

	/**
	 * Verfica que la accion exista en el listado de acciones
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 4:30:21 p. m.
	 *
	 * @param codeAction
	 * @param optionActions
	 * @return
	 * @throws Exception
	 */
	private boolean hasPermission(int codeAction, List<OptionAction> optionActions) throws Exception {
		if (optionActions != null && !optionActions.isEmpty()) {
			for (OptionAction oa : optionActions) {
				if (oa.getAction().getId() == codeAction) {
					optionActions.remove(oa);
					LOG.info("Codigo: " + codeAction + " TRUE");
					return true;
				}
			}
		} else {
			throw new Exception("Lista de acciones vacia");
		}
		LOG.info("Codigo: " + codeAction + " FALSE");
		return false;
	}

	/**
	 * Carga las acciones que tiene la opcion actual
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 4:30:06 p. m.
	 *
	 */
	private void loadActions() {
		try {
			
			Option option = sessionBean.getOptionSelectedApp();
			if (option != null) {
				LOG.info("Opcion: " + option.getName() + " , # Acciones: " + option.getOptionActions().size());
				List<OptionAction> optionActions = new ArrayList<OptionAction>(option.getOptionActions());
				if (!optionActions.isEmpty()) {
					LOG.info(ActionPermits.getStringActions());
					accionCrear = hasPermission(ActionPermits.CREAR, optionActions);
					accionEditar = hasPermission(ActionPermits.EDITAR, optionActions);
					accionEliminar = hasPermission(ActionPermits.ELIMINAR, optionActions);
					accionBuscar = hasPermission(ActionPermits.BUSCAR, optionActions);
					accionHeredar = hasPermission(ActionPermits.HEREDAR, optionActions);
					accionCerrarConvocatoria = hasPermission(ActionPermits.CERRAR_CONVOCATORIA, optionActions);
					accionListar = hasPermission(ActionPermits.LISTAR, optionActions);
					accionPublicarConvocatoria = hasPermission(ActionPermits.PUBLICAR_CONVOCATORIA, optionActions);
				}
			}
		} catch (Exception e) {
			LOG.info(e);
		}
	}

	/****************************************************/

	/****************** OPCION FAVORITA *******************/
	/**
	 * Verifica si la opcion actual esta en la lista de favoritas
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 4:31:33 p. m.
	 *
	 */
	private void isOptionCurrentFavorite() {
		isOptionFavorite = false;
		Option optionSelected = sessionBean.getOptionSelectedApp();
		if (optionSelected == null) {
			return;
		}
		List<OptionFavorite> listUserOption = sessionBean.getOptionFavoriteList();
		for (int i = 0; i < listUserOption.size(); i++) {
			Long idOptionGet = listUserOption.get(i).getUserOption().getOption().getId();
			if (optionSelected.getId() == idOptionGet) {
				isOptionFavorite = true;
				return;
			}
		}
	}

	/**
	 * Guarda una opcion favorita del usuario
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 4:32:05 p. m.
	 *
	 */
	public void saveFavorite() {
		try {
			isOptionFavorite = false;
			boolean encontrado = false;
			Option optionSelected = sessionBean.getOptionSelectedApp();
			List<OptionFavorite> listUserOption = sessionBean.getOptionFavoriteList();
			UserOption userOption = iUserOptionService.findEntityByOptionUser(optionSelected.getId(), App.getUser().getId());
			if (userOption != null) {
				for (int i = 0; i < listUserOption.size() && !encontrado; i++) {
					OptionFavorite optionGet = listUserOption.get(i);
					Long idOptionGet = optionGet.getUserOption().getOption().getId();
					if (optionSelected.getId().equals(idOptionGet)) {
						encontrado = true;
						optionFavorite = optionGet;
					}
				}
				if (encontrado == false) {
					Parameter p = sessionBean.getParameter();
					if (listUserOption.size() < p.getNumFavorites()) {
						optionFavorite = new OptionFavorite();
						optionFavorite.setUserOption(userOption);
						optionFavorite = iOptionFavoriteService.persistEntity(optionFavorite);
						isOptionFavorite = true;
						sessionBean.loadOptionFavorite();
						Messages.showMsg(Messages.MSG_INFO, Messages.ID_MSG_GROWL, Messages.getProperty(Messages.msgFavSaveSuccessful));
					} else {
						Messages.showMsg(Messages.MSG_WARNING, Messages.ID_MSG_GROWL, Messages.getProperty(Messages.msgFavError));
					}
				} else {
					iOptionFavoriteService.deleteEntity(optionFavorite);
					sessionBean.loadOptionFavorite();
					Messages.showMsg(Messages.MSG_INFO, Messages.ID_MSG_GROWL, Messages.getProperty(Messages.msgFavDelSuccessful));
				}
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "formHeader", null, e);
		}
	}

	/**
	 * Elimina la opcion favorita
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 4:32:23 p. m.
	 *
	 * @param optionFav
	 */
	public void deleteFavorite(OptionFavorite optionFav) {
		try {
			iOptionFavoriteService.deleteEntity(optionFav);
			sessionBean.loadOptionFavorite();
			isOptionFavorite = false;
			Messages.showMsg(Messages.MSG_INFO, Messages.ID_MSG_GROWL, Messages.getProperty(Messages.msgFavDelSuccessful));
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, Messages.ID_MSG_GROWL, Messages.getProperty(Messages.msgFavError));
		}
	}

	/**
	 * Retorna la url completa de la opcion pasada como parametro
	 * 
	 * @author Andres Santacruz
	 * @datetime 3/01/2018 - 4:32:38 p. m.
	 *
	 * @param option
	 * @return
	 */
	public String getCompleteUrl(Option option) {
		return UtilWeb.getUrlOption(option);
	}

	/****************************************************/

	/****************** METODOS SESSIONBEAN *******************/

	public List<OptionFavorite> getOptionFavoriteList() {
		return sessionBean.getOptionFavoriteList();
	}

	public Option getOptionSelectedApp() {
		return sessionBean.getOptionSelectedApp();
	}

	public String getNameImg() {
		return sessionBean.getParameter().getIconApp();
	}

	/****************************************************/

	/****************** GETTERS Y SETTERS DEL BEAN *******************/

	public Users getUser() {
		return user;
	}

	public boolean isOptionFavorite() {
		return isOptionFavorite;
	}

	public List<Option> getOptions() {
		return options;
	}

	public Date getNow() {
		return now;
	}

	/****************************************************/

	/****************** SOLO GETTERS PARA LAS ACCIONES *******************/
	public boolean isAccionCrear() {
		return accionCrear;
	}

	public boolean isAccionEliminar() {
		return accionEliminar;
	}

	public boolean isAccionEditar() {
		return accionEditar;
	}

	public boolean isAccionBuscar() {
		return accionBuscar;
	}

	public boolean isAccionHeredar() {
		return accionHeredar;
	}

	public boolean isAccionCerrarConvocatoria() {
		return accionCerrarConvocatoria;
	}

	public boolean isAccionPublicarConvocatoria() {
		return accionPublicarConvocatoria;
	}

	public boolean isAccionListar() {
		return accionListar;
	}

	/****************************************************/

}
