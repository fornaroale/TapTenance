/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketuploadimg;

import javax.swing.*;  
import java.net.*; 
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ale
 */

public class Client {
    public static void main(String[] args) throws IOException {
        
        // Creo Socket
        Socket socket = null;
        String host = "127.0.0.1";
        socket = new Socket(host, 5645);
        
        // Creo oggetto file
        File file = new File("C:\\immagine.jpg");
        
        // Rilevo lunghezza file
        long length = file.length();
        byte[] bytes = new byte[16 * 1024];
        
        // Creo flusso e invio dati
        InputStream in = new FileInputStream(file);
        OutputStream out = socket.getOutputStream();
        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
        
        // Chiudo flusso dati e socket
        out.close();
        in.close();
        socket.close();
    }
}