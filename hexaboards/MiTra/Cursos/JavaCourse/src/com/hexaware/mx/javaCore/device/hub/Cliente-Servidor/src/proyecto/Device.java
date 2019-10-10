package proyecto;

import java.io.DataInputStream;
import java.lang.Thread;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

public class Device {

	public static void main(String[] args) {
		while (true) {
			Properties p = new Properties();
			InputStream file = null;

			try {
				file = new FileInputStream("C:\\Users\\Training\\eclipse-workspace\\Cliente-Servidor\\src\\proyecto\\configuracion.txt");
				p.load(file);
			
				final String host = (String)p.getProperty("ipServer");
				final int puerto = Integer.parseInt(p.getProperty("port"));
			    ObjectOutputStream dato;
	
				Socket sc = new Socket(host, puerto);
				dato = new ObjectOutputStream(sc.getOutputStream());
				dato.writeObject(new Datum(p.getProperty("id"),new Date()));
				//dato.flush();
				sc.close();
				Thread.sleep(1000);
				
				
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
		}
	}
}
