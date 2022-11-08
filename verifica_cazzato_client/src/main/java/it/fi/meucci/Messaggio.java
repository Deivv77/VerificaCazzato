package it.fi.meucci;

import java.util.ArrayList;

public class Messaggio {
    ArrayList <Biglietto> lista = new ArrayList<Biglietto>();

    public Messaggio(ArrayList<Biglietto> lista) {
        this.lista = lista;
    }

    public Messaggio() {
    }

    public ArrayList<Biglietto> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Biglietto> lista) {
        this.lista = lista;
    }
    
    
}
