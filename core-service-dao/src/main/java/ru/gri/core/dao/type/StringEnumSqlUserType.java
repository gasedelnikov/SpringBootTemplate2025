package ru.gri.core.dao.type;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

@Slf4j
public class StringEnumSqlUserType implements UserType<String> {

    @Override
    public int getSqlType() {
        return Types.VARCHAR;
    }

    @Override
    public Class<String> returnedClass() {
        return String.class;
    }

    @Override
    public boolean equals(String x, String y) {
        return x.equals(y);
    }

    @Override
    public int hashCode(String x) {
        return x.hashCode();
    }

    @Override
    public String nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        return rs.getString(position);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, String value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (Objects.isNull(value))
            st.setNull(index, Types.OTHER);
        else {
            st.setObject(index, value, Types.OTHER);
        }
    }

    @Override
    public String deepCopy(String value) {
        return value;
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(String value) {
        return deepCopy(value);
    }

    @Override
    public String assemble(Serializable cached, Object owner) {
        return deepCopy((String) cached);
    }

    @Override
    public String replace(String detached, String managed, Object owner) {
        return detached;
    }

}
