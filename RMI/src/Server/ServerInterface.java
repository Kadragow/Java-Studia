package Server;

import Algorithms.IElement;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface ServerInterface extends Remote {
    List<IElement> solve(ArrayList<IElement> listOfItems) throws RemoteException;
}
