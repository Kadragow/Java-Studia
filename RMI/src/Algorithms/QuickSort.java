package Algorithms;

import java.util.ArrayList;
import java.util.List;
/**
 * Klasa algorytmu sortujacego, korzysta z Quick sort
 *
 * @author Kamil Blanik
 * @since 02-03-2020
 */
public class QuickSort extends AbstractFloatSorter {

    /**
     * Metoda glowna szybkiego sortowania
     * @param a {@code List<IElement>} lista do posortowania
     * @param index {@code int[]} Indeksy do posortownia
     * @param left {@code int} Lewy indeks granicy
     * @param right {@code int} Prawy indeks granicy
     */
    private void quicksort(List<IElement> a, int[] index, int left, int right) {
        if (right <= left) {
            return;
        }
        int i = partition(a, index, left, right);
        quicksort(a, index, left, i - 1);
        quicksort(a, index, i + 1, right);
    }

    /**
     * Metoda szukajaca elementow do zamiany
     * @param a {@code List<IElement>} lista do posortowania
     * @param index {@code int[]} Indeksy do posortownia
     * @param left {@code int} Lewy indeks granicy
     * @param right {@code int} Prawy indeks granicy
     * @return {@code int} Nowy indeks granicy
     */
    private int partition(List<IElement> a, int[] index, int left, int right) {
        int i = left - 1;
        int j = right;
        while (true) {
            while (a.get(index[++i]).getValue() < a.get(index[right]).getValue())
                ;
            while (a.get(index[right]).getValue() < a.get(index[--j]).getValue()) {
                if (j == left) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }

            swap(index, i, j);
        }
        swap(index, i, right);
        return i;
    }

    /**
     * Metoda zamieniajaca wartosci
     * @param index zbior indeksow
     * @param i indeks do zamiany
     * @param j indeks do zamiany
     */
    private void swap(int[] index, int i, int j) {
        int tmp = index[i];
        index[i] = index[j];
        index[j] = tmp;
    }
    /**
     * Metoda zwracajaca posortowana liste podana w argumencie
     * Sortowanie przy pomocy Quick sort
     *
     * @param listToSolve {@code List<IElement>} Lista do posortowania
     * @return {@code List<IElement>} Posortowana lista
     */
    @Override
    public List<IElement> solveAll(List<IElement> listToSolve) {
        int[] index = new int[listToSolve.size()];
        for (int i = 0; i < listToSolve.size(); i++)
            index[i] = i;
        quicksort(listToSolve,index,0,listToSolve.size()-1);
        List<IElement> sortedList = new ArrayList<>();
        for(int i : index){
            sortedList.add(listToSolve.get(i));
        }
        return sortedList;
    }

    @Override
    public List<IntElement> solve(List<IntElement> listToSolve) {
        return null;
    }

    @Override
    public String description() {
        return "Quick sort";
    }

    @Override
    public Boolean isStable() {
        return false;
    }

    @Override
    public Boolean isInSitu() {
        return true;
    }
}
