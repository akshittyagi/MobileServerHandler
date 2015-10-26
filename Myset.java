
public class Myset<T>{
	
	private LinkedList<T> l;
	
	public Myset() {
		// TODO Auto-generated constructor stub
		l=new LinkedList<T>();
	}
	
	public boolean IsEmpty()
	{
		if(l.getSize()==0)
			return true;
		
		return false;
	}
	
	public int GetSize()
	{
		return l.getSize();
	}
	
	public boolean IsMember(Object o) throws LinkedListOutofBoundsException
	{
		int i=0;
		
		while(i<l.getSize())
		{
			if(l.get(i).equals(o))
			{
				return true;
			}
			i++;
		}
		
		return false;
	}
	
	public void Insert(Object o)
	{
		l.add((T)o);
	}
	
	public T getElement(T a) throws LinkedListOutofBoundsException
	{
		int i=0;
		while(i<l.getSize())
		{
			if(l.get(i).equals(a))
				return l.get(i).getData();
			i++;
		}
		
		return null; 
	}
	
	public void Delete(Object o) throws LinkedListOutofBoundsException, EmptySetException
	{
		int i=0;
		while(i<l.getSize())
		{
			if(l.get(i).getData().equals(o))
				{l.remove(l.get(i).getData());
				return;}
			i++;
		}
		
	   throw new EmptySetException();
		
	}
	
	public T getIthElement(int i) throws LinkedListOutofBoundsException
	{
		return this.l.get(i).getData();
	}
	
	public Myset<T> Intersection(Myset<T> set) throws LinkedListOutofBoundsException
	{
		Myset<T> inter =new Myset<T>();
		
		int i=0;
		while(i<this.l.getSize())
		{
			if(set.IsMember(this.getIthElement(i)))
			{
				inter.Insert(this.getIthElement(i));
			}
			i++;
		}
		
		return inter;
	}
	
	public Myset<T> Union(Myset<T> set) throws LinkedListOutofBoundsException, EmptySetException
	{
		Myset<T> union=new Myset<T>();
		
		int i=0;
		
		while(i<this.l.getSize())
		{
			union.Insert(this.getIthElement(i));
		i++;
		}
		
		i=0;
		while(i<set.l.getSize())
		{
			union.Insert(set.getIthElement(i));
		i++;
		}
		
		i=0;
		Myset<T> inter=this.Intersection(set);
		while(i<inter.l.getSize())
		{
			union.Delete(inter.getIthElement(i));
		}
		
		return union;
	}
	
	
	
	
}
