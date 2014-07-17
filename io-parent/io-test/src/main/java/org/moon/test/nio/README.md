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





