package Algorithms;

import java.io.Serializable;

/**
 * Klasa elementu typu float przeznaczonego do sortowania
 *
 * @author Kamil Blanik
 * @since 02-03-2020
 */
public class FloatElement implements IElement, Comparable<FloatElement>, Serializable {
    /**
     * Parametry elementu
     */
    private float value;
    private String name;

    /**
     * Konstruktor jednoparametrowy, przyjmuje wartosc elementu jako parametr
     *
     * @param value parametr {@code float} okresla wartosc przedmiotu
     */
    public FloatElement(float value) {
        this.value = value;
        this.name = "FloatElement";
    }

    /**
     * Konstruktor z dwoma parametrami
     *
     * @param value parametr {@code float} okresla wartosc przedmiotu
     * @param name parametr {@code String} okresla nazwe przedmiotu
     */
    public FloatElement(float value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Konstruktor kopiujacy
     *
     * @param element {@code IElement} Element do skopiowania
     */
    public FloatElement(IElement element){
        this.value = element.getValue();
        this.name = element.getName();
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
     * @return {@code float} Wartosc elementu
     */
    @Override
    public float getValue() {
        return this.value;
    }

    /**
     * Metoda porownujaca wartosci dwoch elementow
     *
     * @param o {@code FloatElement} porownywany element
     * @return {@code int} 0 - elementy takie same, !=0 - elementy rozne
     */
    @Override
    public int compareTo(FloatElement o) {
        return Float.compare(this.value, o.getValue());
    }
}
