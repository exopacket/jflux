package com.inteliense.jflux.threading.types;


import com.inteliense.jflux.threading.base.DetachesThread;

public abstract class DetachedThread extends DetachesThread {

    protected abstract boolean execute();

    private boolean active = false;

    protected void onStart() {  }

    protected void onStop() { }

    public DetachedThread() { }

    public DetachedThread(int idleMiilis) {
        this.idleMillis = idleMiilis;
    }

    @Override
    public void start() {
        thread = new Thread(() -> {
            active = true;
            onStart();
            boolean keepExecuting = true;
            while(keepExecuting && !Thread.currentThread().isInterrupted()) {
                keepExecuting = execute();
                if(idleMillis > 0) {
                    try {
                        Thread.sleep(idleMillis);
                    } catch (InterruptedException e) { break; }
                }
            }
            onStop();
            active = false;
        });
        thread.start();
    }

    @Override
    public void stop() {
        if(active) thread.interrupt();
    }

}
