package Algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        FloatElement i9 = new FloatElement(2.1f, "2.1");
        FloatElement i10 = new FloatElement(5.5f, "5.5");
        Random r = new Random();
        List<IntElement> listInt = new ArrayList<>();
        for(int i = 0; i < 100; i++){

            IntElement newElement = new IntElement(r.nextInt()%100,Integer.toString(i));
            listInt.add(newElement);
        }

        List<IElement> listI = new ArrayList<>(listInt);
        listI.add(i9);
        listI.add(i10);
        List<IElement> listI2 = new ArrayList<>(listI);


        CountingSort cs = new CountingSort();
        QuickSort qs = new QuickSort();
        InsertionSort is = new InsertionSort();
        List<IntElement> sorted = cs.solve(listInt);
        List<IElement> sorted2 = qs.solveAll(listI);
        List<IElement> sorted3 = is.solveAll(listI2);

        System.out.println("\n----------------------------counting sort----------------------------");
        for (IntElement element :
                sorted) {
            System.out.println((int)element.getValue() + " : " + element.getName());
        }
        System.out.println("\n----------------------------quick sort----------------------------");
        for (IElement element :
                sorted2) {
            System.out.println(element.getValue() + " : " + element.getName());
        }
        System.out.println("\n----------------------------insertion sort----------------------------");
        for (IElement element :
                sorted3) {
            System.out.println(element.getValue() + " : " + element.getName());
        }

    }
}
