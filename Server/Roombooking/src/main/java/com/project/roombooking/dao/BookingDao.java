package com.project.roombooking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.project.roombooking.model.BookingModel;
import com.project.roombooking.model.Response;
import com.project.roombooking.model.RoombookingModel;

@Component
public class BookingDao {

	Response res = new Response();

	String url = "jdbc:mysql://127.0.0.1:3306/guestroombooking";
	String username = "root";
	String password = "Kamali@123";

	String SNO;
	String sno;

	@SuppressWarnings({ "unchecked"})
	public Response insertbooking(String s_no, BookingModel datas) {

		
		
//		 Date  date = new Date(0);
//		 datas.setCheckIn(date);
//		 datas.setCheckOut(date);
		 
		
		
		System.out.println(s_no);

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement()) {

				String selectqury = "select created_by  from guestroombooking.roomdetails where room_no ='"+ datas.getRoomNo() + "';";
						
				System.out.println(selectqury);
				
				

				ResultSet rs = st.executeQuery(selectqury);

				JSONArray jsonArray = new JSONArray();

				while (rs.next()) {

					SNO = rs.getString("created_by");
					

					JSONObject jsonObject = new JSONObject();
					jsonObject.put("sNo", rs.getString("created_by"));
					jsonArray.add(jsonObject);

				}

				String orderquery = " INSERT INTO guestroombooking.bookingdetails (room_num,name,adharnumber,mobilenumber,booking_to,booking_by,check_in,check_out,status)VALUES('"
						+ datas.getRoomNo() + "','" + datas.getName() + "'," + datas.getAdharNumner() + ","+datas.getMobileNumber()+",'" + SNO + "','"+s_no+"','" + datas.getCheckIn() + "','" + datas.getCheckOut() + "','"+ datas.getStatus() + "')";
																	

				System.out.println(orderquery);

				st.executeUpdate(orderquery);

				res.setResponseCode(200);
				res.setResponseMgs("success");
				res.setData("Booking   successfully");

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMgs("failed");
				res.setData("Failed to placed");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	@SuppressWarnings("unchecked")
	public Response getonebooking(String s_no) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String getOnequery = "SELECT * FROM guestroombooking.bookingdetails WHERE booking_to= '" + s_no + "' ";
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(getOnequery);) {
				JSONArray jsonArry = new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("roomNo", rs.getString("room_num"));
					jsonObject.put("name", rs.getString("name"));
					jsonObject.put("adharNumner", rs.getString("adharnumber"));
					jsonObject.put("mobileNumber", rs.getString("mobilenumber"));
					jsonObject.put("bookingTo", rs.getString("booking_to"));
					jsonObject.put("bookingBy", rs.getString("booking_by"));
					jsonObject.put("checkIn", rs.getString("check_in"));
					jsonObject.put("checkOut", rs.getString("check_out"));
					jsonObject.put("status", rs.getString("status"));

				

					jsonArry.add(jsonObject);
				}
				res.setResponseCode(200);
				res.setResponseMgs("success");
				res.setData("get the user  successfully !");
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

	public Response delete(String sNo) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement()) {

				String delete = "DELETE FROM  guestroombooking.bookingdetails  WHERE s_no = '" + sNo + "'";
				st.executeUpdate(delete);
				res.setResponseCode(200);
				res.setResponseMgs("success");
				res.setData("user deleted successfully !");

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
	
	public Response editbooking(BookingModel datas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement()) {
				String editdesignerquery = "UPDATE  guestroombooking.bookingdetails SET room_num ='"
						+ datas.getRoomNo() + "' ,check_out = '" + datas.getCheckOut() + "',status = '"+ datas.getStatus() + "'";
						

				System.out.println(editdesignerquery);
				st.executeUpdate(editdesignerquery);
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
}
