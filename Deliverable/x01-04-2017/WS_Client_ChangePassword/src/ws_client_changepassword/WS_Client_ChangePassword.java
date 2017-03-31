/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws_client_changepassword;

import java.util.Scanner;

/**
 *
 * @author ale
 */
public class WS_Client_ChangePassword {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        
        for(int i=0;i<200;i++) System.out.println();
        System.out.print("Inserisci username: ");
        String username = keyboard.nextLine();
        System.out.print("Inserisci vecchia password: ");
        String password = keyboard.nextLine();
        System.out.print("Inserisci nuova password: ");
        String nuovaPassword = keyboard.nextLine();
        
        System.out.println();
        System.out.println(modificaPSW(username,password,nuovaPassword));
    }

    private static String modificaPSW(java.lang.String username, java.lang.String passOld, java.lang.String passNew) {
        org.jm.taptenance.WSTapTenanceServer_Service service = new org.jm.taptenance.WSTapTenanceServer_Service();
        org.jm.taptenance.WSTapTenanceServer port = service.getWSTapTenanceServerPort();
        return port.modificaPSW(username, passOld, passNew);
    }
    
}
