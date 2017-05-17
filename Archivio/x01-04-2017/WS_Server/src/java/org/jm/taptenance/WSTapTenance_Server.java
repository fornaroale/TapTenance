/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jm.taptenance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Progetto TapTenance: Fornaro, Marzorati, Della Torre, Zocca
 */
@WebService(serviceName = "WSTapTenance_Server")
public class WSTapTenance_Server {
    
    // PATH COMUNE PER FILE UTENTI:
    final String filePath = "C:\\TapSchool\\utenti.txt";
    

    /**
     * METODO LOGIN
     * AUTORE: FORNARO ALESSANDRO
     */
    @WebMethod(operationName = "login")
    public String login(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
               String[] utente = line.split(",");
                if (utente[0].equals(username) && utente[1].equals(password)) {
                    br.close();
                    return "--> LOGIN EFFETTUATO.";
                }
            }
            br.close();
            return "--> LOGIN NON EFFETTUATO: DATI NON TROVATI.";
        } catch (IOException ex) {
            return "--> LOGIN NON EFFETTUATO: ECCEZIONE GENERICA.";
        }
    }

    
    
    
    
    /**
     * METODO REGISTRAZIONE
     * AUTORE: MARZORATI STEFANO
     */
    @WebMethod(operationName = "signup")
    public String signup(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        String s = username + "," + password + System.getProperty("line.separator");
        BufferedWriter bw = null;
        FileWriter fw = null;

        try{
            fw = new FileWriter(filePath, true);
            bw = new BufferedWriter(fw);
            bw.write(s);
            System.out.println("Done");
            bw.close();
            fw.close();
            return "--> UTENTE REGISTRATO.";
        } catch (IOException e) {
            return "--> UTENTE NON REGISTRATO.";
        }
    }
    
    
    
    
    
    /**
     * METODO MODIFICA PASSWORD
     * AUTORE: DELLA TORRE CHRISTIAN
     * METODO NON FUNZIONANTE <<---------------------------------
     */
    /*
    @WebMethod(operationName = "ModificaPSW")
    public String ModificaPSW(@WebParam(name = "Username") String Username, @WebParam(name = "PassOld") String PassOld, @WebParam(name = "PassNew") String PassNew) {
    
        String path2 = "C:\\TapSchool\\utenti.txt";
        File FileTemp = new File(filePath);
        File FileUtenti = new File(path2);

        if (FileTemp.exists()) {

            FileWriter fw = null;
            try {
                fw = new FileWriter(filePath, true);
            } catch (IOException ex) {
                Logger.getLogger(WSTapTenance_Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            BufferedWriter bw = new BufferedWriter(fw);
            BufferedReader br = null;
            FileReader fr = null;

            try {
                fr = new FileReader(path2);
                br = new BufferedReader(fr);
		String Record;
                br = new BufferedReader(new FileReader(path2));

		while ((Record = br.readLine()) != null) {
                    String[] DatiUtente = Record.split(",");
                    if (DatiUtente[0].equals(Username) && DatiUtente[1].equals(PassOld)) {
			String NuovoUtente = Username + "," + PassNew;
			bw.write(NuovoUtente);			
			}
                    bw.write(Record);
		}
		
		br.close();
		fw.close();
		bw.close();
	
            } catch (IOException ex) {
                    System.out.println("Errore");
            }

        } else try {
            if (FileTemp.createNewFile()) {
                
                FileWriter fw = new FileWriter(filePath, true);
                BufferedWriter bw = new BufferedWriter(fw);
                
                BufferedReader br = null;
                FileReader fr = null;
                
                try {
                    fr = new FileReader(path2);
                    br = new BufferedReader(fr);
                    
                    String Record;
                    
                    br = new BufferedReader(new FileReader(path2));
                    
                    while ((Record = br.readLine()) != null) {
                        
                        String[] DatiUtente = Record.split(",");
                        if (DatiUtente[0].equals(Username) && DatiUtente[1].equals(PassOld)) {
                            
                            String NuovoUtente = Username + "," + PassNew;
                            bw.write(NuovoUtente);
                            
                        }
                        
                        bw.write(Record);
                    }
                    
                    br.close();
                    fw.close();
                    bw.close();
                    
                } catch (IOException ex) {
                    System.out.println("Errore");
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(WSTapTenance_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    */
    
    
    
    
    
    /**
     * METODO DISCONNESSIONE
     * AUTORE: ZOCCA ANDREA
     */
    @WebMethod(operationName = "disconnect")
    public String disconnect(@WebParam(name = "connesso") Boolean connesso) {
        Boolean utenteConnesso;
        utenteConnesso=connesso;
        
        if (utenteConnesso==true) {
            utenteConnesso=false;
            return "Disconnessione effettuata.";
        } else {
            return "Disconnessione non effettuata.";
        }
    }
}
