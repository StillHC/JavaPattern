// 单列模式
// 饿汉式单例类在类被加载时就将自己实例化，它的优点在于无须考虑多线程访问问题，可以确保实例的唯一性；
// 从调用速度和反应时间角度来讲，由于单例对象一开始就得以创建，因此要优于懒汉式单例。
// 但是无论系统在运行时是否需要使用该单例对象，由于在类加载时该对象就需要创建，因此从资源利用效率角度来讲，
// 饿汉式单例不及懒汉式单例，而且在系统加载时由于需要创建饿汉式单例对象，加载时间可能会比较长。
//       懒汉式单例类在第一次使用时创建，无须一直占用系统资源，实现了延迟加载，
//       但是必须处理好多个线程同时访问的问题，特别是当单例类作为资源控制器，
//       在实例化时必然涉及资源初始化，而资源初始化很有可能耗费大量时间，这意味着出现多线程同时首次引用此类的机率变得较大，
//       需要通过双重检查锁定等机制进行控制，这将导致系统性能受到一定影响。

//饿汉模式
class eagerSingleton{
	private static final eagerSingleton instance = new eagerSingleton();

	private eagerSingleton(){

	} 
	public static eagerSingleton getInstance(){
		return instance;
	}
}

//延迟加载 synchronized
class LazySingleton{
	private static LazySingleton instance = null;
	private LazySingleton(){}

	synchronized public static LazySingleton getInstance(){
		if (instance == null){
			instance = new LazySingleton();
		}
		return instance;
	}
}
//双重检查锁定。
class LazySingleton{
	private static LazySingleton instance = null;

	private LazySingleton(){}

	public static LazySingleton getInstance(){
		if (instance == null){
			synchronized (LazySingleton.class){
				if (instance == null){
					instance = new LazySingleton();
				}
			}
		}
	} 
}


//IoDH技术。
// 通过使用IoDH，我们既可以实现延迟加载，又可以保证线程安全，
// 不影响系统性能，不失为一种最好的Java语言单例模式实现方式
// （其缺点是与编程语言本身的特性相关，很多面向对象语言不支持IoDH
// 在单例类中增加一个静态内部类。

class Singleton{
	private Singleton(){}

	private static class HolderClass{
		private final static Singleton instance = new Singleton();
	}

	public static Singleton getInstance(){
		return HolderClass.instance;
	}

	public static void main(String args[]){
		Singleton s1,s2;
		s1 = Singleton.getInstance();
		s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
	}
}

class Single{

   private volatile static Single instance=null; //被volatile修饰的成员变量可
                  //确保多线程能给正确处理
 
  public Single(){}
  
  public static Single getInstance(){
     
       //第一重判断
      if(instance==null){
             //锁定代码块
           synchronized(Single.class){
                if(instance==null){
                    instance=new Single();
                    
           }
         }
    }
      return instance;
 } 


 1.主要优点
       单例模式的主要优点如下：
       (1) 单例模式提供了对唯一实例的受控访问。因为单例类封装了它的唯一实例，所以它可以严格控制客户怎样以及何时访问它。
       (2) 由于在系统内存中只存在一个对象，因此可以节约系统资源，对于一些需要频繁创建和销毁的对象单例模式无疑可以提高系统的性能。
       (3) 允许可变数目的实例。基于单例模式我们可以进行扩展，使用与单例控制相似的方法来获得指定个数的对象实例，既节省系统资源，又解决了单例单例对象共享过多有损性能的问题。
 
2.主要缺点
       单例模式的主要缺点如下：
       (1) 由于单例模式中没有抽象层，因此单例类的扩展有很大的困难。
       (2) 单例类的职责过重，在一定程度上违背了“单一职责原则”。因为单例类既充当了工厂角色，提供了工厂方法，同时又充当了产品角色，包含一些业务方法，将产品的创建和产品的本身的功能融合到一起。
       (3) 现在很多面向对象语言(如Java、C#)的运行环境都提供了自动垃圾回收的技术，因此，如果实例化的共享对象长时间不被利用，系统会认为它是垃圾，会自动销毁并回收资源，下次利用时又将重新实例化，这将导致共享的单例对象状态的丢失。
 
3.适用场景
       在以下情况下可以考虑使用单例模式：
       (1) 系统只需要一个实例对象，如系统要求提供一个唯一的序列号生成器或资源管理器，或者需要考虑资源消耗太大而只允许创建一个对象。
       (2) 客户调用类的单个实例只允许使用一个公共访问点，除了该公共访问点，不能通过其他途径访问该实例。
