package org.moon.ii.concurrent;

import java.util.concurrent.Future;

public interface ThreadPool {

    void execute(Runnable command);

    <T> Future<T> submit(Runnable task, T result);

    void shutdown();
}
