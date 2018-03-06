package com.proinsalud.sistemas.core.documentary_management.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.proinsalud.sistemas.core.documentary_management.dao.IFileDGDao;
import com.proinsalud.sistemas.core.documentary_management.model.FileDG;
import com.proinsalud.sistemas.core.documentary_management.model.Trd;
import com.proinsalud.sistemas.core.documentary_management.service.IFileDGService;

/**
 * @author Ingeniero Jhon Frey Diaz
 * @datetime 29/01/2018 - 10:58:39 a. m.
 */
@Repository(value = "fileDGService")
public class FileDGService implements IFileDGService, Serializable {

	private static final long serialVersionUID = -1216268494639670867L;
	@Autowired(required = true)
	@Qualifier(value = "fileDGDao")
	private IFileDGDao iFileDGDao;

	@Transactional
	public FileDG persistEntity(FileDG entity) {
		return iFileDGDao.persistEntity(entity);
	}

	@Transactional
	public FileDG mergeEntity(FileDG entity) {
		return iFileDGDao.mergeEntity(entity);
	}

	@Transactional
	public void deleteEntity(FileDG entity) {
		iFileDGDao.deleteEntity(entity);
	}

	@Transactional
	public List<FileDG> findAllEntity() {
		return iFileDGDao.findAllEntity();
	}

	@Transactional
	public FileDG findEntityById(Long id) {
		return iFileDGDao.findEntityById(id);
	}

	@Transactional
	public void persistEntity(List<FileDG> entities) {
		iFileDGDao.persistEntity(entities);
	}

	@Transactional
	public void mergeEntity(List<FileDG> entities) {
		iFileDGDao.mergeEntity(entities);
	}

	@Transactional
	public void deleteEntity(List<FileDG> entities) {
		iFileDGDao.deleteEntity(entities);
	}

	@Transactional
	public FileDG persistEntity(InputStream inputStream, Trd trd) throws Exception {
		File file = saveFileFisico(inputStream, null);
		if (file == null) {
			throw new Exception("No se logro almacenar el archivo.");
		}
		return persistEntityFile(file.getName(), file.getAbsolutePath(), "rutaFisica", trd);
	}

	@Transactional
	public FileDG persistEntity(InputStream inputStream, String nameFile, String rutaF, Trd trd) throws Exception {
		if (inputStream != null) {
			File file = saveFileFisico(inputStream, nameFile);
			if (file == null) {
				throw new Exception("No se logro guardar el archivo");
			}
			return persistEntityFile(nameFile, file.getAbsolutePath(), rutaF, trd);

		}
		return null;
	}

	private FileDG persistEntityFile(String nameFile, String rutaD, String rutaF, Trd trd) {
		FileDG fileDG = new FileDG();
		fileDG.setNameFile(nameFile);
		fileDG.setRouteD(rutaD);
		fileDG.setRouteF(rutaF);
		fileDG.setTrd(trd);
		return iFileDGDao.persistEntity(fileDG);
	}

	private File saveFileFisico(InputStream inputStream, String nameFile) {
		try {
			String dir = "C:\\filesApp" + File.separator;
			String path = dir + (nameFile == null ? System.currentTimeMillis() : nameFile) ;
			File newFile = new File(path);
			OutputStream out = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			inputStream.close();
			out.flush();
			out.close();
			return newFile;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
