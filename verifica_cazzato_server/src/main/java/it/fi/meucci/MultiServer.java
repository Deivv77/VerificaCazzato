package it.fi.meucci;

    import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServer {
    static ArrayList <Socket> list_socket = new ArrayList<Socket>();
    static ArrayList <Thread> list_thread = new ArrayList<Thread>();
    static ServerSocket server;
    static ArrayList <Biglietto> lista_biglietti = new ArrayList<Biglietto>();
   

    public MultiServer()
    {
        //creo i biglietti e li inserisco nella lista statica come fosse un database
        Biglietto b1 = new Biglietto(1, "Curva1-1a");
        Biglietto b2 = new Biglietto(2,"Tribuna vip-2a");
        Biglietto b3 = new Biglietto(3,"Curva-2c");
        Biglietto b4 = new Biglietto(4, "Tribuna-6b");
        Biglietto b5 = new Biglietto(5,"Tribuna ospite-3a");
        Biglietto b6 = new Biglietto(6, "Tribuna stampa-8a");
        Biglietto b7 = new Biglietto(7, "Parterre-7b");
        Biglietto b8 = new Biglietto(8, "Maratona Centrale-7b");
        Biglietto b9 = new Biglietto(9, "Tribuna ospite-3c");
        
        lista_biglietti.add(b1);
        lista_biglietti.add(b2);
        lista_biglietti.add(b3);
        lista_biglietti.add(b4);
        lista_biglietti.add(b5);
        lista_biglietti.add(b6);
        lista_biglietti.add(b7);
        lista_biglietti.add(b8);
        lista_biglietti.add(b9);
    }
    

    public void beginning()
    {
        try {
           server = new ServerSocket(7073);
          
        for(;;)
        {
            System.out.println("---Server acceso---");
            Socket socket = server.accept();
            list_socket.add(socket);
            System.out.println("Server socket " + socket);
            ServerThread server_thread = new ServerThread(socket);
            list_thread.add(server_thread);
            server_thread.start();

        }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
            System.out.println("Error during server instance");
            System.exit(1);
        }
        
    }

   
}



