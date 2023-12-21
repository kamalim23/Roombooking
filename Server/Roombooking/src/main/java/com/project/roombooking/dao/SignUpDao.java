package com.project.roombooking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.project.roombooking.model.Response;
import com.project.roombooking.model.SignUpModel;
import com.project.roombooking.service.SignUpservice;


@Component
public class SignUpDao  implements SignUpservice{
	Response res = new Response();

	String url = "jdbc:mysql://127.0.0.1:3306/guestroombooking";
	String username = "root";
	String password = "Kamali@123";
   @Override
	public Response createUser(SignUpModel datas) {

		String uuid = UUID.randomUUID().toString();
		datas.setsNo(uuid);
		datas.setCreatedBy(uuid);
		datas.setUpdatedBy(uuid);

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		datas.setCreatedDate(date);
		datas.setUpdatedDate(date);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement()) {

				String insertquery = "INSERT INTO  guestroombooking.signuptable"
						+ "(s_no,name,email,mobile_number,is_customer,created_by,updated_by,created_date,update_date)VALUES('"
						+ datas.getsNo() + "','" + datas.getName() + "','" + datas.getEmail() + "',"

						+ datas.getPhoneNumber() + "," + datas.getIsCustomer() + ",'" + datas.getCreatedBy() + "','"
						+ datas.getUpdatedBy() + "','" + datas.getCreatedDate() + "','" + datas.getUpdatedDate() + "')";

				System.out.println(insertquery);
				st.executeUpdate(insertquery);
				res.setResponseCode(200);
				res.setResponseMgs("success");
				res.setData("user created successfully !");

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMgs("failed");
				res.setData("Internal server error ! ");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

		return res;
	}
   @Override
	public Response editDedails(SignUpModel datas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement()) {
				String editquery = "UPDATE guestroombooking.signuptable SET first_name = '" + datas.getName()+"',email = '"
						+ datas.getEmail() + "'  ,mobile_number ="
						+ datas.getPhoneNumber() + "  ,updated_date = '"
						+ datas.getUpdatedDate() + "'WHERE s_no ='" + datas.getsNo() + "';";
				System.out.println(editquery);
				st.executeUpdate(editquery);
				res.setResponseCode(200);
				res.setResponseMgs("success");
				res.setData("updated successfully !");

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMgs("failed");
				res.setData("Internal server error ! ");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
   @Override
	public Response deleteDetails(String sNo) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement()) {
				String deletequery = "UPDATE guestroombooking.signuptable SET is_customer = 0 WHERE s_no = '" + sNo
						+ "';";
				System.out.println(deletequery);
				st.executeUpdate(deletequery);
				res.setResponseCode(200);
				res.setResponseMgs("success");
				res.setData("user deleted successfully !");

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMgs("failed");
				res.setData("user delete to failed ! ");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	 @Override
	public Response login(SignUpModel datas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println(datas.getEmail());
			System.out.println(datas.getPhoneNumber());
			String selectquery = "SELECT * FROM guestroombooking.signuptable WHERE email='" + datas.getEmail()
					+ "' AND mobile_number = '" + datas.getPhoneNumber() + "'";
			try (Connection conn = DriverManager.getConnection(url, username, password);
					PreparedStatement pst = conn.prepareStatement(selectquery)) {
				ResultSet rs = pst.executeQuery(selectquery);

				JSONObject jsonObject = new JSONObject();

				if (rs.next()) {
					if (String.valueOf(rs.getInt("is_customer")).equalsIgnoreCase("1")) {
						res.setData("owner");
						jsonObject.put("sNo", rs.getString("s_no"));
						jsonObject.put("isCustomer", rs.getInt("is_customer"));

						res.setjData(jsonObject);
					} else if (String.valueOf(rs.getInt("is_customer")).equalsIgnoreCase("0")) {
						res.setData("Customer");
						jsonObject.put("sNo", rs.getString("s_no"));
						jsonObject.put("isCustomer", rs.getInt("is_customer"));

						res.setjData(jsonObject);
					}
				} else {
					res.setData("User Does Not Exist!");
				}

				res.setResponseCode(200);
				res.setResponseMgs("success");

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMgs("failed");
				res.setData("Internal server error ! ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}
	
	@SuppressWarnings("unchecked")
	
	public Response getoneData(String sNo) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String getAllquery = "SELECT s_no,name,email  FROM guestroombooking`.`signuptable WHERE s_no='" + sNo + "'";
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(getAllquery);) {

				JSONObject jsonObject = new JSONObject();

				while (rs.next()) {
					jsonObject.put("sNo", rs.getString("s_no"));
					jsonObject.put("name", rs.getString("name"));
					jsonObject.put("email", rs.getString("email"));
				}
				res.setResponseCode(200);
				res.setResponseMgs("success");
				res.setData("get one  successfully !");
				res.setjData(jsonObject);

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMgs("faild");
				res.setData("Internal server error  !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}
	@SuppressWarnings("unchecked")
	public Response getall() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String getAllquery = "SELECT * FROM  guestroombooking.signuptable ";
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(getAllquery);) {
				JSONArray jsonArry = new JSONArray();
			
			
					JSONObject jsonObject = new JSONObject();
					  while (rs.next()) {
						if (String.valueOf(rs.getInt("is_customer")).equalsIgnoreCase("1")) {
							res.setData("owner");
							jsonObject.put("sNo", rs.getString("s_no"));
							jsonObject.put("name", rs.getString("name"));
							jsonObject.put("email", rs.getString("email"));
					
					jsonArry.add(jsonObject);
						} else {
							res.setData("User Does Not Exist!");
						}

						res.setResponseCode(200);
						res.setResponseMgs("success");
				}
				res.setResponseCode(200);
				res.setResponseMgs("success");
				res.setData("get all the user successfully !");
				res.setjData(jsonArry);

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMgs("faild");
				res.setData("Internal server error  !");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return res;
	}

}
