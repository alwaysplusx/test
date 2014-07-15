## Java Collections Framework

## java.util.List

#### java.util.ArrayList

ArrayList采用数组实现对象存储。
	
	private transient Object[] elementData;

当添加对象时达到数组容量上限时候将进行扩充，扩充为原数组的1.5倍(old*3)/2+1。原数组中的对象经过拷贝进入扩充后的数组中。
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

删除对象时候，如果删除不是数组index最大的对象，那么每删除一个对象都要经过异常数组拷贝将原数据拷贝的新数组中。
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
> 根据ArraryList的实现，可以发现。ArrayList查询速度快，对于删除，扩容等都需要进行数组的拷贝才能完成。因此使用时候应该避免对ArrayList进行频繁的删除，扩容。

#### java.util.LinkedList

LinkedList采用链表实现对象存储

当添加对象时，创建一个新对象添加到链表头部的下一个。
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

## java.util.Set

#### java.util.HashSet

#### java.util.TreeSet




