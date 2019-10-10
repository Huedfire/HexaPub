/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hexaware.mx.javaCore.device.hub;

import java.util.Date;
import java.util.Random;

/**
 *
 * @author Training
 */
public class Datum {

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

    public String getDeviceId() {
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

   /* sc = new Socket();
                System.out.println("Esperando una conexión:");
                sc = ss.accept();
                System.out.println("Un cliente se ha conectado.");

                System.out.println("Creando Runnable");
                runnable = new MyRunnable(sc, particulas);
                System.out.println("Creando Thread");
                Thread t = new Thread(runnable);
                System.out.println("Iniciando Thread");
                t.start();
                System.out.println(t.getName());
                contador++;
                 // } while (contador == 10);
                 */