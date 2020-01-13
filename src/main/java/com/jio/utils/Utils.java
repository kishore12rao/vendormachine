package com.jio.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jio.vm.db.DBConnection;

public class Utils {

	public static JSONArray convertToJSONArray(ResultSet resultSet) {
		JSONArray jsonArray = new JSONArray();
		try {	

			while (resultSet.next()) {
				int total_columns = resultSet.getMetaData().getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i = 0; i < total_columns; i++) {
					obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
				}
				jsonArray.put(obj);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jsonArray;
	}

	public static JSONObject convertToJSONObject(ResultSet resultSet) {
		JSONObject jsonObj = new JSONObject();

		try {			
			while (resultSet.next()) {
				int total_columns = resultSet.getMetaData().getColumnCount();
				JSONObject obj = new JSONObject();
				for (int i = 0; i < total_columns; i++) {
					obj.put(resultSet.getMetaData().getColumnLabel(i + 1).toLowerCase(), resultSet.getObject(i + 1));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return jsonObj;
	}

	public static JSONArray executeQueryWithConn(String query, ArrayList<Object> args) {

		JSONArray res = new JSONArray();
		PreparedStatement sqlStatement;
		ResultSet resultSet = null;
		Connection customerConn = null;
		try{
			customerConn = DBConnection.getConnection();
			sqlStatement = (PreparedStatement) customerConn.prepareStatement(query);

			if (null != args) {
				for (int i = 0; args != null && i < args.size(); i++) {
					sqlStatement.setObject(i + 1, args.get(i));
					// sqlStatement.setDate(parameterIndex, x);
				}
			}
			String queryUpperCase = new String(query.toUpperCase());

			if (queryUpperCase.startsWith("SELECT") ) {
				resultSet = sqlStatement.executeQuery();
			} else if(queryUpperCase.startsWith("UPDATE")){
				sqlStatement.executeUpdate();
			} else if(queryUpperCase.startsWith("CREATE")){
				sqlStatement.executeQuery();
			} else {
				sqlStatement.executeUpdate();
			}

			if(resultSet != null) {
				res = convertToJSONArray(resultSet);
			}

		} catch (Exception e) {
			e.printStackTrace();
			res.put(e);
		} finally {
			if (customerConn != null) {
				try {
					customerConn.close();

				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}

		return res;

	}

	public static int executeUpdateWithConn(String query, ArrayList<Object> args) {

		PreparedStatement sqlStatement;
		Connection customerConn = null;
		int rs = -1;
		try{
			customerConn = DBConnection.getConnection();
			sqlStatement = (PreparedStatement) customerConn.prepareStatement(query);

			if (null != args) {
				for (int i = 0; args != null && i < args.size(); i++) {
					sqlStatement.setObject(i + 1, args.get(i));
				}
			}
			
			rs = sqlStatement.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (customerConn != null) {
				try {
					customerConn.close();

				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}

		return rs;

	}

	public static int insertwithConn(String table, Map<String, Object> values)  {
		Connection con=null;
		int rs=-1;

		try {
			con = DBConnection.getConnection();

			StringBuilder columns = new StringBuilder();
			StringBuilder vals = new StringBuilder();
			int c=0;
			for (String col : values.keySet()) {
				c++;
				columns.append(col).append(",");
				if (values.get(col) instanceof String) {
					vals.append("'").append(values.get(col)).append("' ");
					if(values.size()!=c) {
						vals.append(", ");
					} 
				} else if (values.get(col) instanceof java.sql.Date) {
					vals.append("'").append(values.get(col)).append("' ");
					if(values.size()!=c) {
						vals.append(", ");
					} 
				} else {
					vals.append(values.get(col));
					if(values.size()!=c) {
						vals.append(", ");
					}else {
						vals.append(" ");
					}				
				}
			}
			columns.setLength(columns.length() - 1);
			vals.setLength(vals.length() - 1);

			String q = "INSERT INTO %s (%s) VALUES (%s) ";
			//	q += "returning id";

			String query = String.format(q, table, columns.toString(), vals.toString());

			rs = con.createStatement().executeUpdate(query);
			
			if( con != null) con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				if( con != null) con.close();
				e.printStackTrace();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		};


		return rs;
	}

}
