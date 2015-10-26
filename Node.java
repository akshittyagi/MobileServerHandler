
public class Node<T> {
	
	private T data;
	Node<T> next;
	
	public Node() {
		// TODO Auto-generated constructor stub
		next=null;
	}
	
	public Node(T data)
	{
		this.data=data;
	}
	
	public Node<T> getNext()
	{
		return this.next;
	}
	
	public T getData()
	{
		return this.data;
	}
	
	public void setNext(Node<T> next)
	{
		this.next=next;
	}

}
