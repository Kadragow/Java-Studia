package Algorithms;

/**
 * Interfejs elementow przeznaczonych do sortowania
 *
 * @author Kamil Blanik
 * @since 02-03-2020
 */
public interface IElement {
    /**
     * Metoda zwracajaca nazwe elementu
     *
     * @return Nazwa elementu
     */
    String getName();

    /**
     * Metoda zwracajaca wartosc elementu
     *
     * @return Wartosc elementu
     */
    float getValue();
}
