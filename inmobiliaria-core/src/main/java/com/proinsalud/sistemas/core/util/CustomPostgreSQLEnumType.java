package com.proinsalud.sistemas.core.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.EnumType;

/**
 * 
 * @author Andres Santacruz
 * @datetime 2/01/2018 - 3:40:54 p. m.
 *
 */
public class CustomPostgreSQLEnumType extends EnumType {
	private static final long serialVersionUID = 459219041571416546L;

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session) throws HibernateException, SQLException {
		if (value == null) {
			st.setNull(index, Types.OTHER);
		} else {
			st.setObject(index, value.toString(), Types.OTHER);
		}
	}
}
