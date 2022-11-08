package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerThread extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String received = null;
    String modified = null;
    BufferedReader input;
    DataOutputStream output;
 

    public ServerThread (Socket socket)
    {
        this.client = socket;
       
        
    }

    public void run()
    {    
         try {
            comunicate();
        } catch (Exception e) {
        // TODO: handle exception
        e.printStackTrace(System.out);
        }
    }
    public void comunicate() throws Exception{
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new DataOutputStream(client.getOutputStream());
        for(;;)
        {
            //ricevuta messaggio dal client
            ObjectMapper objectMapper = new ObjectMapper();
            received = input.readLine();
            Messaggio ricevuto = objectMapper.readValue(received, Messaggio.class);
            //controllo la size del messaggio ricevuto se è 0 devo il server deve inviare i biglietti disponibili all'acquisto
            if(ricevuto.lista.size() == 0)
            {
                Messaggio a = new Messaggio(MultiServer.lista_biglietti);
                output.writeBytes(objectMapper.writeValueAsString(a) + '\n');
            }

            else
            {
                //creo una lista per i biglietti acquistati e controllo in  caso l'id del biglietto scelto è uguale all'id del biglietto nella lista 
                //rimuovo il biglietto acquistato dalla lista se l'id di quello scelto è uguale
                ArrayList <Biglietto> biglietto_acquistato = new ArrayList<Biglietto>();

                for(int i = 0 ; i < ricevuto.lista.size(); i++)
                {
                    for(int j = 0; j < MultiServer.lista_biglietti.size(); j++)
                    {
                        if(ricevuto.lista.get(i).getId() == MultiServer.lista_biglietti.get(j).getId())
                        {

                            biglietto_acquistato.add(ricevuto.lista.get(i));
                            MultiServer.lista_biglietti.remove(j);
                            j--;
                        }
                    }
                }
                //scrivo al client i biglietti che ha acquistato
                Messaggio messaggio2 = new Messaggio(biglietto_acquistato);
                output.writeBytes(objectMapper.writeValueAsString(messaggio2) + '\n');
            }
            
        
        }
        
     
        
    }
}
