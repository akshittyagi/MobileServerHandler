
public class MobilePhoneSet {
	
	Myset<MobilePhone> Set;
	public MobilePhoneSet()
	{
		Set=new Myset<MobilePhone>();
	}
	
	public int size()
	{
		return Set.GetSize();
	}
	
	public MobilePhone getIthElement(int i) throws LinkedListOutofBoundsException
	{
		return Set.getIthElement(i);
	}
	
	public void Insert(MobilePhone m)
	{
		Set.Insert(m);
	}
	
	public void Delete(MobilePhone m) throws LinkedListOutofBoundsException, EmptySetException
	{
		Set.Delete(m);
	}
	

}
