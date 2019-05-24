package com.test.link;
/**
 * 
* @ClassName: Test  
* <p>Description: 
* 单链表 
*   https://www.cnblogs.com/qianguyihao/p/4761593.html
*   </p>
* @date 2019年5月24日 下午3:07:43  
*
 */
public class Test {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        LinkList list = new LinkList();
        for (int i = 0; i < 10; i++) {
            int temp = ((int) (Math.random() * 100)) % 100;
            list.insert(i, temp);
            System.out.print(temp + " ");
        }

        list.delete(4);
        System.out.println("\n------删除第五个元素之后-------");
        for (int i = 0; i < list.size; i++) {
            System.out.print(list.get(i) + " ");
        }
    }

}
