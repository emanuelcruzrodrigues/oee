package br.feevale.tc.oee.dao.components;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.type.TimestampType;
import org.hibernate.usertype.EnhancedUserType;
import org.joda.time.LocalDate;

/**
  *  Copyright 2001-2009 Stephen Colebourne
  *
  *  Licensed under the Apache License, Version 2.0 (the "License");
  *  you may not use this file except in compliance with the License.
  *  You may obtain a copy of the License at
  *
  *      http://www.apache.org/licenses/LICENSE-2.0
  *
  *  Unless required by applicable law or agreed to in writing, software
  *  distributed under the License is distributed on an "AS IS" BASIS,
  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  *  See the License for the specific language governing permissions and
  *  limitations under the License.
  */
@SuppressWarnings("serial")
public class PersistentLocalDate implements EnhancedUserType, Serializable {

	public final static PersistentLocalDate INSTANCE = new PersistentLocalDate();

	private static final int[] SQL_TYPES = new int[] { Types.DATE };

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

	@Override
	public Class<?> returnedClass() {
		return LocalDate.class; 
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		}
		if (x == null || y == null) {
			return false;
		}
		LocalDate dtx = (LocalDate) x;
		LocalDate dty = (LocalDate) y;

		return dtx.equals(dty);
	}

	@Override
	public int hashCode(Object object) throws HibernateException {
		return object.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] strings, Object object) throws HibernateException, SQLException {
		return nullSafeGet(resultSet, strings[0]);

	}

	@SuppressWarnings("deprecation")
	public Object nullSafeGet(ResultSet resultSet, String string) throws SQLException {
		TimestampType.INSTANCE.nullSafeGet(resultSet, string);
		Object timestamp = TimestampType.INSTANCE.nullSafeGet(resultSet, string);
		if (timestamp == null) {
			return null;
		}

		return new LocalDate(timestamp);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			TimestampType.INSTANCE.nullSafeSet(preparedStatement, null, index);
		} else {
			org.joda.time.DateTime dateTime = ((LocalDate) value).toDateTimeAtStartOfDay();
			java.util.Calendar c = dateTime.toCalendar(java.util.Locale.getDefault());
			TimestampType.INSTANCE.nullSafeSet(preparedStatement, c.getTime(), index);
		}
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		if (value == null) {
			return null;
		}

		return new LocalDate(value);
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object value) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public String objectToSQLString(Object object) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toXMLString(Object object) {
		return object.toString();
	}

	@Override
	public Object fromXMLString(String string) {
		return new LocalDate(string);
	}

}