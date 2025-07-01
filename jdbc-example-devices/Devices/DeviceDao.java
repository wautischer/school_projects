package at.htlklu.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeviceDao {

	static Connection conn;
	static Statement stmt;

	// mapRow of a row of table device
	private static Device mapRow(ResultSet rsetToMap) throws SQLException {
		return new Device(rsetToMap.getInt(1), rsetToMap.getString(2), rsetToMap.getBoolean(3));
	}

	// Method: Get all device
	public static ArrayList<Device> getAllDevices() {

		ArrayList<Device> listOfAllDevices = new ArrayList<Device>();

		try {
			conn = DbConnection.getConnection();
			stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM device");

			while (rset.next() == true) {

				/*
				 * int idDev = rset.getInt(1); 
				 * String nameDev = rset.getString(2); 
				 * boolean stateDev = rset.getBoolean(3);
				 * Device newDev = new Device(idDev,nameDev,stateDev);
				 * listOfAllDevices.add(newDev);
				 */

				listOfAllDevices.add(mapRow(rset));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listOfAllDevices;
	}

	// Method: Insert a Device Object
	public static int insertDevice(Device devToInsert) {

		String sql = "INSERT INTO device (devicename, state) VALUES (?, ?)";
		int toRet = -1; // -1 = Error
		try {
			PreparedStatement pst = DbConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// ?:
			pst.setString(1, devToInsert.getDevicename()); // ? > for devicename
															// in SQL
			pst.setBoolean(2, devToInsert.isState()); // ? > for state in SQL

			toRet = pst.executeUpdate();

			// write id from insert (DB) back to object (JAVA)
			ResultSet rsetKeys = pst.getGeneratedKeys();
			if (rsetKeys.next()) {
				int genId = rsetKeys.getInt(1);
				devToInsert.setId(genId);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return toRet;
	}

	// Change state of one device
	public static int changeDevStatus(int id) {
		Statement upd;		
		String sql = "";
		int stateOfADeviceAsInt = getStatusOfDeviceId(id);
		int ret = -1;

		if (stateOfADeviceAsInt != -1) {
			if (stateOfADeviceAsInt == 1) {
				sql = "UPDATE device SET state = false WHERE id = " + id;
			} else {
				sql = "UPDATE device SET state = true WHERE id = " + id;
			}
			try {
				Connection conn = DbConnection.getConnection();
				upd = conn.createStatement();
				ret = upd.executeUpdate(sql);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static int getStatusOfDeviceId(int id) {
		// TODO Auto-generated method stub
		int retVal = -1;
		String sql = "SELECT state FROM device WHERE id = ?";
		try {
			PreparedStatement pst = DbConnection.getConnection().prepareStatement(sql);
			pst.setInt(1, id);
			ResultSet rset = pst.executeQuery();
			if (rset.next()) {
				retVal = rset.getInt("state");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}

}