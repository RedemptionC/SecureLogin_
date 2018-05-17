package entity;

import org.springframework.stereotype.Component;

@Component
public class User
{
	String name;
	String pwd;
	String email;
	Integer activated=0;


	public Integer getActivated()
	{
		return activated;
	}

	public void setActivated(Integer activated)
	{
		this.activated = activated;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPwd()
	{
		return pwd;
	}

	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}

	@Override
	public String toString()
	{
		return "User [name=" + name + ", pwd=" + pwd + ", email=" + email + ", activated=" + activated + "]";
	}







}