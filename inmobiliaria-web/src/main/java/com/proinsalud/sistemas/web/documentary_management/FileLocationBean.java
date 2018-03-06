package com.proinsalud.sistemas.web.documentary_management;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;

import com.proinsalud.sistemas.core.documentary_management.model.FileLocation;
import com.proinsalud.sistemas.core.documentary_management.service.IFileLocationService;
import com.proinsalud.sistemas.web.util.App;
import com.proinsalud.sistemas.web.util.Messages;
import com.proinsalud.sistemas.web.util.UtilWeb;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 31/01/2018 - 10:06:54 a. m.
 */
@ManagedBean
@ViewScoped
public class FileLocationBean implements Serializable {

	private static final long serialVersionUID = 2824740368705060003L;
	public static final Log LOG = App.getLogger(FileLocationBean.class);

	@Autowired
	private IFileLocationService iFileLocationService;

	private TreeNode root;
	private TreeNode select;
	private FileLocation fileLocation;
	private List<FileLocation> fileLocations;

	public FileLocationBean() {
		super();
	}

	@PostConstruct
	public void init() {
		App.initInjectionAutowired(this);
		initAttributes();
		loadRootTree();
	}

	public void initAttributes() {
		select = null;
		fileLocation = new FileLocation();
	}

	public void loadFileLocationRoot() {
		fileLocations = iFileLocationService.findFileLocationRoot();
	}

	public void loadRootTree() {
		loadFileLocationRoot();
		root = new DefaultTreeNode("Root", null);
		UtilWeb.loadRootTree(root, fileLocations, "fileLocation", "getFileLocations");
	}

	public void loadSelect() {
		try {
			fileLocation = new FileLocation();
			if (select != null) {
				if (select.getData() instanceof FileLocation) {
					fileLocation.setFileLocactionFather((FileLocation) select.getData());
				}
			}
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
		}
	}

	public void cancel() {
		initAttributes();
		UtilWeb.setRoot(root, false, false);
	}

	public void saveFileLocation() {
		try {
			if (fileLocation.getId()==null) {
				fileLocation = iFileLocationService.persistEntity(fileLocation);
			}
			Long idFileLocation = fileLocation.getId();
			loadRootTree();
			initAttributes();
			select = UtilWeb.findItemRoot(root, idFileLocation, true, true);
			loadSelect();
			Messages.showMsg(Messages.MSG_INFO, "form1", null);
		} catch (Exception e) {
			UtilWeb.printError(LOG, e);
			Messages.showMsg(Messages.MSG_ERROR, "form1", null, e);
		}
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelect() {
		return select;
	}

	public void setSelect(TreeNode select) {
		this.select = select;
	}

	public FileLocation getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(FileLocation fileLocation) {
		this.fileLocation = fileLocation;
	}

	public List<FileLocation> getFileLocations() {
		return fileLocations;
	}

	public void setFileLocations(List<FileLocation> fileLocations) {
		this.fileLocations = fileLocations;
	}
	
	

}
