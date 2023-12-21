package com.project.roombooking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.project.roombooking.model.Response;
import com.project.roombooking.model.RoombookingModel;
import com.project.roombooking.service.RoombookingService;

@Component
public class RoombookingDao  implements RoombookingService{

	Response res = new Response();
	String url = "jdbc:mysql://127.0.0.1:3306/guestroombooking";
	String username = "root";
	String password = "Kamali@123";

	@Override
	public Response createdetails( String sNo,RoombookingModel datas){
		String uuid = UUID.randomUUID().toString();
		datas.setsNo(uuid);
		datas.setCreatedBy(sNo);
		datas.setUpdatedBy(sNo);
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		datas.setCreatedDate(date);
		datas.setUpdatedDate(date);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement()) {
				
				String desigenrquery ="INSERT INTO guestroombooking.roomdetails(snum,room_no,number_of_rooms,duration_time,room_images,created_by,updated_by,created_date,updated_date,price)VALUES('"+datas.getsNo()+"','"+datas.getRoomNo()+"','"+datas.getNumberofRooms()+"','"+datas.getDurationtime()+"','"+datas.getRoomImage()+"','"+datas.getCreatedBy()+"','"+datas.getUpdatedBy()+"','"+datas.getCreatedDate()+"','"+datas.getUpdatedDate()+"','"+datas.getPrice()+"');";
				System.out.println(desigenrquery);
				st.executeUpdate(desigenrquery);
				res.setResponseCode(200);
				res.setResponseMgs("success");
				res.setData("uploaded successfully");

			} catch (Exception e) {
				e.printStackTrace();
				res.setResponseCode(500);
				res.setResponseMgs("failed");
				res.setData("uploaded failed");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Response editroomdetails(RoombookingModel datas) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement()) {
				String editdesignerquery = "UPDATE  guestroombooking.roomdetails SET room_no ='"
						+ datas.getRoomNo() + "' ,number_of_rooms = '" + datas.getNumberofRooms() + "',duration_time = '"
						+ datas.getDurationtime() + "',room_images = '"  +datas.getRoomImage()
						+ "',price = '" +datas.getPrice() + "'WHERE snum ='" + datas.getsNo()
						+ "'";

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

	@SuppressWarnings("unchecked")
	@Override
	public Response getAllroom() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String getAllquery = "SELECT * FROM  guestroombooking.roomdetails";
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(getAllquery);) {
				JSONArray jsonArry = new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("roomNo", rs.getString("room_no"));
					jsonObject.put("numberofRooms", rs.getString("number_of_rooms"));
					jsonObject.put("durationtime", rs.getString("duration_time"));
					jsonObject.put("roomImage", rs.getString("room_images"));
					jsonObject.put("price", rs.getString("price"));
					jsonObject.put("sNo", rs.getString("snum"));

					jsonArry.add(jsonObject);
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

	@SuppressWarnings("unchecked")
	@Override
	public Response getOneroom(String sNo) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String getAllquery = "SELECT * FROM  guestroombooking.roomdetails WHERE snum='" + sNo + "'";
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(getAllquery);) {
				JSONArray jsonArry = new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("roomNo", rs.getString("room_no"));
					jsonObject.put("numberofRooms", rs.getString("number_of_rooms"));
					jsonObject.put("durationtime", rs.getString("duration_time"));
					jsonObject.put("roomImage", rs.getString("room_images"));
					jsonObject.put("price", rs.getString("price"));
					jsonObject.put("sNo", rs.getString("snum"));
					jsonArry.add(jsonObject);
				}
				res.setResponseCode(200);
				res.setResponseMgs("success");
				res.setData("get one  successfully !");
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

	@SuppressWarnings("unchecked")
	@Override
	public Response getOneownerroom(String s_no) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String getAllquery = "SELECT * FROM guestroombooking.roomdetails where created_by ='" + s_no + "';";
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();
					ResultSet rs = st.executeQuery(getAllquery);) {
				JSONArray jsonArry = new JSONArray();
				while (rs.next()) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("roomNo", rs.getString("room_no"));
					jsonObject.put("numberofRooms", rs.getString("number_of_rooms"));
					jsonObject.put("durationtime", rs.getString("duration_time"));
					jsonObject.put("roomImage", rs.getString("room_images"));
					jsonObject.put("price", rs.getString("price"));
					jsonArry.add(jsonObject);

				}
				res.setResponseCode(200);
				res.setResponseMgs("success");
				res.setData("get one  successfully !");
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
