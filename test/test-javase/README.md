### Buffer
Buffer中有三个关键状态变量

> 可将Buffer看作一个数组

<ul>

<li>position:它指定了下一个字节将放到数组的哪一个元素中</li>

<li>limit:表明还有多少数据需要取出,或者还有多少空间可以放入数据</li>

<li>capacity:表示可以储存在缓冲区中的最大数据容量</li>

</ul>

几个需要了解的方法,以及关键变量的变化[缓冲区内部细节](http://www.ibm.com/developerworks/cn/education/java/j-nio/index.html)

<ul>

<li>buffer.clear(),将limit设置为与capacity相同,设置position为0

<li>buffer.flip(),调用该方法时候做了两件事主要的事为,将limit设置为当前position;将position设置为0

</ul>

缓冲区分配,包装,分片

分配一个缓冲区,Buffer.allocation(1024),ByteBuffer.allocateDirect(1024)

将一个数组包装成一个缓冲区,Buffer.wrap(new byte[1024])

buffer.slice(),从缓冲区分出的子缓冲区共享同一个底层数据数组

	{
		buffer.position(3);
		buffer.limit(7);
		//分出一个由3-6的自缓冲片
		buffer.slice();
	}
	
MappedByteBuffer 内存映射文件

将文件映射到内存中

	{
		RandomAccessFile raf = new RandomAccessFile( "xx.txt", "rw" );
		FileChannel fc = raf.getChannel();
		//将文件的一部分映射到内存中
    	MappedByteBuffer mbb = fc.map( FileChannel.MapMode.READ_WRITE, start, size );
	}

FileLock

	{
		//mode rw
		RandomAccessFile raf = new RandomAccessFile( "usefilelocks.txt", "rw" );
		FileChannel fc = raf.getChannel();
		FileLock lock = fc.lock( position, size, shared);	
		
		lock.release();
	}
	
异步 I/O

异步 I/O 是一种 没有阻塞地 读写数据的方法。通常，在代码进行 read() 调用时，代码会阻塞直至有可供读取的数据。同样， write() 调用将会阻塞直至数据能够写入。

另一方面，异步 I/O 调用不会阻塞。相反，您将注册对特定 I/O 事件的兴趣 ― 可读的数据的到达、新的套接字连接，等等，而在发生这样的事件时，系统将会告诉您。

异步 I/O 的一个优势在于，它允许您同时根据大量的输入和输出执行 I/O。同步程序常常要求助于轮询，或者创建许许多多的线程以处理大量的连接。使用异步 I/O，您可以监听任何数量的通道上的事件，不用轮询，也不用额外的线程。	

### Remote Method Invocation

早在Java 1.1时期Remote Method Invocation(RMI)就已近存在

Java简单RMI实现
<ul>
<li>接口继承自`java.rmi.Remote`
<li>子类继承自`java.rmi.server.UnicastRemoteObject`
<li>接口中的方法必须都声明抛出异常`java.rmi.RemoteException`
</ul>

发布RMI服务
```java
{
  UserService service = new SimpleUserService();
  LocateRegistry.createRegistry(9696);
  Naming.rebind(address, service);
}
```

客户端调用
```java
{
  UserService service = (UserService) Naming.lookup(address);
}
```

> 值得注意的是，当服务端直接返回一个客户端对象时候，将会重新创建一个客户端的哈希值


修改安全策略：${JAVA_HOME}/jre/lib/security/java.policy

    grant{
          permission java.security.AllPermission "","";
    };

## Java Collections Framework

## java.util.List

#### java.util.ArrayList

ArrayList采用数组实现对象存储。
```java	
private transient Object[] elementData;
```
当达到数组容量上限时候将进行扩充，扩充为原数组的1.5倍(oldCapacity*3)/2+1。原数组中的对象经过拷贝进入扩充后的新数组中。
```java
public void ensureCapacity(int minCapacity) {
	modCount++;
	int oldCapacity = elementData.length;
	if (minCapacity > oldCapacity) {
		Object oldData[] = elementData;
		int newCapacity = (oldCapacity * 3) / 2 + 1;
		if (newCapacity < minCapacity)
			newCapacity = minCapacity;
		// minCapacity is usually close to size, so this is a win:
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
}
```
删除对象时候，如果删除不是数组index最大的对象，每删除一个对象，都要把原数组拷贝到新数组中。
```java
public E remove(int index) {
	RangeCheck(index);
	modCount++;
	E oldValue = (E) elementData[index];
	//如果是最后一个对象则小于0
	int numMoved = size - index - 1;
	if (numMoved > 0)
		System.arraycopy(elementData, index + 1, elementData, index, numMoved);
	elementData[--size] = null; // Let gc do its work
	return oldValue;
}
```
> 根据ArraryList的实现，可以发现。ArrayList查询速度快，对于删除，扩容等都需要进行数组的拷贝。因此使用时候应该避免对ArrayList进行频繁的删除，扩容。

#### java.util.LinkedList

LinkedList采用链表实现对象存储。
```java
private static class Entry<E> {
	E element;
	Entry<E> next;
	Entry<E> previous;
}
```
添加对象时，创建一个新对象添加到链表头部的下一个。
```java
private Entry<E> addBefore(E e, Entry<E> entry) {
	Entry<E> newEntry = new Entry<E>(e, entry, entry.previous);
	newEntry.previous.next = newEntry;
	newEntry.next.previous = newEntry;
	size++;
	modCount++;
	return newEntry;
}
```
删除时候也不必对整个链表进行拷贝，只需要将对象的链进行重新连接然后移除待删除的对象即可。
```java
private E remove(Entry<E> e) {
	if (e == header)
		throw new NoSuchElementException();
	E result = e.element;
	e.previous.next = e.next;
	e.next.previous = e.previous;
	e.next = e.previous = null;
	e.element = null;
	size--;
	modCount++;
	return result;
}
```

> 根据LinkedList的特性，可以发现。LinkedList删除，扩充简单对内存消耗小。但是查询速度不如ArrayList。

## java.util.Map

以Key=Value结构来保存数据

#### java.util.HashMap

HashMap键值对的结构如下代码
```java
static class Entry<K, V> implements Map.Entry<K, V> {
	final K key;
	V value;
	Entry<K, V> next;
	final int hash;
}
```
HashMap中保存了一个该Entry的数组
```java
transient Entry[] table;
```	
添加一个`K-V`时候，将会生成K的hash值，在根据这个hash值来确定K在数组table中的index，如果`table[index]! = null`则比较双方的`hashCode`与`equals`方法的返回值确定是否相同对象。如果结果为`false`则继续与`Entry.next`比较。如果发现当前K的与某一K相同则替换。
```java
public V put(K key, V value) {
	if (key == null)
		return putForNullKey(value);
	int hash = hash(key.hashCode());
	int i = indexFor(hash, table.length);
	for (Entry<K, V> e = table[i]; e != null; e = e.next) {
		Object k;
		if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
			V oldValue = e.value;
			e.value = value;
			e.recordAccess(this);
			return oldValue;
		}
	}
	modCount++;
	addEntry(hash, key, value, i);
	return null;
}

void addEntry(int hash, K key, V value, int bucketIndex) {
	Entry<K, V> e = table[bucketIndex];
	table[bucketIndex] = new Entry<K, V>(hash, key, value, e);
	if (size++ >= threshold)
		resize(2 * table.length);
}
```

> 由于同样是用数组来存储对象，HashMap达到上限时候也要进行扩容处理。

#### java.util.LinkedHashMap

以链式方式存储

#### java.util.TreeMap

二叉链表方式存储，K必须实现`java.lang.Comparable`

## java.util.Set

#### java.util.HashSet

HashSet依赖HashMap实现hash排序，允许插入空值

#### java.util.TreeSet

TreeSet依赖TreeMap实现排序，不允许插入空值

### Rxtx for Java
* java-simple-serial-connector(jssc)
https://github.com/scream3r/java-simple-serial-connector.git



