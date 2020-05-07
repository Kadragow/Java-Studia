package Algorithms;

/**
 * Klasa elementu typu int przeznaczonego do sortowania
 *
 * @author Kamil Blanik
 * @since 02-03-2020
 */
public class IntElement implements IElement, Comparable<IntElement>{
    /**
     * Parametry elementu
     */
    private int value;
    private String name;

    /**
     * Konstruktor z jednym parametrem
     *
     * @param value parametr {@code int} Nadaje wartosc elementu
     */
    public IntElement(int value) {
        this.value = value;
        this.name = "IntElement";
    }

    /**
     * Konstruktor kopiujacy
     *
     * @param element {@code IntElement} Element do skopiowania
     */
    public IntElement(IntElement element){
        this.value = (int)element.getValue();
        this.name = element.getName();
    }

    /**
     * Konstruktor z dwoma parametrami
     *
     * @param value {@code int} Wartosc elementu
     * @param name {@code String} Nazwa elementu
     */
    public IntElement(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Metoda zwracajaca nazwe elementu
     *
     * @return {@code String} Nazwa elementu
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Metoda zwracajaca wartosc elementu
     *
     * @return {@code int} Wartosc elementu
     */
    @Override
    public float getValue() {
        return this.value;
    }

    /**
     * Metoda porownujaca wartosci dwoch elementow
     *
     * @param o {@code IntElement} porownywany element
     * @return {@code int} 0 - elementy takie same, !=0 - elementy rozne
     */
    @Override
    public int compareTo(IntElement o) {
        return Integer.compare((int)this.getValue(), (int)o.getValue());
    }
}
