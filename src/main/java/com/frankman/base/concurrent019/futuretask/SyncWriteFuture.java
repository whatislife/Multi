package com.frankman.base.concurrent019.futuretask;




import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SyncWriteFuture  implements WriteFuture<AsyncResponse>  {

    private CountDownLatch latch = new CountDownLatch(1);
    private final long begin = System.currentTimeMillis();
    private long timeout;
    private AsyncResponse response;
    private final String requestId;
    private boolean writeResult;
    private Throwable cause;
    private boolean isTimeout = false;

    public SyncWriteFuture(String requestId) {
        this.requestId = requestId;
    }

    public SyncWriteFuture(String requestId, long timeout) {
        this.requestId = requestId;
        this.timeout = timeout;
        writeResult = true;
        isTimeout = false;
    }

   @Override
    public Throwable cause() {
        return cause;
    }
   @Override
    public void setCause(Throwable cause) {
        this.cause = cause;
    }
   @Override
    public boolean isWriteSuccess() {
        return writeResult;
    }
   @Override
    public void setWriteResult(boolean result) {
        this.writeResult = result;
    }
   @Override
    public String requestId() {
        return requestId;
    }
   @Override
    public AsyncResponse response() {
        return response;
    }
   @Override
    public void setResponse(AsyncResponse response) {
        this.response = response;
        latch.countDown();
    }
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return true;
    }
    @Override
    public boolean isCancelled() {
        return false;
    }
    @Override
    public boolean isDone() {
        return false;
    }
    @Override
    public AsyncResponse get() throws InterruptedException, ExecutionException {
        latch.await();
        return response;
    }
    @Override
    public AsyncResponse get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (latch.await(timeout, unit)) {
            return response;
        }
        return null;
    }
    @Override
    public boolean isTimeout() {
        if (isTimeout) {
            return isTimeout;
        }
        return System.currentTimeMillis() - begin > timeout;
    }
}
