import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

public class Main {
    private static long MAX = 10000;
    private static long MIN = 0;
    private boolean stop;
    private long seed;
    private int numberOfThreads;
    //private List<Object> elementsList;
    private Random random;
    private WeakHashMap<Long, Object> mapOfResults;
   // private HashMap<Long, Object> mapOfResults;
    private int[] resultList;
    private ArrayList<Thread> listOfThreads;
    private int elementsAmount;

    private File file;
    private URL[] urlsToLoadFrom;
    private URLClassLoader loader;
    private ArrayList<Class> classesAlgorithms;
    private Class classElement;
    private Class classAlgorithm;

    public Main() {
        stop = false;
        elementsAmount = 100;
        numberOfThreads = 30;
        setClassed();
        //generateElements(123);
        //generateElements(53423);
        //generateElements(12236513);
        //generateElements(123);
        //generateElements(12236513);
        runThreads();
    }

    private List<Object> generateElements(long seed) {
        List<Object> elementsList=new ArrayList<>();
        try {
            Random r = new Random(seed);
            for(int i = 0; i <elementsAmount; i++){
                int val = r.nextInt()%500;
                Object o = classElement.getConstructors()[0].newInstance(val,"Element: " + i);
                elementsList.add(o);
                //System.out.println(val);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return elementsList;
    }

    private void setClassed() {
        try {
            URL url = new URL("file:C:\\Users\\Kamil\\IdeaProjects\\java-1\\out\\production\\SortingAlgorithms\\");
            urlsToLoadFrom = new URL[]{url};
            loader = new URLClassLoader(urlsToLoadFrom);
            classesAlgorithms = new ArrayList<Class>();

            File dir = new File("C:\\Users\\Kamil\\IdeaProjects\\java-1\\out\\production\\SortingAlgorithms\\com\\company");
            File[] directoryListing = dir.listFiles();
            if (directoryListing != null) {
                for (File child : directoryListing) {
                    String childName = child.getName();
                    String className = childName.replace(".class", "");
                    String classPackage = "com.company.";
                    String fullClassName = classPackage.concat(className);
                    Class c = loader.loadClass(fullClassName);
                    Method[] methods = c.getMethods();

                    for (Method m : methods) {
                        if (m.getName().equals("solve") && !Modifier.isAbstract(c.getModifiers())) {
                            classesAlgorithms.add(c);
                            break;
                        }
                        if (m.getName().equals("getValue")) {
                            classElement = c;
                            break;
                        }
                    }
                }

                System.out.println("Załadowane klasy:");
                for(Class c : classesAlgorithms)
                    System.out.println(c.getName());
                if(classElement != null)
                    System.out.println(classElement.getName());
                if(classAlgorithm != null)
                    System.out.println(classAlgorithm.getName());
                System.out.println("\n\n\n");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void runThreads() {
//        final int[] algorithm = new int[numberOfThreads];
//        final boolean[] seedUsed = new boolean[numberOfThreads];

        listOfThreads = new ArrayList<>();
        mapOfResults = new WeakHashMap<>();
        //mapOfResults = new HashMap<>();
        resultList = new int[4];
        for (int i = 0; i<4; i++){
            resultList[i] = 0;
        }
        random = new Random();
        for (int i = 0; i < numberOfThreads; i++) {
            //final int helper = i;
            listOfThreads.add(new Thread(() -> {
                while (!stop) {
                    //System.gc();

                    long seed = (Math.abs(random.nextLong()) % MAX) + MIN + 1;
                    //System.out.println(seed);

                    //seedUsed[helper] = false;

                    synchronized (mapOfResults) {
                        resultList[0]++;
                        resultList[1]++;

                        if (mapOfResults.containsKey(seed)) {
                            //seedUsed[helper] = true;
                            //Udane odwołanie
                        } else {
                            try {
                                //algorithm[helper] = 0;
//                                WeakReference<Class> classWeakReference = new WeakReference<>(classesAlgorithms.get(0));
//                                WeakReference<Object> algorithm1 = new WeakReference<>(classWeakReference.get().getConstructors()[0].newInstance());
//                                WeakReference<Method> methodSolve = new WeakReference<>(classWeakReference.get().getMethod("solve",List.class));

                                WeakReference<Class> classWeakReference = new WeakReference<>(classesAlgorithms.get(2));
                                WeakReference<Object> algorithm1 = new WeakReference<>(classWeakReference.get().getConstructors()[0].newInstance());
                                WeakReference<Method> methodSolve = new WeakReference<>(classWeakReference.get().getMethod("solveAll",List.class));

//                                Class classWeakReference = classesAlgorithms.get(2);
//                                Object algorithm1 = classWeakReference.getConstructors()[0].newInstance();
//                                Method methodSolve = classWeakReference.getMethod("solveAll",List.class);

                                List<Object> listToSort = generateElements(seed);

                                if(methodSolve.get()!=null && algorithm1.get()!=null){
                                    List<Object> sortedList = (ArrayList<Object>) methodSolve.get().invoke(algorithm1.get(),listToSort);
                                    mapOfResults.put(seed, sortedList);
                                }

//                                if(methodSolve!=null && algorithm1!=null){
//                                    List<Object> sortedList = (ArrayList<Object>) methodSolve.invoke(algorithm1,listToSort);
//                                    mapOfResults.put(seed, sortedList);
//                                }

                            }catch(Exception e){
                                e.printStackTrace();
                            }
                            resultList[2]++;
                            resultList[3]++;
                        }
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        new Thread(() -> {
            for (Thread t : listOfThreads) {
                t.start();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (!stop) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (resultList) {
                    System.out.println("\n\n\n"
                            + "Liczba wszystkich odwołań: " + resultList[0] + "\n"
                            + "Liczba nieudanych odwołań: " + resultList[2] + "\n"
                            + "Liczba odwłań od ostatniego raportu: " + resultList[1] + "\n"
                            + "Liczba nieudanych odwołań od ostatniego raportu: " + resultList[3] + "\n"
                            + "Stosunek wszystkich odwołań: " + 1.0 * resultList[2]/resultList[0] + "\n"
                            + "Stosunek odwołań od ostatniego raportu: " + 1.0 * resultList[3]/resultList[1] + "\n"
                            + "Wielkość hashmapy: " + mapOfResults.size());
                    resultList[1]=0;
                    resultList[3]=0;
                }
            }
        }).start();
    }

    public static void main(String[] args){
        try {
            new Main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
