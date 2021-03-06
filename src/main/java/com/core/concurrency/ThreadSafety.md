### 有状态(Stateful):

有数据存储功能。有状态对象(Stateful Bean)，就是有实例变量的对象，可以保存数据，类里面有成员变量，而且成员变量是可变的,是非线程安全的。在不同方法调用间不保留任何状态。
### 无状态(Stateless):
一次操作，不能保存数据。无状态对象(Stateless Bean)，就是没有实例变量的对象.不能保存数据，类里面没有成员变量，或者有成员变量但是不可变的、或者成员变量是单例的,是不变类，是线程安全的。

### Spring中的有状态(Stateful)和无状态(Stateless)

(1). 通过上面的分析，相信大家已经对有状态和无状态有了一定的理解。无状态的Bean适合用不变模式，技术就是单例模式，这样可以共享实例，提高性能。有状态的Bean，多线程环境下不安全，那么适合用Prototype原型模式。Prototype: 每次对bean的请求都会创建一个新的bean实例。
(2). 默认情况下，从Spring bean工厂所取得的实例为singleton（scope属性为singleton）,容器只存在一个共享的bean实例。
(3). 理解了两者的关系，那么scope选择的原则就很容易了：有状态的bean都使用prototype作用域，而对无状态的bean则应该使用singleton作用域。
(4). 如Service层、Dao层用默认singleton就行，虽然Service类也有dao这样的属性，但dao这些类都是没有状态信息的，也就是相当于不变(immutable)类，所以不影响。Struts2中的Action因为会有User、BizEntity这样的实例对象，是有状态信息的，在多线程环境下是不安全的，所以Struts2默认的实现是Prototype模式。在Spring中，Struts2的Action中，scope要配成prototype作用域。

### Servlet、Struts中的有状态和无状态:
(1). Servlet体系结构是建立在Java多线程机制之上的，它的生命周期是由Web 容器负责的。一个Servlet类在Application中只有一个实例存在，也就是有多个线程在使用这个实例。这是单例模式的应用。无状态的单例是线程安全的，但我们如果在Servlet里用了实例变量，那么就变成有状态了，是非线程安全的。Out,Request,Response,Session,Config,Page,PageContext是线程安全的,Application在整个系统内被使用,所以不是线程安全的.
(2). Struts1也是基于单例模式实现，也就是只有一个Action实例供多线程使用。默认的模式是前台页面数据通过actionForm传入，在action中的excute方法接收，这样action是无状态的，所以一般情况下Strunts1是线程安全的。如果Action中用了实例变量，那么就变成有状态了，同样是非线程安全的。
(3). Struts2默认的实现是Prototype模式。也就是每个请求都新生成一个Action实例，所以不存在线程安全问题。需要注意的是，如果由Spring管理action的生命周期， scope要配成prototype作用域。
(4). 如何解决Servlet和Struts1的线程安全问题，当我们能比较好的理解有状态和无状态的原理，自然很容易得出结论：不要使用有状态的bean,也就是不要用实例变量。如果用，就要用prototype模式

### 总结：
Stateless无状态用单例Singleton模式，Stateful有状态就用原型Prototype模式。
Stateful 有状态是多线程编码的天敌，所以在开发中尽量用Stateless无状态，无状态是不变(immutable)模式的应用，有很多优点：不用管线程和同步的问题，如果值是不可变的，程序不用担心多个线程改变共享状态，所以可以避免线程竞争的bugs. 因为没有竞争，就不用用locks等机制，所以无状态的不变机制，也可以避免产生死锁现象。



