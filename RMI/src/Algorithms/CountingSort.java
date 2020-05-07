package Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Klasa algorytmu sortujacego, korzysta z Counting sort
 *
 * @author Kamil Blanik
 * @since 02-03-2020
 */
public class CountingSort extends AbstractIntSorter {
    /**
     * Metoda zwracajaca posortowana liste podana w argumencie
     * Sortowanie przy pomocy Counting sort
     *
     * @param listToSolve {@code List<IntElement>} Lista do posortowania
     * @return {@code List<IntElement>} Posortowana lista
     */
    @Override
    public List<IntElement> solve(List<IntElement> listToSolve) {
        List<IntElement> outList = new ArrayList<>();
        for(int i = 0; i< listToSolve.size(); i++){
            outList.add(new IntElement(0));
        }
        int max = (int)Collections.max(listToSolve).getValue();
        int min = (int)Collections.min(listToSolve).getValue();
        int range = max - min + 1;
        int[] count = new int[range];
        for (IntElement element : listToSolve) {
            count[(int) element.getValue() - min]++;
        }

        for (int i = 1; i< count.length; i++){
            count[i] += count[i - 1];
        }

        for (int i = listToSolve.size() - 1; i >=0; i--){
            outList.set(count[(int)listToSolve.get(i).getValue() - min] - 1, new IntElement(listToSolve.get(i)));
            count[(int)listToSolve.get(i).getValue() - min]--;
        }

        return outList;
    }

    @Override
    public String description() {
        return "Counting sort";
    }

    @Override
    public Boolean isStable() {
        return true;
    }

    @Override
    public Boolean isInSitu() {
        return false;
    }
}
