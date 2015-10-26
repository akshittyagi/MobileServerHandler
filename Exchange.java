
public class Exchange {
	
	private int uid;
	Exchange parent;
	private MobilePhoneSet setOfPhones;
	private ExchangeList children;
	private int level;
	
	public Exchange(int uid)
	{
		this.uid=uid;
		parent=null;
		children=new ExchangeList();
		setOfPhones=new MobilePhoneSet();
	}
	
	public Exchange(int uid,int level)
	{
		this.uid=uid;
		this.level=level;
		parent=null;
		children=new ExchangeList();
		setOfPhones=new MobilePhoneSet();
	}
	public Exchange()
	{
		parent=null;
		children=new ExchangeList();
	}
	
	public void setLevel(int l)
	{
		this.level=l;
	}
	public int getLevel()
	{
		return this.level;
	}
	
	public void addChildren(Exchange node) throws LinkedListOutofBoundsException, EmptySetException
	{
		children.Insert(node);
		this.setOfPhones.Set.Union(node.setOfPhones.Set);
	}
	
	public int getID()
	{
		return this.uid;
	}
	public void setMobSet(MobilePhoneSet mobSet)
	{
		this.setOfPhones=mobSet;
	}
	
	public void setChildren(ExchangeList children)
	{
		this.children=children;
	}
	
	public Exchange parent()
	{
		return this.parent;
	}
	
	public int numChildren()
	{
		return children.size();
	}
	
	public Exchange getIthChild(int i) throws LinkedListOutofBoundsException
	{
		return children.getIthExchange(i);
	}
	
	public boolean isRoot()
	{
		if(parent==null)
			return true;
		
		return false;
	}
	
	public RoutingMapTree subtree(int i,int level) throws LinkedListOutofBoundsException
	{
		RoutingMapTree r=new RoutingMapTree(children.getIthExchange(i),level);
		return r;
	}
	
	public void addToExchange(MobilePhone a)
	{
		setOfPhones.Insert(a);
	}
	
	public void removeFromExchange(MobilePhone a) throws LinkedListOutofBoundsException, EmptySetException
	{
		setOfPhones.Delete(a);
		a.nullifyBaseStation();
	}
	
	public MobilePhone isInExchange(int id) throws LinkedListOutofBoundsException
	{
		for(int i=0;i<setOfPhones.size();i++)
		{
			if(setOfPhones.getIthElement(i).getNumber()==id)
			{
				return setOfPhones.getIthElement(i);
			}
		}
		
		return null;
	}
	
	public MobilePhoneSet residentSet()
	{
		return setOfPhones;
	}
	
	public void setParent(Exchange parent)
	{
		this.parent=parent;
	}

}
