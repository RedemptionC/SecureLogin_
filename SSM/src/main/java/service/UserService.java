package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.User;
import mapper.IUserMapper;
import util.IsEmail;
import util.SHA1;

//import省略
@Service
public class UserService
{
	// 此处变量是接口类型，不加@Autowired
	private IUserMapper iUserMapper;

	@Autowired
	public UserService(IUserMapper iUserMapper)
	{
		this.iUserMapper = iUserMapper;
	}


	public User loginByName(User user)
	{
		User u = iUserMapper.checkUserByName(user);
		if (u != null)
			return u;
		else
		{
			return null;
		}
	}
	
	public User loginByEmail(User user)
	{
		
		User u = iUserMapper.checkUserByEmail(user);

		if (u != null)
			return u;
		else
		{
			return null;
		}
	}

	@Transactional
	public boolean register(User user)
	{
		int cnt = iUserMapper.addUser(user);
		if (cnt != 0)
			return true;
		else
		{
			return false;
		}
	}

	public User findUserByName(String username)
	{
		User u = iUserMapper.findUserByUserName(username);
		if (u != null)
			return u;
		else
		{
			return null;
		}
	}
	public User findUserByEmail(String email)
	{
		User u = iUserMapper.findUserByEmail(email);
		if (u != null)
			return u;
		else
		{
			return null;
		}
	}
	
	public boolean setState(Integer state,String name)
	{
		User user=new User();
		user.setActivated(state);
		user.setName(name);
		System.out.println(user);
		int rs=iUserMapper.activateByName(user);
		if(rs==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public User findUserByNameAndEmail(User user)
	{
		User t=iUserMapper.findUserByNameAndEmail(user);
		return t;
	}
	
	public int resetPwd(User user)
	{
		int rs=iUserMapper.resetPwd(user);
		return rs;
	}
	
	public User accountExist(String id)
	{
		boolean email=IsEmail.checkEmaile(id);
		if(email==true)
		{
			User rs=iUserMapper.findUserByEmail(id);
			if(rs==null)
			{
				return null;
			}
			else
			{
				return rs;
			}
		}
		else
		{
			User rs=iUserMapper.findUserByUserName(id);
			if(rs==null)
			{
				return null;
			}
			else
			{
				return rs;
			}
		}
		
	}
	
	public boolean pwdCorrect(User fromDb,User fromForm)
	{
		String name=fromDb.getName();
		String pwd=fromForm.getPwd();
		String rs=SHA1.encode(pwd+name);
		System.out.println(rs);
		System.out.println(fromDb.getPwd());
		if(fromDb.getPwd().equals(rs))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}