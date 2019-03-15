
public class MainThread extends Thread {
    public static void main(String[] args) {

        final int size = 10000000;
        float[] arr = new float[size];

        for (int i= 0; i < size; i++){
            arr[i] = 1;
        }

        System.out.println("Время работы в основном потоке " + PushArr(arr));
        System.out.println("Время работы в разных потоках " + PushArrThread(arr));

    }

    private static long PushArr(float[] arr) {
        long startTime = System.currentTimeMillis();

        for (int i= 0; i < arr.length; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
       // System.out.println(arr[43786]);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        return elapsedTime  ;
    }

    private static long PushArrThread(float[] arr) {

        long startTime = System.currentTimeMillis();

        final int h = arr.length / 2;

        float [] arr1 = new float[h];
        System.arraycopy(arr, 0, arr1, 0, h);

        float [] arr2 = new float[h];
        System.arraycopy(arr, h, arr2, 0, h);


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i= 0; i < arr1.length; i++){
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i= 0; i < arr2.length; i++){
                    arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        t1.start();
        t2.start();

//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

      //  System.out.println(arr[43786]);

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;

        return elapsedTime  ;

    }
}
