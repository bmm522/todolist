//package com.jiinkim.todolist.common.config.mybatis;
//
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.TypeHandler;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class StatusTypeHandler implements TypeHandler<Status> {
//
//    @Override
//    public void setParameter(PreparedStatement ps, int i, Status parameter, JdbcType jdbcType) throws SQLException {
//
//        // Convert the enum to the corresponding database value (in this case, a String).
//        ps.setString(i, parameter.getValue());
//    }
//
//    @Override
//    public Status getResult(ResultSet rs, String columnName) throws SQLException {
//
//        // Convert the database value (in this case, a String) to the corresponding enum.
//        return Status.lookup(rs.getString(columnName));
//    }
//
//    @Override
//    public Status getResult(ResultSet rs, int columnIndex) throws SQLException {
//
//        // Convert the database value (in this case, a String) to the corresponding enum.
//        return Status.valueOf(rs.getString(columnIndex));
//    }
//
//    @Override
//    public Status getResult(CallableStatement cs, int columnIndex) throws SQLException {
//
//        // Convert the database value (in this case, a String) to the corresponding enum.
//        return Status.valueOf(cs.getString(columnIndex));
//    }
//}
