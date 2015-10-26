
public class ExchangeList {
	
	LinkedList<Exchange> exList;
	
	public ExchangeList() {
		// TODO Auto-generated constructor stub
		exList=new LinkedList<Exchange>();
	}
	
	public int size()
	{
		return exList.getSize();
	}
	
	public Exchange getIthExchange(int i) throws LinkedListOutofBoundsException
	{
		return exList.get(i).getData();
	}
	
	public void Insert(Exchange exchange)
	{
		exList.add(exchange);
	}
	
	public void Delete(Exchange exchange)
	{
		exList.remove(exchange);
	}
	
//	public static void main(String args[]) throws LinkedListOutofBoundsException
//	{
//		ExchangeList ex=new ExchangeList();
//		for(int i=0;i<10;i++)
//		{
//			Exchange temp=new Exchange(i);
//			ex.Insert(temp);
//			System.out.println(ex.size());
//		}
//		
//		ex.Delete(ex.exList.head.getData());
//		System.out.println(ex.size());
//	}
}