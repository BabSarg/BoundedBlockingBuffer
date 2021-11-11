package buffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingBuffer<T> {

    private T data;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void putData(T d) throws InterruptedException {
        lock.lock();
        if(data != null){
            condition.await();
        }else {
            data = d;
            condition.signalAll();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        if (data == null){
            condition.await();
            return null;
        }else {
            T temp = data;
            data = null;
            condition.signalAll();
            return temp;
        }
    }
}
