package buffer;

public class BoundedBlockingBuffer<T> {

    private T data;


    public synchronized void putData(T d) throws InterruptedException {
        while (data != null) {
            wait();
        }
        data = d;
        notifyAll();
    }

    public synchronized T take() throws InterruptedException {
        while (data == null) {
            wait();
        }
        T temp = data;
        data = null;
        notifyAll();
        return temp;
    }

}
