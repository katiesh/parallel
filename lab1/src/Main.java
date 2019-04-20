import sun.security.krb5.internal.TGSRep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int n = 1000;
        int m = 1000;
        int size = 4;
        Integer[][] array = new Integer[n][m];
        int result = 0;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j <m ; j++) {
                array[i][j] = (int)(Math.random()*100);
            }
        }
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j <m ; j++) {
                result+=array[i][j];
            }
        }
        System.out.println("Sequence counting: " + result + " Time: " + (System.currentTimeMillis() - time1));
        int numOfElementsForOneThread = n*m/size;
        ThreadCalc[] threadArray = new ThreadCalc[size];
        ThreadCalc.setArray(array);
        long time2 = System.currentTimeMillis();
        for (int i = 0; i <size ; i++) {
            threadArray[i] = new ThreadCalc(i*numOfElementsForOneThread,
                    i==(size-1)?n*m:(numOfElementsForOneThread)*(i+1));
            threadArray[i].start();
        }
        for (int i = 0; i <size ; i++) {
            threadArray[i].join();
        }
        int parallelResult = 0;
        for (int i = 0; i < size; i++) {
            parallelResult+=threadArray[i].getResult();
        }
        long parallelTime = System.currentTimeMillis() - time2;
        System.out.println("Parallel counting: " + parallelResult + " Time: " + parallelTime);
    }
}
