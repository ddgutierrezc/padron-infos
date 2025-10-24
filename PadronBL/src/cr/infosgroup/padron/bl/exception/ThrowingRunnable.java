package cr.infosgroup.padron.bl.exception;

@FunctionalInterface
public interface ThrowingRunnable {
    void run() throws Exception;
}
