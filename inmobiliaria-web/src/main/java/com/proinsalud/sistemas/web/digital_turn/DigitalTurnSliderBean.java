package com.proinsalud.sistemas.web.digital_turn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.digital_turn.model.Slider;
import com.proinsalud.sistemas.core.digital_turn.service.ISliderService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 22/02/2018 - 3:15:29 p. m.
 */
@ManagedBean
@ViewScoped
public class DigitalTurnSliderBean implements Serializable {

	private static final long serialVersionUID = -924733383539179402L;
	private static final Log LOG = App.getLogger(DigitalTurnSliderBean.class);

	@Autowired
	private ISliderService iSliderService;

	
	private UploadedFile uploadFile;
	private InputStream inputStream;
	private List<Slider> sliders;
	private Slider slider;
	private String destination = "H:\\proinsaludv3\\proinsaludApp\\proinsalud-web\\src\\main\\webapp\\resources\\assets\\img\\";
	private boolean showForm;
	private boolean crearDiapositiva;
	private boolean updateDiapositiva;
	public DigitalTurnSliderBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		slider = new Slider();
		sliders = iSliderService.findAllEntity();
		

	}

	public void loadDiapositivaSlider(Slider diapositiva) {
		try {
			updateDiapositiva = true;
			showForm=true;
			slider = iSliderService.findEntityById(diapositiva.getId());
		} catch (Exception e) {

			UtilWeb.printError(LOG, e);
		}
	}

	public void createSlider() {
		try {
			uploadIcon();
			if(slider.getUrlImage() !=null) {
				slider=iSliderService.persistEntity(slider);
			}	
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
		cancel();
	}

	public void updateDiapositiva() {
		try {
			uploadIcon();
			if (slider != null) {
				if (slider.getId() != null) {
					slider = iSliderService.mergeEntity(slider);
					uploadFile = null;
				}
				cancel();
				init();
			}

		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}
	
	public void formSlider() {
		crearDiapositiva = true;
		showForm=true;
	}


	public void uploadIcon() throws Exception {
		try {
			if (uploadFile != null && !uploadFile.getFileName().isEmpty()) {
				slider.setUrlImage(uploadFile.getFileName());
				copyFile(uploadFile.getFileName(), uploadFile.getInputstream());
			}
		} catch (IOException e) {
			UtilWeb.printError(LOG, e);
			throw new Exception(e);
		}
	}

	public void uploadFile(FileUploadEvent event) {
		uploadFile = event.getFile();
		try {
			inputStream = uploadFile.getInputstream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void copyFile(String fileName, InputStream in) {
		try {
			OutputStream out = new FileOutputStream(new File(destination + fileName));
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

	public void cancel() {
		init();
		showForm = false;
		updateDiapositiva=false;
		crearDiapositiva=false;
		
	}

	public List<Slider> getSliders() {
		return sliders;
	}

	public Slider getSlider() {
		return slider;
	}

	public void setSlider(Slider slider) {
		this.slider = slider;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public UploadedFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(UploadedFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public boolean isCrearDiapositiva() {
		return crearDiapositiva;
	}

	public boolean isUpdateDiapositiva() {
		return updateDiapositiva;
	}
	
	

}
