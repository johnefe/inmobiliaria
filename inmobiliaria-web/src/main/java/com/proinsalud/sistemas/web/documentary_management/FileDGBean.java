package com.proinsalud.sistemas.web.documentary_management;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.documentary_management.model.FileDG;
import com.proinsalud.sistemas.core.documentary_management.model.Trd;
import com.proinsalud.sistemas.core.documentary_management.service.IFileDGService;
import com.proinsalud.sistemas.core.documentary_management.service.ITrdService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 29/01/2018 - 8:34:18 a. m.
 */
@ManagedBean
@ViewScoped
public class FileDGBean implements Serializable {

	private static final long serialVersionUID = 9025354654515162001L;

	private static final Log LOG = App.getLogger(FileDGBean.class);

	@Autowired
	private IFileDGService iFileDGService;

	@Autowired
	private ITrdService iTrdService;

	private Trd trd;
	private FileDG fileDG;
	private UploadedFile uploadFile;
	private InputStream inputStream;

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
	}

	public void initForm() {
		try {

		} catch (Exception e) {
			LOG.error(e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public void uploadFile(FileUploadEvent event) {
		try {
			uploadFile = event.getFile();
			inputStream = uploadFile.getInputstream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveDocument() {
		try {
			Trd trd = iTrdService.findAllEntity().get(0);
			//fileDG = iFileDGService.persistEntity(inputStream, new File(uploadFile.getFileName()).getName(), "rutaFisica", trd);
			fileDG = iFileDGService.persistEntity(inputStream, trd);
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public FileDG getFileDG() {
		return fileDG;
	}

	public void setFileDG(FileDG fileDG) {
		this.fileDG = fileDG;
	}

	public Trd getTrd() {
		return trd;
	}

	public void setTrd(Trd trd) {
		this.trd = trd;
	}

}
