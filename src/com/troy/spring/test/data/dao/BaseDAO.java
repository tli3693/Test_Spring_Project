package com.troy.spring.test.data.dao;

import java.sql.SQLException;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public abstract class BaseDAO  {
	@Autowired
	protected DataSource oracleDataSource;

//	@PostConstruct
//	private void initialize() {
//		setDataSource(oracleDataSource);
//	}
//
//	private Connection getConnection(String db) {
//		Connection conn = null;
//		try {
//
//			if (oracleDataSource == null) {
//				throw new SQLException("DATASOURCE WAS NULL!");
//			}
//			conn = oracleDataSource.getConnection();
//			if (conn == null) {
//				throw new SQLException("CONNECTION WAS NULL!");
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return conn;
//
//	}
//
//	private PreparedStatement setParameters(PreparedStatement stmt,
//			Object[] params) throws SQLException {
//		int index = 1;
//		if(params!=null) {
//			for (Object obj : params) {
//				if (obj instanceof String)
//					stmt.setString(index, (String) obj);
//				else if (obj instanceof String)
//					stmt.setString(index, (String) obj);
//				else if (obj instanceof Integer)
//					stmt.setInt(index, (Integer) obj);
//				else if (obj instanceof Boolean)
//					stmt.setBoolean(index, (Boolean) obj);
//				else if (obj instanceof Float)
//					stmt.setFloat(index, (Float) obj);
//				else if (obj instanceof Double)
//					stmt.setDouble(index, (Double) obj);
//				else if (obj instanceof Byte)
//					stmt.setByte(index, (Byte) obj);
//				else if (obj instanceof Short)
//					stmt.setShort(index, (Short) obj);
//				else
//					throw new SQLException("Data type not found: " + obj.getClass());
//				index++;
//			}
//		}
//		return stmt;
//	}

//	public ResultSet sqlSelect(String sql, Object[] params, String db)
//			throws SQLException {
//		Connection conn = getConnection();
//		PreparedStatement stmt = null;
//
//		try {
//			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
//					ResultSet.CONCUR_UPDATABLE);
//			setParameters(stmt, params);
//			ResultSet rs = stmt.executeQuery();
//			try {
//				CachedRowSet rowset = new CachedRowSetImpl();
//				rowset.populate(rs);
//				return rowset;
//			} finally {
//				rs.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null)
//				conn.close();
//			if (stmt != null)
//				stmt.close();
//		}
//		return null;
//	}


//	public int sqlInsertOrUpdate(String sql, Object[] params, String db)
//			throws SQLException {
//		Connection conn = getConnection();
//		PreparedStatement stmt = null;
//
//		try {
//			stmt = conn.prepareStatement(sql);
//			setParameters(stmt, params);
//			return stmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null)
//				conn.close();
//			if (stmt != null)
//				stmt.close();
//		}
//		return -1;
//	}
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @param db
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing, or -1 on failure
	 * @throws SQLException
	 */
	public int sqlInsertOrUpdate(String sql, Object[] params, String db) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(oracleDataSource);

		int returnCode =  jdbcTemplate.update(sql, params);
		//SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, params);
		
	
		return returnCode;
	}
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @param db
	 * @return RowSet of data retrieved, else return null
	 * @throws SQLException
	 */
	public SqlRowSet sqlSelect(String sql, Object[] params, String db)
			throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(oracleDataSource);

		
		SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, params);
	
		return rs;
	}
	

//	public int sqlDelete(String sql, Object[] params, String db)
//			throws SQLException {
//		Connection conn = getConnection();
//		PreparedStatement stmt = null;
//
//		try {
//			stmt = conn.prepareStatement(sql);
//			setParameters(stmt, params);
//			return stmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (conn != null)
//				conn.close();
//			if (stmt != null)
//				stmt.close();
//		}
//		return -1;
//	}
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @param db
	 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing, or -1 on failure
	 * @throws SQLException
	 */
	public int sqlDelete(String sql, Object[] params, String db)
			throws SQLException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(oracleDataSource);

		int returnCode =  jdbcTemplate.update(sql, params);
		//SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, params);
	
		return returnCode;
	}
	
	

}
