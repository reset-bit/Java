package homework.datastructure;

/**
 * 双向链表实现
 * 
 * @author Reset
 * @date 2020/4/27 下午
 * */

class Node{
	Object data;
	Node prev;
	Node next;
	
	public Node() {
		this.data = null;
		this.prev = this.next = null;
	}
	
	public Node(Object data) {
		this.data = data;
		this.prev = this.next = null;
	}
}

public class LinkList {
	public Node head;//用户可访问-public
	public Node tail;
	public int size = 0;
	
	public LinkList() {
		head = new Node(0);
		tail = head;
	}
	
	public LinkList(Object data) {
		Node newNode = new Node(data);
		head = new Node(0);
		head.next = newNode;
		newNode.prev = head;
		tail = newNode;
	}
	
	/**
	 * 在链表某个位置插入对象
	 * 
	 * @param data 目标插入数据
	 * @param pos 目标插入位置
	 * @exception 插入位置不合法
	 * @return false 插入目标位置失败; true 插入目标位置成功
	 * */
	public boolean insert(int pos, Object data) {
		Node newNode = new Node(data);
		Node curNode = head;
		for(int i = 1; curNode != null && i < pos; ++i) {
			curNode = curNode.next;
		}
		if(curNode == null) {
			System.out.println("插入位置不合法");
			return false;
		}
		else {
			curNode.prev.next = newNode;
			newNode.prev = curNode.prev;
			newNode.next = curNode;
			curNode.prev = newNode;
			System.out.println("已在第"+pos+"位插入值为"+data+"的数据");
			++size;
			return true;
		}
	}
	
	/**
	 * 在链表最后插入对象
	 * 这里输入的是数据，若插入节点，即：main函数中new Node(data)，会破坏封装-->需要知道Node构造方法
	 * 
	 * @param data 目标插入数据
	 * @exception 链表为空链表
	 * */
	public boolean insert(Object data) {
		Node newNode = new Node(data);
		if(size == 0) {//空链表
			newNode.prev = head;
			head.next = newNode;
			tail = newNode;
		}
		else {//非空链表
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		++size;
		return true;
	}
	
	/**
	 * 在链表的某个位置删除对象
	 * 
	 * @param pos 需要删除的位置
	 * @exception 空链表/删除位置不合法/删除最后一个元素
	 * */
	public boolean delete(int pos) {
		Node curNode = head;
		if(size == 0) {//空链表
			System.out.println("本链表为空链表，删除位置不合法");;
			return false;
		}
		else {//非空链表
			for(int i = 0; curNode !=null && i < pos; ++i) {
				curNode = curNode.next;
			}
			if(curNode == null) {
				System.out.println("删除位置不合法");
				return false;
			}
			else {
				if(curNode.equals(tail)) {//删除尾元素
					curNode.prev.next = null;
					tail = curNode.prev;
				}
				else {
					curNode.prev.next = curNode.next.prev;
					curNode.next.prev = curNode.prev.next;
				}
				--size;
				return true;
			}
		}
	}
	
	/**
	 * 删除与目标数据相同的元素
	 * 
	 * @param data 目标数据
	 * @exception 空链表/不存在相同数据/删除最后一个元素
	 * */
	public boolean delete(Object data) {
		Node curNode = head;
		if(size == 0) {//空链表
			System.out.println("本链表为空链表，没有相同元素");
			return false;
		}
		else {
			while(curNode != null && !curNode.data.equals(data)) {
				curNode = curNode.next;
			}
			if(curNode == null) {
				System.out.println("没有相同元素");
				return false;
			}
			else {
				if(curNode.equals(tail)) {//删除尾元素
					curNode.prev.next = null;
					tail = curNode.prev;
				}
				else {
					curNode.prev.next = curNode.next.prev;
					curNode.next.prev = curNode.prev.next;
				}
				--size;
				return true;
			}
		}
	}
	
	public void traverse() {
		if(size != 0) {
			Node curNode = head.next;
			while(curNode != null) {
				System.out.print(curNode.data);
				curNode = curNode.next;
			}
		}
		System.out.println(" ");
	}
	
	/**
	 * 判断链表是否为空
	 * 
	 * @return true 链表为空; false 链表不为空
	 * */
	public boolean isEmpty() {
		if(head.next == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 取链表指定位置数据
	 * 
	 * @param pos 目标位置
	 * @exception 空链表/位置不合法
	 * */
	public Object getData(int pos) {
		Node curNode = head;
		if(size == 0) {//空链表
			System.out.println("本链表为空链表，输出错误");
			return null;
		}
		else {
			for(int i = 0; curNode != null && i < pos; ++i) {
				curNode = curNode.next;
			}
			return curNode.data;
		}
	}
	
	public int size() {
		return size;
	}
	public static void main(String[] args) {
		LinkList list = new LinkList();
		Integer[] array = {0,1,2,3,4,5,6,7,8,9};
		
		for(int i = 1; i < array.length; ++i) {
			list.insert(array[i]); 
		}
		if(list.isEmpty()) {
			System.out.println("this list is empty");
		}
		else {
			System.out.println("this list is not empty");
		}
		System.out.println("the size of this list is "+list.size());
		System.out.print("the current list is ");
		list.traverse();
		list.delete(9);
		System.out.print("the current list is ");
		list.traverse();
		System.out.println(list.getData(3));
		list.insert(6, 9);
		list.insert(33);
		System.out.print("the current list is ");
		list.traverse();
	}
}
