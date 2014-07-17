package org.moon.ii.concurrent;

import org.moon.ii.concurrent.impl.SimpleThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleThreadPoolTest {
	
	static Logger log = LoggerFactory.getLogger(Task.class);
	
	public static void main(String[] args) throws Exception {
		SimpleThreadPool threadPool = new SimpleThreadPool(5, "self-turning-");
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				log.info("has run");
			}
		});
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
				log.info("has run");
			}
		});

		for (;;) {
			Thread.sleep(200);
			threadPool.offer(new Task());
		}
	}
}
class Task implements Runnable {

	static int loop = 0;

	static Logger log = LoggerFactory.getLogger(Task.class);

	@Override
	public void run() {
		log.info("has run " + (loop++));
	}

}


