package com.proinsalud.sistemas.web.util;

import java.io.IOException;
import java.util.Properties;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Messages {

	public static final int MSG_INFO = 1;
	public static final int MSG_WARNING = 2;
	public static final int MSG_ERROR = 3;

	public static final String ID_MSG_GROWL = "growl1";

	// CONFIGURATION MESSAGES
	private Properties properties = null;

	private Messages() {
		this.properties = new Properties();
		try {
			properties.load(Messages.class.getClassLoader().getResourceAsStream("msgapplication.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private static Messages getInstance() {
		return ConfigurationHolder.INSTANCE;
	}

	public static String getProperty(String key) {
		return getInstance().properties.getProperty(key);
	}

	private static class ConfigurationHolder {
		private static final Messages INSTANCE = new Messages();
	}

	/**
	 * Metodo encargado de mostrar un mensaje en la vista actual
	 * 
	 * @author Andres Santacruz
	 * @datetime 25/10/2017 - 11:53:07 a. m.
	 *
	 * @param typeMessage
	 *            es el tipo de mensaje, puede ser TYPE_MESSAGE_INFO = 1 ,
	 *            TYPE_MESSAGE_WARNING = 2 , TYPE_MESSAGE_ERROR = 3;
	 * @param form
	 *            es el id del formulario, si no tiene formulario debe ir null
	 * @param msg
	 *            es el mensaje que se mostrara
	 * @return
	 */
	public static void showMsg(int typeMessage, String form, String msg) {
		if (msg == null) {
			msg = getMsgActions(typeMessage);
		}
		FacesMessage fM = null;
		switch (typeMessage) {
		case MSG_INFO:
			fM = new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.getProperty(Messages.msgInfoTitle), msg);
			break;
		case MSG_WARNING:
			fM = new FacesMessage(FacesMessage.SEVERITY_WARN, Messages.getProperty(Messages.msgWarningTitle), msg);
			break;
		case MSG_ERROR:
			fM = new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getProperty(Messages.msgErrorTitle), msg);
			break;

		default:
			break;
		}
		if (fM != null) {
			FacesContext.getCurrentInstance().addMessage(form, fM);
		}
	}

	public static void showMsg(int typeMessage, String form, String msg, Exception e) {
		if (msg == null) {
			msg = getMsgActions(typeMessage);
		}
		showMsg(typeMessage, form, msg);
		if (typeMessage == MSG_ERROR) {
			if (e != null) {
				msg = e.getMessage();
				Throwable t = e.getCause();
				if (t != null) {
					while (t.getCause() != null) {
						t = t.getCause();
					}
					msg = t.getMessage();
				}
				msg = msg == null ? "NullPointerException" : msg;
				showMsg(typeMessage, form, msg);
			}
		}
	}

	private static String getMsgActions(int typeMessage) {
		String msg = "...";
		switch (typeMessage) {
		case MSG_INFO:
			msg = Messages.getProperty(Messages.msgSuccessful);
			break;
		case MSG_ERROR:
			msg = Messages.getProperty(Messages.msgErrorOcurred);
			break;

		default:
			break;
		}
		return msg;
	}

	// MENSSAGES
	public static final String msgWelcome = "msgWelcome";
	public static final String msgErrorSessionLogin = "msgErrorSessionLogin";
	public static final String msgError400 = "msgError400";
	public static final String msgError400Img = "msgError400Img";
	public static final String msgError401 = "msgError401";
	public static final String msgError401Img = "msgError401Img";
	public static final String msgError403 = "msgError403";
	public static final String msgError403Img = "msgError403Img";
	public static final String msgError404 = "msgError404";
	public static final String msgError404Img = "msgError404Img";
	public static final String msgError500 = "msgError500";
	public static final String msgError500Img = "msgError500Img";

	public static final String msgErrorTitle = "msgErrorTitle";
	public static final String msgInfoTitle = "msgInfoTitle";
	public static final String msgWarningTitle = "msgWarningTitle";

	public static final String msgSuccessful = "msgSuccessful";
	public static final String msgErrorOcurred = "msgErrorOcurred";

	public static final String msgErrorLoadData = "msgErrorLoadData";
	public static final String msgLogout = "msgLogout";
	public static final String msgLogin = "msgLogin";
	public static final String msgLogoutTimeout = "msgLogoutTimeout";
	
	public static final String msgFavSaveSuccessful = "msgFavSaveSuccessful";
	public static final String msgFavDelSuccessful = "msgFavDelSuccessful";
	public static final String msgFavError = "msgFavError";
	
	public static final String msgConvocatoriaEliminada = "msgConvocatoriaEliminada";
	public static final String msgConvocatoriaPublicada = "msgConvocatoriaPublicada";

}
