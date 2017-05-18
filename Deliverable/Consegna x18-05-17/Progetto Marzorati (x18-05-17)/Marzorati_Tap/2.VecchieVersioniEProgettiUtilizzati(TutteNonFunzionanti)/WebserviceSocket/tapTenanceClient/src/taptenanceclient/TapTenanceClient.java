/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taptenanceclient;

/**
 *
 * @author Marzorati Stefano
 */
public class TapTenanceClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String s=viewDone();
        System.out.println(s);
    }

    private static String viewDone() {
        org.marzorati.taptenance.ViewDoneWS_Service service = new org.marzorati.taptenance.ViewDoneWS_Service();
        org.marzorati.taptenance.ViewDoneWS port = service.getViewDoneWSPort();
        return port.viewDone();
    }
    
}
