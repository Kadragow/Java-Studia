package lab;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "persons")
@XmlAccessorType (XmlAccessType.FIELD)
public class Persons {
    @XmlElement(name = "person")
    private List<Person> persons;

    public Persons(){
        persons = new ArrayList<>();
    }

    public Persons(List<Person> persons){
        this.persons = persons;
    }

    public List<Person> getPersons(){
        return persons;
    }

    public void setPersons(List<Person> persons){
        this.persons = persons;
    }

}
