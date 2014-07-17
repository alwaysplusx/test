### ReadWriteLock
```java
static class Factory {

	List<String> data = new LinkedList<String>();
	// private Queue<String> queue = new ArrayBlockingQueue<String>(10);
	private ReadWriteLock lock = new ReentrantReadWriteLock();

	public String consume() {
		lock.writeLock().lock();
		String result = null;
		try {
			if (!data.isEmpty())
				result = data.remove(0);
			log.info("consume " + result);
			return result;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void produce(String message) {
		lock.writeLock().lock();
		try {
			Thread.sleep(1000);
			data.add(message);
			log.info("produce " + message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
	}
}
```