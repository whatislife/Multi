package com.frankman.base.conn011;
/**
 * 
* @ClassName: DoubleSingle2  
* <p>Description: 
     单例模式中会学到双重检查加锁   然后volatile关键词会被问
  https://my.oschina.net/yukong/blog/870890
  </p>
* @date 2019年5月11日 下午2:23:39  
*
 */
public class DoubleSingle2 {
	
	private volatile static DoubleSingle2 uniqueInstance;
    private DoubleSingle2(){}
    public static DoubleSingle2 getInstance(){
        if(uniqueInstance == null){
        // B线程检测到uniqueInstance不为空
            synchronized(DoubleSingle2.class){
                if(uniqueInstance == null){
                    uniqueInstance = new DoubleSingle2();
                    // A线程被指令重排了，刚好先赋值了；但还没执行完构造函数。
                }
            }
        }
        return uniqueInstance;// 后面B线程执行时将引发：对象尚未初始化错误。
    }
    
    public static void main(String[] args) {
    	
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleSingle2.getInstance().hashCode());
			}
		},"t1");
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleSingle2.getInstance().hashCode());
			}
		},"t2");
		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(DoubleSingle2.getInstance().hashCode());
			}
		},"t3");
		
		t1.start();
		t2.start();
		t3.start();
	}

}
