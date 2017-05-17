/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_client_logout;

import java.util.Scanner;

/**
 *
 * @author ale
 */
public class WS_Client_Logout {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        for(int i=0;i<200;i++) System.out.println();
        System.out.print("Inserisci username: ");
        String username = keyboard.nextLine();
        
        System.out.println();
        System.out.println("Utente: " + username + " - " + disconnect(true));
    }

    private static String disconnect(java.lang.Boolean connesso) {
        org.jm.taptenance.WSTapTenanceServer_Service service = new org.jm.taptenance.WSTapTenanceServer_Service();
        org.jm.taptenance.WSTapTenanceServer port = service.getWSTapTenanceServerPort();
        return port.disconnect(connesso);
    }
    
}
