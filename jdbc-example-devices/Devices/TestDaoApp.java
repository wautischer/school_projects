package at.htlklu.app;

import java.util.ArrayList;

import at.htlklu.db.Device;
import at.htlklu.db.DeviceDao;

public class TestDaoApp {

	public static void main(String[] args) {
		
	/*
		schema = "iotdevices"
		table = "device":
			INTEGER id          ... eindeutite ID als primary Key, not null, auto-increment
			VARSCHAR(255) name  ... not null 
			BOOLEAN state       ... not null
	*/
		
		System.out.println(DeviceDao.getStatusOfDeviceId(8));
		ArrayList<Device> listOfDev = DeviceDao.getAllDevices();
		System.out.println(listOfDev);
		
		System.out.println(DeviceDao.changeDevStatus(8));
		listOfDev = DeviceDao.getAllDevices();
		System.out.println(listOfDev);
		
	}

}
