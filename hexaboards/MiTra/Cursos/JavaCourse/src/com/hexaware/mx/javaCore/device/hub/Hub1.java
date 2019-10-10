/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hexaware.mx.javaCore.device.hub;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.Vector;
import proyecto.Datum;

/**
 *
 * @author Hugo Jacobo
 */
public class Hub1 {

    public static void main(String[] args) {
        Properties prop = new Properties();
        Hub1 h1 = new Hub1();
        String finales = null;
        Vector<Datum> particulas = new Vector<>();
        Vector<Datum> particulas1 = new Vector<>();
        Vector<Datum> particulas2 = new Vector<>();
        Vector<Datum> particulas3 = new Vector<>();
        Vector<Datum> particulas4 = new Vector<>();
        Vector<Datum> particulas5 = new Vector<>();
        int puerto = 0;
        ObjectInputStream obj;

        ServerSocket ss;
        Socket sc;
        String jo;
       
        String flag;
        //llama al archivo
        try (InputStream input = new FileInputStream("C:\\Users\\Training\\Desktop\\Conf.txt")) {

            // load a properties file
            prop.load(input);

            puerto = Integer.parseInt(prop.getProperty("devPort1"));

            ss = new ServerSocket(puerto);
            System.out.println("Server running");

            String u = "";
            int contador = 0;
            int suma, suma2, suma3, suma4 = 0;
            System.out.println("Dispositivo conectado");
            while (contador < 10) {
                sc = ss.accept();

                obj = new ObjectInputStream(sc.getInputStream());

                Datum mensaje = (Datum) obj.readObject();


                if (particulas.add(mensaje)) {
                    System.out.println("Nuevo dato recibido");
                } else {
                    System.out.println("Datos NO guardados");
                }

                sc.close();
                //System.out.println("Dispositivo desconectado");
                contador++;
            }

            // Ciclo para procesar el vector particulas
            for (int i = 0; i < particulas.size(); i++) {
                //  u = particulas.get(i).getDevId();
                u = particulas.get(i).getDeviceId();
//      Switchs

                switch (u) {
                    case "1":

                        particulas1.add(particulas.get(i));

                        break;

                    case "2":

                        particulas2.add(particulas.get(i));
                        break;

                    case "3":

                        particulas3.add(particulas.get(i));

                        break;

                    case "4":

                        particulas4.add(particulas.get(i));

                        break;
                    case "5":

                        particulas5.add(particulas.get(i));

                        break;

                    default:
                        System.out.println("Device no definido");

                }

            }

            String str = " ";
            System.out.println("Report of averages" + "\n");
            int valor1 = h1.GenerarPromedio(particulas1);
            str = str + "Device 1 Average of particles: " + valor1 + "\n";
            int valor2 = h1.GenerarPromedio(particulas2);
            str = str + "Device 2 Average of particles: " + valor2 + "\n";
            int valor3 = h1.GenerarPromedio(particulas3);
            str = str + "Device 3 Average of particles: " + valor3 + "\n";
             int valor4 = h1.GenerarPromedio(particulas4);
             str = str + "Device 4 Average of particles: " + valor4 + "\n";
            int valor5 = h1.GenerarPromedio(particulas5);
             str = str + "Device 5 Average of particles: " + valor5 + "\n";

            System.out.println(str);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public int GenerarPromedio(Vector<Datum> particulas) {

        int suma = 0;
        int contador = particulas.size() - 1;
        for (int i = 0; i < particulas.size(); i++) {
            suma = suma + particulas.get(i).getsplnAir();

        }
        int chr = suma / contador;

        return chr;
    }

}
