
public class MobilePhone {
	
	private int number;
	private boolean status;
	private Exchange baseStation;
	
	public MobilePhone(int number) {
		// TODO Auto-generated constructor stub
		this.number=number;
		status=false;
	}
	
	public MobilePhone(int number,Exchange base)
	{
		this.number=number;
		status=false;
		this.baseStation=base;
	}
	
	public int getNumber()
	{
		return this.number;
	}
	
	public boolean getStatus()
	{
		return this.status;
	}
	
	public void switchOn()
	{
		status=true;
	}
	
	public void switchOff()
	{
		status=false;
	}
	
	public Exchange location() throws MobilePhoneOffException
	{
		if(status==false)
		{
			throw new MobilePhoneOffException();
		}
		
		return this.baseStation;
	}
	
	public void nullifyBaseStation()
	{
		baseStation=null;
	}

}
