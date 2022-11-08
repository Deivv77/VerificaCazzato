package it.fi.meucci;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;



public class ClientStr {
    String name_server = "localhost";
    int serverport = 7073;
    Socket mysocket;
    BufferedReader keyboard;
    String user;
    String received;
    DataOutputStream output;
    BufferedReader input;


    public Socket connect()
    {

        try {
            keyboard = new BufferedReader(new InputStreamReader(System.in));
            mysocket = new Socket(name_server, serverport);
            output = new DataOutputStream(mysocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(mysocket.getInputStream()));
            ObjectMapper objectMapper = new ObjectMapper();


            //creo lista biglietti vuota da inviare al server
            ArrayList<Biglietto> lista_vuota = new ArrayList<Biglietto>();
            Messaggio messaggio = new Messaggio(lista_vuota);
            output.writeBytes(objectMapper.writeValueAsString(messaggio)+ '\n');

            //Il client riceve i biglietti disponibili dal server
            String tickets = input.readLine();
            Messaggio ricevuto = objectMapper.readValue(tickets,Messaggio.class);

            for(int i = 0 ; i < ricevuto.lista.size(); i++)
            {
                System.out.println("Id " + ricevuto.lista.get(i).getId() + " " + "numero biglietto: " + ricevuto.lista.get(i).getNumero_biglietto());
            }


        } catch (UnknownHostException e) {
            // TODO: handle exception
            System.err.println("Unknowed host");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Error during the connection");
            System.exit(1);
        }
        return mysocket;
        
    }

    public void comunicate()
    {
        
        try {
         
            //scelta biglietti da acquistare
            System.out.println("Scegli i biglietti da acquistare"+ " (utilizza lo spazio se vuoi acquistare più di un biglietto)" + '\n');
            user = keyboard.readLine();
            ArrayList <Biglietto> shop = new ArrayList<>();
            //creo un array di string dove inserisco l'id scelto dal client e uso il carattere dello spazio come split
            String scelta[] = user.split(" ");
            for(int i = 0; i < scelta.length; i++)
            {
                //grazie allo split fatto prima posso utilizzare ogni stringa del mio array di stringhe come index per l'id del biglietto acquistato
                Biglietto chose = new Biglietto(Integer.parseInt(scelta[i])," ");
                shop.add(chose);
            }


            //invio dei biglietti da comprare
            ObjectMapper objectMapper = new ObjectMapper();

            Messaggio shopping = new Messaggio(shop);
            output.writeBytes(objectMapper.writeValueAsString(shopping) + '\n');
            received = input.readLine();
           // System.out.println("server feedback" + '\n' + received);
          
            for(int i = 0; i<shop.size(); i++)
            {
                System.out.println("Biglietto comprato: " + "ID:" + " " +shop.get(i).getId());
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            System.out.println("Error during the connection");
            System.exit(1);
        }
        

    } 
}