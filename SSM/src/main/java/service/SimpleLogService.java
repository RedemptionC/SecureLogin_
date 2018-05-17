package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.SimpleLog;
import mapper.ISimpleLogMapper;

@Service
public class SimpleLogService
{
	private ISimpleLogMapper simpleLog;
	
	@Autowired
	public SimpleLogService(ISimpleLogMapper sl)
	{
		this.simpleLog=sl;
	}
	public boolean addLog(SimpleLog sl)
	{
		int rs=simpleLog.addLog(sl);
		if(rs!=0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public int faliLoginInOneDay(String name)
	{
		String today=new java.sql.Date(System.currentTimeMillis()).toString();
		int times=simpleLog.failLoginInOneDay(today, name);
		return times;
		
		
	}
	
	
	
}
