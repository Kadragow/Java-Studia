package lab;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainViewController {
    private Connect connect;

    private String idText;
    private String firstName;
    private String lastName;
    private String salaryText;
    private String someText;
    private int idInt;
    private int salaryInt;

    private String idAnimalText;
    private String name;
    private String ownerIDText;
    private int idAnimalInt;
    private int ownerIDInt;

    @FXML
    private TextField textFieldPersonID;
    @FXML
    private TextField textFieldPersonFirstName;
    @FXML
    private TextField textFieldPersonLastName;
    @FXML
    private TextField textFieldPersonSalary;
    @FXML
    private  TextField textFieldPersonSomeField;
    @FXML
    private TextField textFieldAnimalID;
    @FXML
    private TextField textFieldAnimalName;
    @FXML
    private TextField textFieldAnimalOwnerID;
    @FXML
    private Label labelError;
    @FXML
    private TableView tableViewPerson;
    @FXML
    private TableColumn<Person, Integer> tableColumnPersonID;
    @FXML
    private TableColumn<Person, String> tableColumnPersonFirstName;
    @FXML
    private TableColumn<Person, String> tableColumnPersonLastName;
    @FXML
    private TableColumn<Person, Integer> tableColumnPersonSalary;
    @FXML
    private TableColumn<Person, String> tableColumnPersonSomeText;
    @FXML
    private TableView tableViewAnimal;
    @FXML
    private TableColumn<Animal, Integer> tableColumnAnimalID;
    @FXML
    private TableColumn<Animal, String> tableColumnAnimalName;
    @FXML
    private TableColumn<Animal, Integer> tableColumnAnimalOwnerID;



    public void initialize(){
        connect = new Connect();
        connect.dbConnect();

        tableColumnPersonID.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
        tableColumnPersonFirstName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        tableColumnPersonLastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        tableColumnPersonSalary.setCellValueFactory(new PropertyValueFactory<Person, Integer>("salary"));
        tableColumnPersonSomeText.setCellValueFactory(new PropertyValueFactory<Person, String>("someText"));

        tableColumnAnimalID.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("id"));
        tableColumnAnimalName.setCellValueFactory(new PropertyValueFactory<Animal, String >("name"));
        tableColumnAnimalOwnerID.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("ownerID"));
    }

    public void actionInsertPerson() {
        labelError.setText("");
        if(!checkFieldsPerson()) return;

        if(!connect.dbInsertPerson(idInt, firstName, lastName, salaryInt, someText)) labelError.setText("Błąd!");
    }

    public void actionUpdatePerson() {
        labelError.setText("");
        if(!checkFieldsPerson()) return;

        if(!connect.dbUpdatePerson(idInt, firstName, lastName, salaryInt, someText)) labelError.setText("Błąd!");
    }

    public void actionFillTablePerson(){
        ArrayList<Person> arrayListPeople = connect.dbQueryPeople();
        ObservableList<Person> observableListPeople = FXCollections.observableArrayList(arrayListPeople);
        tableViewPerson.setItems(observableListPeople);
    }

    public void actionInsertAnimal() {
        labelError.setText("");
        if(!checkFieldsAnimal()) return;

        if(!connect.dbInsertAnimal(idAnimalInt, name, ownerIDInt)) labelError.setText("Błąd!");
    }

    public void actionUpdateAnimal(){
        labelError.setText("");
        if(!checkFieldsAnimal()) return;

        if(!connect.dbUpdateAnimal(idAnimalInt, name, ownerIDInt)) labelError.setText("Błąd!");
    }

    public void actionFillTableAnimal(){
        ArrayList<Animal> arrayListAnimals = connect.dbQueryAnimals();
        ObservableList<Animal> observableListAnimals = FXCollections.observableArrayList(arrayListAnimals);
        tableViewAnimal.setItems(observableListAnimals);
    }

    public void actionImportFromXml() throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Persons persons = (Persons) jaxbUnmarshaller.unmarshal( new File("ExportedData.xml"));

        System.out.println("Wczytane dane z pliku xml: ");
        for(Person p : persons.getPersons()){
            System.out.println(p.toString());
            connect.dbUpdatePerson(p.getId(),p.getFirstName(), p.getLastName(),p.getSalary(),p.getSomeText());
        }
    }

    public void actionExportToXml() throws JAXBException {
        List<Person> personList = connect.dbQueryPeople();
        Persons persons = new Persons(personList);

        JAXBContext jaxbContext = JAXBContext.newInstance(Persons.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
        marshaller.marshal(persons, new File("ExportedData.xml"));

    }

    private boolean checkFieldsPerson(){
        idText = textFieldPersonID.getText();
        firstName = textFieldPersonFirstName.getText();
        lastName = textFieldPersonLastName.getText();
        salaryText = textFieldPersonSalary.getText();
        someText = textFieldPersonSomeField.getText();

        if(idText.equals("") || firstName.equals("") || lastName.equals("") || salaryText.equals("") || someText.equals("")) {
            labelError.setText("Błąd!");
            return false;
        }
        try{
            idInt = Integer.parseInt(idText);
            salaryInt = Integer.parseInt(salaryText);
        } catch(Exception e){
            labelError.setText("Błąd!");
            return false;
        }
        return true;
    }

    private boolean checkFieldsAnimal(){
        idAnimalText = textFieldAnimalID.getText();
        name = textFieldAnimalName.getText();
        ownerIDText = textFieldAnimalOwnerID.getText();

        if(idAnimalText.equals("") || name.equals("") || ownerIDText.equals("")) {
            labelError.setText("Błąd!");
            return false;
        }
        try{
            idAnimalInt = Integer.parseInt(idAnimalText);
            ownerIDInt = Integer.parseInt(ownerIDText);
        } catch(Exception e){
            labelError.setText("Błąd!");
            return false;
        }
        return true;
    }

}