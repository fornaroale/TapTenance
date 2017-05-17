/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketuploadimg;

import java.net.*;
import java.io.*;
import java.awt.image.*;

import javax.imageio.*; 
import javax.swing.*; 

/**
 *
 * @author ale
 */

public class Server {
    public static void main(String[] args) throws IOException {
        
        // Avvio Socket su porta
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5645);
        } catch (IOException ex) {
            System.out.println("Impossibile avviare il Server sulla porta impostata.");
        }

        // Inizializzazzione variabili della Socket
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        
        // Socket in attesa
        try {
            socket = serverSocket.accept();
        } catch (IOException ex) {
            System.out.println("Impossibile accettare la richiesta del client.");
        }

        // Leggo flusso dati in entrata
        try {
            in = socket.getInputStream();
        } catch (IOException ex) {
            System.out.println("Impossibile acquisire flusso dati.");
        }
        
        // Scrivo il flusso dati in un file
        try {
            out = new FileOutputStream(new File("D:\\immagine.jpg"));
        } catch (FileNotFoundException ex) {
            System.out.println("File non trovato. ");
        }
        byte[] bytes = new byte[16*1024];
        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        // Chiudo flusso dati e Socket
        out.close();
        in.close();
        socket.close();
        serverSocket.close();
        
        // Messaggio di conferma
        System.out.println("Trasferimento concluso.");
    }
}