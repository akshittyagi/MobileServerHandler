
public class RoutingMapTree {

	private Exchange root;

	public RoutingMapTree(Exchange node,int level) {
		// TODO Auto-generated constructor stub
		root=node;
		root.setLevel(level);
	}

	public RoutingMapTree()
	{
		root=new Exchange(0,0);
	}

	public boolean isEmpty()
	{
		if(root==null)
			return true;

		return false;
	}

	public Exchange getRoot()
	{
		return root;
	}

	public Exchange findNodeWithID(int id,RoutingMapTree tree) throws LinkedListOutofBoundsException,ExchangeNotFoundException
	{

		if(tree.getRoot().getID()==id)
		{
			return tree.getRoot();
		}

		if(tree.getRoot().numChildren()==0)
		{
			return null;
		}
		Exchange temp=new Exchange();
		int i;

		for(i=0;i<tree.getRoot().numChildren();i++)
		{
			temp=findNodeWithID(id,tree.getRoot().subtree(i,tree.getRoot().getLevel()+1));

			if(temp!=null)
				break;

		}

		if(temp==null && tree.getRoot().getID()==0)
		{
			throw new ExchangeNotFoundException();
		}

		return temp;
	}

	public Exchange parentOfNode(Exchange node)
	{
		return node.parent();
	}

	public boolean isExternal(Exchange node)
	{
		if(node.numChildren()==0)
		{
			return true;
		}

		return false;
	}

	public void addExchange(int a,int b) throws LinkedListOutofBoundsException, ExchangeNotFoundException, EmptySetException, AlreadyHasChildrenHenceCanNotAddExchangeException
	{
		Exchange nodeA=findNodeWithID(a, this);
		Exchange nodeB=new Exchange(b,nodeA.getLevel()+1);

		if(this.isExternal(nodeA) && nodeA.residentSet().size()!=0)
		{
			throw new AlreadyHasChildrenHenceCanNotAddExchangeException(); 
		}
		nodeA.addChildren(nodeB);
		nodeB.setParent(nodeA);

	}

	public int queryNthChild(int a,int n) throws LinkedListOutofBoundsException, ExchangeNotFoundException
	{
		Exchange nodeA=findNodeWithID(a, this);
		return nodeA.getIthChild(n).getID();
	}

	public MobilePhoneSet queryMobilePhoneSet(int a) throws LinkedListOutofBoundsException, ExchangeNotFoundException
	{
		Exchange nodeA=findNodeWithID(a,this);
		return nodeA.residentSet();
	}

	public void switchOnMobile(int a,int b) throws LinkedListOutofBoundsException, ExchangeNotFoundException, MobilePhoneAlreadyOnException, ExchangeIsNotBaseHenceNotExternalException
	{
		Exchange nodeB=findNodeWithID(b,this);
		if(!this.isExternal(nodeB))
		{
			throw new ExchangeIsNotBaseHenceNotExternalException();
		}
		if(this.root.isInExchange(a)!=null)
		{
			throw new MobilePhoneAlreadyOnException();
		}
		else if(nodeB.isInExchange(a)!=null)
		{
			nodeB.isInExchange(a).switchOn();
			//already exists
		}
		else
		{
			MobilePhone mobA=new MobilePhone(a,nodeB);
			mobA.switchOn();
			nodeB.addToExchange(mobA);
			if(nodeB.isRoot())
			{return;}
			Exchange node=nodeB.parent;
			while(node!=this.root)
			{	
				node.addToExchange(mobA);
				node=node.parent;
			}
			//entire tree updated
			root.addToExchange(mobA);
		}
	}

	public void switchOffMobile(int a) throws LinkedListOutofBoundsException,MobilePhoneNotFoundException, MobilePhoneOffException, EmptySetException
	{
		int i=0;
		MobilePhone mobA=new MobilePhone(a);
		while(i<this.getRoot().residentSet().size())
		{
			if(this.getRoot().residentSet().getIthElement(i).getNumber()==a)
			{	
				mobA=this.getRoot().residentSet().getIthElement(i);
				break;
			}
			i++;
		}

		if(i==this.getRoot().residentSet().size())
		{
			throw new MobilePhoneNotFoundException();
		}
		if(!mobA.getStatus())
		{
			throw new MobilePhoneOffException();
		}

				Exchange mobBaseStation=mobA.location();
				
				mobBaseStation.removeFromExchange(mobA);
				Exchange node = mobBaseStation.parent;
		
				while(node!=this.root)
				{
					node.removeFromExchange(mobA);
					node=node.parent;
				}
				
				//entire tree updated
				root.removeFromExchange(mobA);
		mobA.switchOff();
	}

	public void performAction(String actionMessage) 
	{ 
		try{
			String str[]=actionMessage.split(" ");

			String str1=str[0];
			String str2=str[1];
			String str3=null;
			if(!str1.equals("queryMobilePhoneSet") && !str1.equals("switchOffMobile") && !str1.equals("findPhone"))
			{
				str3=str[2];
			}

			int a=Integer.parseInt(str2);
			int b=0;
			if(str.length==3)
			{
				b=Integer.parseInt(str3);
			}

			if(str1.equals("addExchange"))
			{
				addExchange(a, b);
				return ;
			}
			else if(str1.equals("switchOnMobile"))
			{
				switchOnMobile(a, b);
				return ;
			}
			else if(str1.equals("switchOffMobile"))
			{
				MobilePhone mobA=findMobilePhoneByID(a);
				int i=0;
				MobilePhone temp=new MobilePhone(a);
				while(i<this.getRoot().residentSet().size())
				{
					if(this.getRoot().residentSet().getIthElement(i).getNumber()==a)
					{	
						temp=this.getRoot().residentSet().getIthElement(i);
						break;
					}
					i++;
				}

				if(i==this.getRoot().residentSet().size())
				{
					throw new MobilePhoneNotFoundException();
				}
				if(!temp.getStatus())
				{
					throw new MobilePhoneOffException();
				}
				
				mobA.switchOff();
				
				return ;
			}
			else if(str1.equals("queryNthChild"))
			{
				int id=queryNthChild(a, b);
				System.out.println(b+"th Child of " + a + ": "+id +" "+findNodeWithID(a, this).getIthChild(b).getLevel());
				return ;
			}
			else if(str1.equals("queryMobilePhoneSet"))
			{	
				MobilePhoneSet mbSet = queryMobilePhoneSet(a);
				System.out.println("MobilePhoneSet of " + a +" is: ");
				for(int i=0;i<mbSet.size();i++)
				{
					if(mbSet.getIthElement(i).getStatus())
						System.out.print(mbSet.getIthElement(i).getNumber() +" Status "+ (mbSet.getIthElement(i).getStatus()?"On":"Off") + ", ");
				}
				System.out.println();
				return ;
				
			}
			else if(str1.equals("findPhone"))
			{
				System.out.println("MobilePhone "+a+" found at Exchange: " + findPhone(findMobilePhoneByID(a)).getID());
				
				
				
				return ;
			}
			else if(str1.equals("lowestRouter"))
			{
				Exchange nodeA=findNodeWithID(a,this);
				Exchange nodeB=findNodeWithID(b, this);
				System.out.println("lowestRouter of " + a +" & "+ b + " is: " + lowestRouter(nodeA, nodeB).getID());
				
				return ;
			}
			else if(str1.equals("findCallPath"))
			{
				MobilePhone mobA=findMobilePhoneByID(a);
				MobilePhone mobB=findMobilePhoneByID(b);

				ExchangeList callRoute=routeCall(mobA, mobB);

				int i=0;
				System.out.println("Route for Call from "+a+" to "+b+" is: ");
				while(i<callRoute.size())
				{
					System.out.print(callRoute.getIthExchange(i).getID() +", ");
					i++;
				}
				System.out.println();
				return ;
			}
			else if(str1.equals("movePhone"))
			{
				MobilePhone mobA=findMobilePhoneByID(a);
				Exchange nodeB=findNodeWithID(b, this);

				movePhone(mobA, nodeB);

				return ;
			}
			else
			{
				System.out.println("Wrong Input");
				return ;
			}
		}

		catch(MobilePhoneOffException e)
		{
			System.out.println("MobilePhoneOff");
		}
		catch(MobilePhoneNotFoundException e)
		{
			System.out.println("MobilePhoneNotFound");
		}
		catch(ExchangeNotFoundException e)
		{
			System.out.println("ExchangeNotFound");
		}
		catch(EmptySetException e)
		{
			System.out.println("EmptySetException");
		}
		catch(LinkedListOutofBoundsException e)
		{
			System.out.println("LinkedListOutofBounds");
		}
		catch(MobilePhoneAlreadyOnException e)
		{
			System.out.println("MobilePhoneAlreadyOn");
		}
		catch(ExchangeIsNotBaseHenceNotExternalException e)
		{
			System.out.println("ExchangeIsNotBaseHenceNotExternal");
		}
		catch(AlreadyHasChildrenHenceCanNotAddExchangeException e)
		{
			System.out.println("AlreadyHasChildrenHenceCanNotAddExchangeException");
		}

	}

	public MobilePhone findMobilePhoneByID(int id) throws LinkedListOutofBoundsException, MobilePhoneNotFoundException
	{
		int i=0;
		MobilePhone ret=null;
		while(i<this.getRoot().residentSet().size())
		{
			if(this.getRoot().residentSet().getIthElement(i).getNumber()==id)
			{
				ret = this.getRoot().residentSet().getIthElement(i);
				break;
			}
			i++;
		}

		if(i==this.getRoot().residentSet().size() )
		{
			throw new MobilePhoneNotFoundException();
		}
		else
		{
			return ret;
		}
	}
	public Exchange findPhone(MobilePhone a) throws LinkedListOutofBoundsException, MobilePhoneNotFoundException, MobilePhoneOffException
	{
		int i=0;
		MobilePhone mob=null;
		while(i<this.getRoot().residentSet().size())
		{
			if(this.getRoot().residentSet().getIthElement(i).equals(a))
			{
				mob=this.getRoot().residentSet().getIthElement(i);
				break;
			}
			i++;
		}

		if(i==this.getRoot().residentSet().size() && mob==null)
		{
			throw new MobilePhoneNotFoundException();
		}
		else if(!mob.getStatus())
		{
			throw new MobilePhoneOffException();
		}
		else
		{
			return mob.location();
		}
	}

	public Exchange lowestRouter(Exchange a,Exchange b)
	{
		if(a.equals(b))
		{
			return a;
		}

		if(a.equals(this.root) || b.equals(this.root) )
		{
			return this.root;
		}

		if(a.getLevel()<b.getLevel())
		{
			return lowestRouter(a, b.parent);
		}
		else if(a.getLevel()>b.getLevel())
		{
			return lowestRouter(a.parent, b);
		}
		else
		{
			return lowestRouter(a.parent, b.parent);
		}
	}

	public ExchangeList routeCall(MobilePhone a,MobilePhone b) throws LinkedListOutofBoundsException, MobilePhoneNotFoundException, MobilePhoneOffException
	{
		Exchange ofA=findPhone(a);
		Exchange ofB=findPhone(b);
		Exchange lowestCommonNode=lowestRouter(ofA, ofB);


		ExchangeList callRoute=new ExchangeList();

		Exchange curr;
		if(!lowestCommonNode.equals(ofA) && !lowestCommonNode.equals(ofB))
		{
			curr=ofA;
			while(!curr.equals(lowestCommonNode))
			{
				callRoute.Insert(curr);
				curr=curr.parent;
			}
			callRoute.Insert(curr);

			ExchangeList rev=new ExchangeList();
			curr=ofB;
			while(!curr.parent.equals(lowestCommonNode))
			{
				rev.Insert(curr);
				curr=curr.parent;
			}
			rev.Insert(curr);

			int i=0;
			while(i<rev.size())
			{
				callRoute.Insert(rev.getIthExchange(rev.size()-i-1));
				i++;
			}
		}
		else if(lowestCommonNode.equals(ofA))
		{
			curr=ofB;
			ExchangeList rev=new ExchangeList();
			while(!curr.equals(ofA))
			{
				rev.Insert(curr);
				curr=curr.parent;
			}
			rev.Insert(curr);

			int i=0;
			while(i<rev.size())
			{
				callRoute.Insert(rev.getIthExchange(rev.size()-i-1));
				i++;
			}
		}
		else
		{
			curr=ofA;
			ExchangeList rev=new ExchangeList();
			while(!curr.equals(ofB))
			{
				rev.Insert(curr);
				curr=curr.parent;
			}
			rev.Insert(curr);

			int i=0;
			while(i<rev.size())
			{
				callRoute.Insert(rev.getIthExchange(rev.size()-i-1));
				i++;
			}
		}

		return callRoute;

	}

	public void movePhone(MobilePhone a,Exchange b) throws LinkedListOutofBoundsException, MobilePhoneNotFoundException, MobilePhoneOffException, EmptySetException, ExchangeNotFoundException, MobilePhoneAlreadyOnException, ExchangeIsNotBaseHenceNotExternalException
	{
		if(!a.getStatus())
		{
			throw new MobilePhoneOffException();
		}
		else if(!this.isExternal(b))
		{
			throw new ExchangeIsNotBaseHenceNotExternalException();
		}

		
		switchOffMobile(a.getNumber());
		switchOnMobile(a.getNumber(), b.getID());
	}


}




