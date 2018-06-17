# socket
* socket是什么
> socket是一种操作系统提供的进程间通信机制。在操作系统中，通常会为应用程序提供一组应用程序接口（API），称为套接字接口（英语：socket API）。应用程序可以通过套接字接口，来使用网络套接字，以进行数据交换。在套接字接口中，以IP地址及通信端口组成套接字地址（socket address）。远程的套接字地址，以及本地的套接字地址完成连接后，再加上使用的协议（protocol），这个五元组（five-element tuple），作为套接字对（socket pairs），之后就可以彼此交换数据。例如，在同一台计算机上，TCP协议与UDP协议可以同时使用相同的port而互不干扰。 操作系统根据套接字地址，可以决定应该将数据送达特定的进程或线程。这就像是电话系统中，以电话号码加上分机号码，来决定通话对象一般。[维基百科](https://zh.wikipedia.org/wiki/%E7%B6%B2%E8%B7%AF%E6%8F%92%E5%BA%A7)

* socket是如何工作的

![](https://github.com/moxingwang/collection/blob/master/resources/image/socket_abstract.jpg?raw=true)

> Socket是应用层与TCP/IP协议族通信的中间软件抽象层,它是一组接口.在设计模式中,Socket其实就是一个门面模式,它把复杂的TCP/IP协议族隐藏在Socket接口后面,对用户来说,一组简单的接口就是全部,让Socket去组织数据,以符合指定的协议。[图解socket原理](https://blog.csdn.net/ce123_zhouwei/article/details/8918959)

* 总结
> 简单的说socket是一种操作系统进程通信机制，对网际协议抽象，对因用层提供接口调用。

# 同步，异步，阻塞，非阻塞概念
## 同步和异步
> 同步和异步描述的调用结果。

* 同步
> 所谓同步，就是在发出一个功能调用时，在没有得到结果之前，该调用就不返回。也就是必须一件一件事做,等前一件做完了才能做下一件事。例如普通B/S模式（同步）：提交请求->等待服务器处理->处理完毕返回 这个期间客户端浏览器不能干任何事。

* 异步
> 异步的概念和同步相对。当一个异步过程调用发出后，调用者不能立刻得到结果。实际处理这个调用的部件在完成后，通过状态、通知和回调来通知调用者。例如 ajax请求（异步）: 请求通过事件触发->服务器处理（这是浏览器仍然可以作其他事情）->处理完毕。
 
## 阻塞和非阻塞
> 阻塞和非阻塞的主题是线程。

* 阻塞
> 阻塞调用是指调用结果返回之前，当前线程会被挂起（线程进入非可执行状态，在这个状态下，cpu不会给线程分配时间片，即线程暂停运行）。函数只有在得到结果之后才会返回。有人也许会把阻塞调用和同步调用等同起来，实际上他是不同的。对于同步调用来说，很多时候当前线程还是激活的，只是从逻辑上当前函数没有返回,它还会抢占cpu去执行其他逻辑，也会主动检测io是否准备好。

* 非阻塞
> 非阻塞和阻塞的概念相对应，指在不能立刻得到结果之前，该函数不会阻塞当前线程，而会立刻返回。


# IO
> 所谓IO阻塞或者非阻塞都是指在IO操作（读或者写的时候）调用线程或者进程的阻塞和非阻塞。
## Linux中的五中IO模型
* 阻塞I/O（blocking I/O）
> 调用线程一直等待读或者写函数的返回。
* 非阻塞I/O （nonblocking I/O）
> 调用函数后直接返回，线程定时检测读写是否完成。
* I/O复用(select 和poll) （I/O multiplexing）
> 多路复用是指使用一个线程来检查多个文件描述符（Socket）的就绪状态，比如调用select和poll函数，传入多个文件描述符，如果有一个文件描述符就绪，则返回，否则阻塞直到超时。得到就绪状态后进行真正的操作可以在同一个线程里执行，也可以启动线程执行（比如使用线程池）。
* 信号驱动I/O （signal driven I/O (SIGIO)）
> 信号驱动 IO 的方式是发送 SIGIO 信号来通知某个 fd 是可读或是可写的。 fd 的阻塞等待或是轮询都让系统来进行托管，而不是在用户态处理。
* 异步I/O （asynchronous I/O (the POSIX aio_functions)）
> 调用函数直接返回继续执行，读写结果其它方式通知。

# JAVA 网络IO
* bio
> 同步阻塞。
* aio
> 异步非阻塞。
* nio
> 同步非阻塞。

# NIO

# 提高服务器性能的方法
* 榨干CUP、内存、物理硬件
* 使用少的线程饱和的工作，减少等待，干有用功


# 学习资料
* [如何学习Java的NIO？](https://www.zhihu.com/question/29005375)
* [基于NIO的长连接的实现已开放到github](https://blog.csdn.net/yangbutao/article/details/18505831)
* [Java 中 BIO、NIO、AIO 模型的对比](http://patchouli-know.com/2017/03/18/java-bio-nio-aio/)
* [julyerr Blog java AIO]（http://julyerr.club/2018/02/22/aio/）
* [julyerr Blog java NIO](http://julyerr.club/2018/02/20/nio/)
* [BIO与NIO、AIO的区别(这个容易理解)](https://blog.csdn.net/skiof007/article/details/52873421)
* [慕课网 Netty入门之WebSocket初体验](https://www.imooc.com/search/?words=netty)
* [关于BIO和NIO的理解](https://www.cnblogs.com/zedosu/p/6666984.html)
* [TCP/IP、Http、Socket的关系理解](https://blog.csdn.net/qq_35181209/article/details/75212533)
* [图解socket原理](https://blog.csdn.net/ce123_zhouwei/article/details/8918959)
* [简述linux同步与异步、阻塞与非阻塞概念以及五种IO模型](https://www.cnblogs.com/chaser24/p/6112071.html)