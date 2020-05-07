package Client;

import Algorithms.FloatElement;
import Algorithms.IElement;
import Server.ServerDesc;
import Server.ServerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClientController {
    private Client client;
    private ArrayList<IElement> listOfElements = new ArrayList<IElement>();
    private Registry serverRegistry;
    private ServerInterface serverInterface;

    @FXML
    TextArea textAreaResult;
    @FXML
    private ComboBox<ServerDesc> comboBoxServers;

    public void initialize() throws RemoteException, NotBoundException {
        setClient(new Client());
        client.setClientController(this);
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TextArea getTextAreaResult() {
        return textAreaResult;
    }

    public ComboBox<ServerDesc> getComboBoxServers() {
        return comboBoxServers;
    }

    public void actionSolve() throws RemoteException, NotBoundException {
        textAreaResult.clear();
        String serverName = comboBoxServers.getSelectionModel().getSelectedItem().getName();
        int port = comboBoxServers.getSelectionModel().getSelectedItem().getPort();
        serverRegistry = LocateRegistry.getRegistry(port);
        serverInterface = (ServerInterface) serverRegistry.lookup(serverName);
        List<IElement> sortedList = serverInterface.solve(listOfElements);
        for(IElement ele : sortedList){
            textAreaResult.setText(textAreaResult.getText() + "\n" + ele.getName() + " Wartosc: " + ele.getValue());
        }

    }

    public void actionGenerateRandom(){
        Random random = new Random();
        listOfElements.clear();
        textAreaResult.clear();
        for (int i = listOfElements.size(); i < 32; i++)
            if (listOfElements.size() < 32) {
                IElement element = new FloatElement(random.nextFloat()*100,"Element : " + i);
                listOfElements.add(element);
                textAreaResult.setText(textAreaResult.getText() + "\n" + element.getName() + " Wartosc: " + element.getValue());
            }
    }
}