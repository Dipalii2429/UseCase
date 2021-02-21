package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.DaoInterface.*;
import com.project.connection.DBConnection;
import com.project.mainclasses.Train;

public class TrainDaoImpl implements TrainDaoInterface {
	DBConnection db = new DBConnection();
	Connection con = db.getConnection();
	Train t = new Train();
	@Override
	public void addTrainDetails(Train t) {

		String query = "insert into Train values(?,?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, t.getTrainNo());
			pst.setString(2, t.getTrainName());
			pst.setString(3, t.getSource());
			pst.setString(4, t.getDestination());
			pst.setFloat(5, (float) t.getTicketprice());
			pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Train getTrainDetails(int trainID) {
		
		String query = "select * from train where trainno=" + trainID;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				t.setTrainNo(rs.getInt(1));
				t.setTrainName(rs.getString(2));
				t.setSource(rs.getString(3));
				t.setDestination(rs.getString(4));
				t.setTicketprice(rs.getFloat(5));
				
				return t;
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;

	}

	@Override
	public List<Train> getAllTrainDetails() {

		List<Train> train = new ArrayList<Train>();

		Statement st;
		try {
			st = con.createStatement();

			String query = "select * from train";
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) {
				Train tn= new Train();
				tn.setTrainNo(rs.getInt(1));
				tn.setTrainName(rs.getString(2));
				tn.setSource(rs.getString(3));
				tn.setDestination(rs.getString(4));
				tn.setTicketprice(rs.getFloat(5));
				train.add(tn);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return train;

	}

}
