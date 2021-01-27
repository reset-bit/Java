package homework.datastructure;

/**
 * 双向链表实现（内部类）
 * 
 * @author people
 * @date 2020/4/27 下午
 * */

public class Doublelist<T> {
	   class Node {//辅助类，不面向用户交互，也可放在类外，不加public
	       T data;
	       Node next;
	       Node front;
	       public Node() {
	           this.data=null;
	           this.next=null;
	           this.front=null;
	       }
	       public Node(T data,Node pre,Node next) {
	           this.data=data;
	           this.front=pre;
	           this.next=next;
	       }
	       public void setData(T a) {
	           this.data=a;
	       }
	       public T getData() {
	           return data;
	       }
	   }
	   
	   public int size;
	   public Node head;
	   public  Node rear;
	   
	   public  Doublelist() {
	       this.size=0;
	       this.head=new Node();
	       this.rear=new Node(null,this.head,null);
	   }
	   
	   public void insert(T x) {
	       Node tmp=new Node(x,null,null);
	       this.rear.front.next=tmp;
	       tmp.front=this.rear.front;
	       tmp.next=this.rear;
	       this.rear.front=tmp;
	       size++;
	   }
	   
	   public void insert(int pos,T x) {
	       Node tmp=new Node(x,null,null);
	       Node cur=this.head;
	       for(int i=0;i<pos;++i) {
	           cur=cur.next;
	       }
	       tmp.next=cur;
	       tmp.front=cur.front;
	       cur.front.next=tmp;
	       cur.front=tmp;
	       size++;
	   }
	   
	   public void delete(T x) {
	       Node tmp=this.head.next;
	       while(tmp!=null) {
	           if(tmp.data==x) {
	               tmp.front.next=tmp.next;
	               tmp.next.front=tmp.front;
	               size--;
	           }
	           tmp=tmp.next;
	       }
	   }
	   
	   public void delete(int pos,Doublelist t) {
	       Node tmp=this.head;
	       for(int i=0;i<pos;++i) {
	           tmp=tmp.next;
	       }
	       tmp.front.next=tmp.next;
	       tmp.next.front=tmp.front;
	       size--;
	   }
	   
	   public boolean isEmpty() {
	       if(size==0) return true;
	       else return false;
	   }
	   
	   public int size() {
	       return size;
	   }
	   
	   public void traverse() {
	       Node tmp=head.next;
	       while(tmp.next!=null) {
	           System.out.print(tmp.data);
	           tmp=tmp.next;
	       }
	       System.out.println();
	   }
	   
	   public static void main(String[] args) {
	       Doublelist<Integer> list=new Doublelist<Integer>();
	       list.insert(1);
	       list.insert(2);
	       list.insert(3);
	       list.insert(4,5);
	       System.out.println("size: "+list.size());
	       list.traverse();
	       list.delete(3);
	       list.traverse();
	       list.delete(5,list);
	       list.traverse();
	   }
}
