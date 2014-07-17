package org.moon.test.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassisAndFixedRateTimerDiffTest {

	static Logger log = LoggerFactory.getLogger(ClassisAndFixedRateTimerDiffTest.class);

	public static void main(String[] args) {
		Timer t1 = new Timer();
		Timer t2 = new Timer();

		// 当同一个Timer运行两个定时任务时候貌似会出现任务定时不正确
		t1.schedule(new TimerTask() {
			Date start;
			Date time = new Date();
			int i = 0;

			@Override
			public void run() {
				Date now = new Date();
				if (i == 0) {
					start = now;
				}
				long sleepTime = ((int) (Math.random() * 4000));
				log.info(">>> task1   第" + ++i + "次运行，距上次运行 " + (now.getTime() - time.getTime()) + "ms, 该任务运行" + (now.getTime() - start.getTime()) + "ms, 当前任务将暂停" + sleepTime + "ms");
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				time = now;
			}
		}, 0, 1000 * 3);

		t2.scheduleAtFixedRate(new TimerTask() {
			Date time = new Date();
			Date start;
			int i = 0;

			@Override
			public void run() {
				Date now = new Date();
				if (i == 0) {
					start = now;
				}
				long sleepTime = ((int) (Math.random() * 4000));
				log.info("### task2   第" + ++i + "次运行，距上次运行 " + (now.getTime() - time.getTime()) + "ms, 该任务运行" + (now.getTime() - start.getTime()) + "ms, 当前任务将暂停" + sleepTime + "ms");
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				time = now;
			}
		}, 0, 1000 * 2);
	}
}
