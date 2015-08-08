package br.feevale.tc.oee.dao.components;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.usertype.EnhancedUserType;
import org.hibernate.usertype.ParameterizedType;

import br.feevale.tc.oee.enums.OEEEnum;

/**
 * @author Emanuel
 * emanuelcruzrodrigues@gmail.com
 * 07/08/2015
 */
public class PersistentEnum implements EnhancedUserType, ParameterizedType {
	
	private Class<OEEEnum> enumClass;

	@Override
	public void setParameterValues(Properties parameters) {
		String enumClassName = parameters.getProperty("enumClassName");
		try {
			@SuppressWarnings("unchecked")
			Class<OEEEnum> clazz = (Class<OEEEnum>) Class.forName(enumClassName);
			enumClass = clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Enum<?>) value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y;
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) throws HibernateException, SQLException {
		String value = resultSet.getString(names[0]);
		return resultSet.wasNull() ? null : valueOf(value);
	}

	@Override
	public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) throws HibernateException, SQLException {
		if (value == null) {
			preparedStatement.setNull(index, Types.VARCHAR);
		} else {
			preparedStatement.setString(index, ((OEEEnum) value).getValue());
		}
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public Class<?> returnedClass() {
		return enumClass.getClass();
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.VARCHAR };
	}

	@Override
	public Object fromXMLString(String xmlValue) {
		return valueOf(xmlValue);
	}

	@Override
	public String objectToSQLString(Object value) {
		return '\'' + ((OEEEnum) value).getValue() + '\'';
	}

	@Override
	public String toXMLString(Object value) {
		return ((OEEEnum) value).getValue();
	}
	
	private OEEEnum valueOf(String value) {
		for (OEEEnum OEEEnum : enumClass.getEnumConstants()) {
			if (value.equals(OEEEnum.getValue())) {
				return OEEEnum;
			}
		}
		return null;
	}

}
