import java.util.Arrays;

public class ValueCalculator {
    private float[] numbers;
    private int size;
    private int halfSize;

    public ValueCalculator(int size) {
        this.size = size;
        halfSize = size / 2;
    }

    public void method() {
        long start = System.currentTimeMillis();
        numbers = new float[size];
        Arrays.fill(numbers, 8);
        float[] num1 = new float[halfSize];
        float[] num2 = new float[halfSize];
        System.arraycopy(numbers, 0, num1, 0, halfSize);
        System.arraycopy(numbers, halfSize, num2, 0, halfSize);
        Runnable t1 = () -> {
            for (int i = 0; i < num1.length; i++) {
                num1[i] = (float) (num1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        };
        Runnable t2 = () -> {
            for (int i = 0; i < num2.length; i++) {
                num2[i] = (float) (num2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        };
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.arraycopy(num1, 0, numbers, 0, halfSize);
        System.arraycopy(num1, 0, numbers, halfSize, halfSize);
        System.out.println("process completed in " + (System.currentTimeMillis() - start) + " milliseconds.");
    }
}
