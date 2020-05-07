package Algorithms;

import java.util.List;

/**
 * Klasa abstrakcyjna algorytmu sortujacego elementy typu IElement
 *
 * @author Kamil Blanik
 * @since 02-03-2020
 */
public abstract class AbstractFloatSorter extends AbstractIntSorter{
    /**
     * Metoda zwracajaca posortowana liste podana w argumencie
     *
     * @param listToSolve {@code List<IElement>} Lista do posortowania
     * @return {@code List<IElement>} Posortowana lista
     */
    public abstract List<IElement> solveAll(List<IElement> listToSolve);

}
