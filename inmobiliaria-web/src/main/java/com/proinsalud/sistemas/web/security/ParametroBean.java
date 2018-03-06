package com.proinsalud.sistemas.web.security;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.security.model.Parameter;
import com.proinsalud.sistemas.core.security.service.IParameterService;
import com.proinsalud.sistemas.web.application.bean.SessionBean;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Jhon Frey Diaz
 * @datetime 22/12/2017 - 11:01:54 a. m.
 *
 */
@ManagedBean
@ViewScoped
public class ParametroBean implements Serializable {

	private static final long serialVersionUID = -610318305717822592L;
	private static final Log LOG = App.getLogger(ParametroBean.class);

	@Autowired
	private IParameterService iParameterService;

	@Autowired
	private SessionBean sessionBean;

	private Parameter parameter;
	private UploadedFile uploadFile;
	private String destination;
	private boolean showForm;
	private InputStream in;

	public ParametroBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		destination = UtilWeb.getPathBase(UtilWeb.Constants.PATH_LOGO_PROINSALUD);
		loadParameter();
	}

	public void initForm() {
		try {
			loadParameter();
			showForm = true;
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void saveParameter() {
		try {
			uploadIcon();
			if (parameter != null) {
				if (parameter.getId() != null) {
					parameter = iParameterService.mergeEntity(parameter);
					sessionBean.loadParameter();
					uploadFile = null;
				}
				cancel();
			}
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void uploadIcon() throws Exception {
		try {
			if (uploadFile != null && !uploadFile.getFileName().isEmpty()) {
				parameter.setIconApp("logo_proinsalud.png");
				//copyFile(uploadFile.getFileName(), uploadFile.getInputstream());
				//String name = uploadFile.getFileName();
				//InputStream in = uploadFile.getInputstream();
				OutputStream out = new FileOutputStream(new File(destination));
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = in.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				in.close();
				out.flush();
				out.close();		
			}
		} catch (IOException e) {
			UtilWeb.printError(LOG, e);
			throw new Exception(e);
		}
	}

	public void uploadFile(FileUploadEvent event) {
		uploadFile = event.getFile();
		try {
			in = uploadFile.getInputstream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			UtilWeb.printError(LOG, e);
		}
	}

	public void copyFile(String fileName, InputStream in) {
		try {
			OutputStream out = new FileOutputStream(new File(destination));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			in.close();
			out.flush();
			out.close();		
		} catch (IOException e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void loadParameter() {
		try {
			parameter = iParameterService.findAllEntity().get(0);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void cancel() {
		showForm = false;
	}

	public Parameter getParameter() {
		return parameter;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

}
