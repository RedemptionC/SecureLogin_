package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.User;

//import省略
@Repository
public interface IUserMapper
{
	@Update("update user set activated=#{activated} where name=#{name}")
	public int activateByName(User user);
	
	@Insert("insert into user(name,pwd,email,activated) values(#{name},#{pwd},#{email},#{activated})")
	public int addUser(User user);

	@Delete("delete from user where id=#{id}")
	public int deleteUser(int id);

	@Update("update user set name=#{username},pwd=#{password} where id=#{id}")
	public int updateUser(User user);

	@Select("select * from user where id=#{id}")
	public User getUserById(int id);

	@Select("select * from user where name=#{name} and pwd=#{pwd} ")
	public User checkUserByName(User user);
	
	@Select("select * from user where pwd=#{pwd} and email=#{email} ")
	public User checkUserByEmail(User user);

	@Select("select * from user where name=#{username}")
	public User findUserByUserName(String username);

	@Select("select * from user")
	public List<User> getAllUsers();
	
	@Select("select * from user where email=#{email}")
	public User findUserByEmail(String mail);
	
	@Select("select * from user where email=#{email} and name=#{name}")
	public User findUserByNameAndEmail(User user);
	
	@Update("update user set pwd=#{pwd} where name=#{name}")
	public int resetPwd(User user);
}