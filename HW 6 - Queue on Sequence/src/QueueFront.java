import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 * @param <T>
 */
public final class QueueFront<T> extends Queue3<T> {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private QueueFront() {
        super();
    }

    /**
     * Reports the front of {@code this}.
     *
     * @return the front entry of {@code this}
     * @aliases reference returned by {@code front}
     * @requires this /= <>
     * @ensures <front> is prefix of this
     */
    @Override
    public T front() {
        assert this.length() > 0 : "Violation of: this /= <>";
        Queue<T> tmp = new Queue3<>();
        T front = this.dequeue();
        tmp.enqueue(front);
        /**
         * Only Queue1L and Queue2 extend QueueKernel and Standard. Queue3
         * doesn't hence error. Queue1L and Queue2 cannot be used because of
         * declaration of front method as final.
         **/
        while (this.length() != 0) {
            T remove = this.dequeue();
            tmp.enqueue(remove);
        }
        this.transferFrom(tmp);
        return front;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        Queue<Integer> q = new Queue3<>();
        q.enqueue(1);
        q.enqueue(2);
        out.println(q.front());
        out.println(q);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
