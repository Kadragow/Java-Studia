package Server;

import Algorithms.IElement;
import Algorithms.InsertionSort;
import Algorithms.QuickSort;
import ServersRegistry.ServersRegistryInterface;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server extends UnicastRemoteObject implements ServerInterface, Serializable{
    private static final long serialVersionUID = 2L;
    private Registry registry;
    private Registry serversListRegistry;
    private ServersRegistryInterface serversRegistryInterface;
    private ServerDesc serverDesc;
    private int port = 1100;

    public Server() throws RemoteException {
        super();
        try{
            boolean finished = false;

            while(port < 1300) {
                try {
                    System.out.println("Server na porcie " + port);
                    registry = LocateRegistry.createRegistry(port);
                    registry.rebind("Server" + (port - 1099), this);

                    String algorithmDescription;
                    if(port % 2 == 0)
                        algorithmDescription = "Quick Sort";
                    else
                        algorithmDescription = "Insertion Sort";

                    serverDesc = new ServerDesc("Server" + (port - 1099), port, algorithmDescription);
                    finished = true;
                    break;
                }catch(Exception e){
                    port++;
                }
            }

            if(!finished)
                System.exit(0);

            serversListRegistry = LocateRegistry.getRegistry(1099);
            serversRegistryInterface = (ServersRegistryInterface) serversListRegistry.lookup("ListOfServers");
            serversRegistryInterface.register(serverDesc);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public List<IElement> solve(ArrayList<IElement> listOfItems) {
        if(port % 2 == 0) {
            QuickSort qs = new QuickSort();
            List<IElement> sortedList = qs.solveAll(listOfItems);

            return sortedList;
        }
        else{
            InsertionSort is = new InsertionSort();
            List<IElement> sortedList = is.solveAll(listOfItems);

            return sortedList;
        }
    }

    public static void main(String args[]) throws RemoteException {
        new Server();

    }
}