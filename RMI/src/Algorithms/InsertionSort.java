package Algorithms;

import java.util.List;
/**
 * Klasa algorytmu sortujacego, korzysta z Insertion sort
 *
 * @author Kamil Blanik
 * @since 02-03-2020
 */
public class InsertionSort extends AbstractFloatSorter {
    /**
     * Metoda zwracajaca posortowana liste podana w argumencie
     * Sortowanie przy pomocy Insertion sort
     *
     * @param listToSolve {@code List<IElement>} Lista do posortowania
     * @return {@code List<IElement>} Posortowana lista
     */
    @Override
    public List<IElement> solveAll(List<IElement> listToSolve) {
        int n = listToSolve.size();
        for (int i = 1; i < n; ++i) {
            IElement key = listToSolve.get(i);
            int j = i - 1;
            while (j >= 0 && listToSolve.get(j).getValue() > key.getValue()) {
                listToSolve.set(j + 1, listToSolve.get(j));
                j = j - 1;
            }
            listToSolve.set(j + 1, key);
        }
        return listToSolve;
    }

    @Override
    public List<IntElement> solve(List<IntElement> listToSolve) {
        return null;
    }

    @Override
    public String description() {
        return "Insertion sort";
    }

    @Override
    public Boolean isStable() {
        return true;
    }

    @Override
    public Boolean isInSitu() {
        return true;
    }
}
