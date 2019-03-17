package com.rienzner.networktest;

import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPThread extends Thread{
    private String matnr;
    private String message;

    public TCPThread(String matnr) {
        this.matnr = matnr;
    }

    @Override
    public void run() {
        Socket clientSocket = null;
         try {
              clientSocket = new Socket("se2-isys.aau.at",  53212);
             Log.d("TAG","Start Thread");
             BufferedReader inFromUser= new BufferedReader(new InputStreamReader(System.in));

             DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
             Log.d("TAG","send to SERVER: "+matnr);
             BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

             outToServer.writeBytes(matnr+'\n');
             message=inFromServer.readLine();
             Log.d("Tag","ServerResponse: "+message);

         }
        catch(Exception e){
            message = e.getMessage();
            Log.d("TAG", e.toString());
        }
        finally{

             if (clientSocket != null) {
                 try {
                     clientSocket.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
         }
    }

    public String getMessage(){
        return message;
    }
}
