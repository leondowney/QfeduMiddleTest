package SingletonPattern;

/**
 * @author Leon Downey
 * @date 2023/2/10 16:06
 * @describe： 单例模式
 */
class Singleton{
    //私有化类的构造器
    private Singleton(){
    }
    //声明当前类的对象，并且是static，但是没有赋值
    //此处添加volatile是为了防止指令重排序
    private static volatile Singleton instance = null;
    //声明为public、static的方法用来返回类的实例
    //如果发现当且instance为null，给instance创建一个实例。
    public static Singleton getInstance(){
        if(instance == null){
            synchronized(Singleton.class){
                if(instance == null){
                    //这并不是一个原子操作
                    //1.给instance分配内存空间
                    //2.调用Singleton构造函数初始化
                    //3.将instance对象指向分配的内存空间，此时instance不为null
                    //使用volatile修饰instance就是防止这1-2-3步被指令重排，保证其顺序执行
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
