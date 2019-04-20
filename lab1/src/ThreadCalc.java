public class ThreadCalc extends Thread{
    private int result;
    private static Integer[][] array;
    private int size;
    private int startIndex;
    private int endIndex;

    public static void setArray(Integer[][] array1){
        array = array1;
    }

    public ThreadCalc( int startIndex, int endIndex) {
        this.size = array.length;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        for (int i = startIndex; i < endIndex; i++) {
            result += array[i % size][i / size];
        }
    }

    public int getResult(){
        return result;
    }
}
