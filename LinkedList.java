
public class LinkedList<T>{
	
	Node<T> head;
	private int size;
	
	public LinkedList() {
		// TODO Auto-generated constructor stub
		head=new Node<T>();
		size=0;
	}
	public LinkedList(Node<T> head)
	{	
		this.head=head;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public void add(T data)
	{
		Node<T> node=new Node<T>(data);
		node.setNext(head);
		head=node;
		size++;
	}
	
	public void remove(T node)
	{
		
		if(node.equals(this.head.getData()))
		{
			head=head.next;
			size--;
			return;
		}
		
		
		Node<T> curr=this.head;
		//TODO handle the last deletion
		while(!curr.next.getData().equals(node))
		{
			curr=curr.next;
		}
		
		
		curr.next=curr.next.next;
		size--;
	}
	
	public boolean isEmpty()
	{
		if(size==0)
		{
			return true;
		}
		return false;
	}
	
	public boolean contains(Node<T> node)
	{
		int i=0;
		Node<T> curr=head;
		while(i<size && !curr.equals(node))
		{
			curr=curr.next;
			i++;
		}
		
		if(i==size)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public Node<T> get(int i) throws LinkedListOutofBoundsException 
	{
		Node<T> curr=this.head;
		if(i>=size)
		{
			throw new LinkedListOutofBoundsException();
		}
		
		int count=size-i-1;
		while(count>0)
		{
			curr=curr.next;
			count--;
		}
		
		return curr;
		
	}
	
//	public static void main(String args[]) throws LinkedListOutofBoundsException
//	{
//		LinkedList<Integer> l=new LinkedList<Integer>();
//		
//		for(int i=0;i<10;i++)
//		{
//			Node<Integer> n=new Node<Integer>(i);
//			
//			l.add(n);
//		}
//		
//		System.out.println(l.size);
//		System.out.println(l.head.getData());
//
//		for(int i=0;i<l.getSize();i++)
//		{
//			System.out.println(l.get(i).getData());
//		}
//		
//		System.out.println(l.contains(l.head));
//		Node<Integer> node=l.head;
//		while(!l.isEmpty())
//		{
//			node=node.next;
//			System.out.println(node);
//			l.remove(node);
//		}
//		
//		
//		System.out.println(l.isEmpty());
//		
//	}

}
