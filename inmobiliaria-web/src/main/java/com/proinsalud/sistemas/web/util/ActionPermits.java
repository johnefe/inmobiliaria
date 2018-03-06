package com.proinsalud.sistemas.web.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.proinsalud.sistemas.core.security.model.Action;

/**
 * Es la clase de constantes para identificar los permisos de acciones
 * 
 * @author Andres Santacruz
 * @datetime 3/01/2018 - 7:39:47 a. m.
 *
 */
public class ActionPermits {

	public static final int CREAR = 1;
	public static final int EDITAR = 2;
	public static final int ELIMINAR = 3;
	public static final int BUSCAR = 4;
	public static final int HEREDAR = 5;
	public static final int CERRAR_CONVOCATORIA = 6;
	public static final int LISTAR = 7;
	public static final int PUBLICAR_CONVOCATORIA = 8;

	public static String getStringActions() {
		StringBuilder builder = new StringBuilder();
		ActionPermits ap = new ActionPermits();
		List<Field> fields = new ArrayList<>(Arrays.asList(ap.getClass().getFields()));
		int columns = 0;
		builder.append("\n-------------ACCIONES SETEADAS EN LA APP--------\n");
		builder.append("Size: " + fields.size() + "\n");
		for (Field f : fields) {
			try {
				columns++;
				builder.append("( " + f.getName() + " = " + ((int) f.get(ap)) + " )  ");
				if (columns == 5) {
					builder.append("\n");
					columns = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		builder.append("\n---------------------------------\n");
		return builder.toString();
	}

	public static String validateActions(List<Action> actionsBd) {
		StringBuilder builder = new StringBuilder();
		ActionPermits ap = new ActionPermits();
		List<Field> fields = new ArrayList<>(Arrays.asList(ap.getClass().getFields()));
		List<Action> copyActionsBd = new ArrayList<Action>(actionsBd);

		for (Action action : copyActionsBd) {
			List<Field> copyFields = new ArrayList<Field>(fields);
			for (Field field : copyFields) {
				if (action.getAction().equals(field.getName())) {
					try {
						actionsBd.remove(action);
						int code = (int) field.get(ap);
						if (action.getId().equals(new Long(code))) {
							fields.remove(field);
						} else {
							builder.append("Action Error").append("\n\t");
							builder.append("(BD: " + action.getAction() + " = " + action.getId() + " ) !== ");
							builder.append("(APP: " + field.getName() + " = " + code + " ) ").append("\n");
						}
						break;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		if (actionsBd != null && !actionsBd.isEmpty()) {
			builder.append("Acciones Sobrantes en BD\n");
			for (Action action : actionsBd) {
				builder.append("\t" + action.getAction() + " = " + action.getId());
				builder.append("\n");
			}
		}

		if (fields != null && !fields.isEmpty()) {
			builder.append("Acciones Sobrantes en APP\n");
			for (Field field : fields) {
				try {
					builder.append("\t" + field.getName() + " = " + ((int) field.get(ap)));
					builder.append("\n");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		int isCero = builder.length();
		if (isCero > 0) {
			builder.insert(0, "\n\n********************************* ERROR IMPORTANTE **************************************\n");
			builder.insert(builder.length(), "**************************************************************\n\n");
		}
		return isCero == 0 ? null : builder.toString();
	}
}
