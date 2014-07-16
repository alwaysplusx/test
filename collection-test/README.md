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

	`transient Entry[] table;`
	
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


