package proyecto;


import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Datum implements Serializable{
	String deviceId;
	int splnAir;
	Date timeStaps;
	Random r = new Random();
	
	public Datum() {
		
	}
	
	public Datum(String id, int spl, Date t) {
		this.deviceId = id;
		this.splnAir = spl;
		this.timeStaps = t;
	}

	public Datum(String id, Date date) {
		this.deviceId = id;
		this.splnAir = r.nextInt(100);
		this.timeStaps = date;
	}
	
	public String getDeviceId(){
		return deviceId;
	
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
    
	public int getsplnAir() {
		return splnAir;
	}
	
	public void setSplnAir(int splnAir) {
		this.splnAir = splnAir;
	}
	
    public Date timeStaps() {
	return timeStaps;
    }


	public void setTimeStaps(Date timeStaps) {
		this.timeStaps = timeStaps;
	}


    
}


