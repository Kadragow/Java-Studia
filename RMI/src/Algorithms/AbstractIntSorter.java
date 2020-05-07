package Algorithms;

import java.util.List;

/**
 * Klasa abstrakcyjna algorytmu sortujacego elementy typu IntElement
 *
 * @author Kamil Blanik
 * @since 02-03-2020
 */
public abstract class AbstractIntSorter {
    /**
     * Metoda zwracajaca posortowana liste podana w argumencie
     *
     * @param listToSolve {@code List<IntElement>} Lista do posortowania
     * @return {@code List<IntElement>} Posortowana lista
     */
    public abstract List<IntElement> solve(List<IntElement> listToSolve);

    /**
     * Metoda zwracajaca opis algorytmu
     *
     * @return {@code String} Opis algorytmu
     */
    public abstract String description();

    /**
     * Metoda zwracajaca czy algorytm jest stabilny
     *
     * @return {@code Boolean} Czy jest stabilny
     */
    public abstract Boolean isStable();

    /**
     * Metoda zwracajaca czy algorytm jest w miejscu
     *
     * @return {@code Boolean} Czy jest w miejscu
     */
    public abstract Boolean isInSitu();

}
