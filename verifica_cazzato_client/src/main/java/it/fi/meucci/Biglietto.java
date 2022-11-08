package it.fi.meucci;

public class Biglietto {
    private int id;
    private String numero_biglietto;


    
    public Biglietto(int id, String numero_biglietto) {
        this.id = id;
        this.numero_biglietto = numero_biglietto;
    }

    
    public Biglietto() {
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNumero_biglietto() {
        return numero_biglietto;
    }
    public void setNumero_biglietto(String numero_biglietto) {
        this.numero_biglietto = numero_biglietto;
    }

    
}
