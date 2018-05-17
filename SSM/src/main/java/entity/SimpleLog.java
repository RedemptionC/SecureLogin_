package entity;

import java.sql.Time;
import java.sql.Timestamp;

public class SimpleLog
{
	String name;
	String ip;
	String action;
	Timestamp time;
	public SimpleLog(Timestamp time,String ip,String action,String name)
	{
		this.name=name;
		this.ip=ip;
		this.action=action;
		this.time=time;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	public String getAction()
	{
		return action;
	}
	public void setAction(String action)
	{
		this.action = action;
	}
	public Timestamp getTime()
	{
		return time;
	}
	public void setTime(Timestamp time)
	{
		this.time = time;
	}
	@Override
	public String toString()
	{
		return "SimpleLog [name=" + name + ", ip=" + ip + ", action=" + action + ", time=" + time + "]";
	}
	
}
