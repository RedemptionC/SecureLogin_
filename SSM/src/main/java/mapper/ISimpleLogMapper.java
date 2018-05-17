package mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import entity.SimpleLog;

@Repository
public interface ISimpleLogMapper
{
	@Insert("insert into log(name,ip,action,time)values(#{name},#{ip},#{action},#{time})")
	public int addLog(SimpleLog sl);
	
	@Select("select count(*) from log where time like concat(#{today},'%') and name=#{name} and ACTION='用户名或密码错误'")
	public int failLoginInOneDay(@Param("today")String today,@Param("name")String name);
}
