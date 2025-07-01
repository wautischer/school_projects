package at.htlklu.db;

public class Device {
	
 private int id;
 private String devicename;
 private boolean state;
 
	public Device(int id, String devicename, boolean state) {
		super();
		this.id = id;
		this.devicename = devicename;
		this.state = state;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDevicename() {
		return devicename;
	}
	
	public void setDevicename(String devicename) {
		this.devicename = devicename;
	}
	
	public boolean isState() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Device [getId()=" + getId() + ", getDevicename()=" + getDevicename() + ", isState()=" + isState() + "]";
	}
	
	//equals, compareTo, .... bei Bedarf
	
}
