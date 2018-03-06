package com.proinsalud.sistemas.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;

/**
 * Se encarga de inicializar una entity con todos sus atributos
 * @author Andres Santacruz
 * @datetime 26/01/2018 - 7:16:16 a. m.
 *
 */
public class HibernateUtil {

	public static byte[] hibernateCollectionPackage = "org.hibernate.collection".getBytes();

	/**
	 * Iniciliza un objeto con todos sus atributos (listas , objetos)
	 * Se le puede pasar por parametro un objeto o una lista
	 * @author Andres Santacruz
	 * @datetime 26/01/2018 - 7:17:29 a. m.
	 *
	 * @param o
	 */
	@SuppressWarnings("rawtypes")
	public static void initializeObject(Object o) {
		try {
			if (o == null) {
				return;
			}
			Set<Object> seenObjects = new HashSet<Object>();
			if (o instanceof List) {
				if (((List) o).isEmpty()) {
					return;
				}
				String insidePackageName = getPackageName(((List) o).get(0).getClass());
				for (Object obj : (List) o) {
					initializeObject(obj, seenObjects, insidePackageName.getBytes());
				}
			} else {
				String insidePackageName = getPackageName(((List) o).get(0).getClass());
				initializeObject(o, seenObjects, insidePackageName.getBytes());
			}
			seenObjects = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	private static void initializeObject(Object o, Set<Object> seenObjects, byte[] insidePackageName) {

		seenObjects.add(o);

		Method[] methods = o.getClass().getMethods();
		for (Method method : methods) {

			String methodName = method.getName();

			// check Getters exclusively
			if (methodName.length() < 3 || !"get".equals(methodName.substring(0, 3)))
				continue;

			// Getters without parameters
			if (method.getParameterTypes().length > 0)
				continue;

			int modifiers = method.getModifiers();

			// Getters that are public
			if (!Modifier.isPublic(modifiers))
				continue;

			// but not static
			if (Modifier.isStatic(modifiers))
				continue;

			try {

				// Check result of the Getter
				Object r = method.invoke(o);

				if (r == null)
					continue;

				// prevent cycles
				if (seenObjects.contains(r))
					continue;

				// ignore simple types, arrays und anonymous classes
				if (!isIgnoredType(r.getClass()) && !r.getClass().isPrimitive() && !r.getClass().isArray() && !r.getClass().isAnonymousClass()) {

					// ignore classes out of the given package and out of the hibernate collection
					// package
					if (!isClassInPackage(r.getClass(), insidePackageName) && !isClassInPackage(r.getClass(), hibernateCollectionPackage)) {
						continue;
					}

					// initialize child object
					Hibernate.initialize(r);

					if (r instanceof List) {
						for (Object obj : (List) r) {
							// traverse over the child object
							initializeObject(obj, seenObjects, insidePackageName);
						}
					} else {
						// traverse over the child object
						initializeObject(r, seenObjects, insidePackageName);
					}

				}

			} catch (InvocationTargetException e) {
				e.printStackTrace();
				return;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return;
			}
		}

	}

	private static final Set<Class<?>> IGNORED_TYPES = getIgnoredTypes();

	private static boolean isIgnoredType(Class<?> clazz) {
		return IGNORED_TYPES.contains(clazz);
	}

	private static Set<Class<?>> getIgnoredTypes() {
		Set<Class<?>> ret = new HashSet<Class<?>>();
		ret.add(Boolean.class);
		ret.add(Character.class);
		ret.add(Byte.class);
		ret.add(Short.class);
		ret.add(Integer.class);
		ret.add(Long.class);
		ret.add(Float.class);
		ret.add(Double.class);
		ret.add(Void.class);
		ret.add(String.class);
		ret.add(Class.class);
		ret.add(Package.class);
		return ret;
	}

	private static Boolean isClassInPackage(Class<?> clazz, byte[] insidePackageName) {

		Package p = clazz.getPackage();
		if (p == null)
			return null;

		byte[] packageName = p.getName().getBytes();

		int lenP = packageName.length;
		int lenI = insidePackageName.length;

		if (lenP < lenI)
			return false;

		for (int i = 0; i < lenI; i++) {
			if (packageName[i] != insidePackageName[i])
				return false;
		}

		return true;
	}

	/**
	 * Retorna la ruta del paquete del objeto pasado por parametro
	 * 
	 * @author Andres Santacruz
	 * @datetime 25/01/2018 - 5:38:47 p. m.
	 *
	 * @param o
	 * @return
	 */
	private static String getPackageName(@SuppressWarnings("rawtypes") Class clazz) {
		try {
			return clazz.newInstance().getClass().getPackage().getName();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}