package com.example.christian.push_taptenance;

import org.w3c.dom.Text;

/**
 * Created by Christian on 29/04/2017.
 */
public class Segnalazione {
    String Titolo;
    String Descrizione;
    int Lotto;
    String Utente;

    Segnalazione() {
        Titolo = "";
        Descrizione = "";
        Lotto = 0;
        Utente = "";
    }

    public String getTitolo() {
        return Titolo;
    }

    public String getUtente() {
        return Utente;
    }

    public int getLotto() {
        return Lotto;
    }

    public String getDescrizione() {
        return Descrizione;
    }

    public void setTitolo(String titolo) {
        Titolo = titolo;
    }

    public void setDescrizione(String descrizione) {
        Descrizione = descrizione;
    }

    public void setLotto(int lotto) {
        Lotto = lotto;
    }

    public void setUtente(String utente) {
        Utente = utente;
    }

    public Segnalazione(String Titolo, String Desc, int Lotto, String Utente) {
        this.Descrizione = Desc;
        this.Titolo = Titolo;
        this.Lotto = Lotto;
        this.Utente = Utente;

    }
}
