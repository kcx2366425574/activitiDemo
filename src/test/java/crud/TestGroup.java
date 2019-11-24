package crud;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @date 2019-11-24
 * @author kcx
 * @description activiti数据查询
 */
public class TestGroup {
			
	/**
	 * 创建流程引擎
	 */
	ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
	
	IdentityService identityService = engine.getIdentityService();
	
	/**
	 * 在group表中save数据
	 */
	@Test
	public void testSave() {
		for(int i = 0;i<10;i++) {
			Group group = identityService.newGroup(String.valueOf(i));
			group.setName("Name_"+String.valueOf(i));
			group.setType("Type_"+String.valueOf(i));
			identityService.saveGroup(group);
		}
	}
	
	/**
	 *用list() 查询group表中的数据
	 */
	@Test
	public void testList() {
		List<Group> groups = identityService.createGroupQuery().list();
		
		for (Group group : groups) {
			System.out.println(group.getId()+"---"+group.getType()+"-----"+group.getName());
		}
	}
	
	/**
	 *用listpage() 查询group表中的数据
	 */
	@Test
	public void testListpage() {
		List<Group> groups = identityService.createGroupQuery().listPage(1, 5);
		
		for (Group group : groups) {
			System.out.println(group.getId()+"---"+group.getType()+"-----"+group.getName());
		}
	}
	
	/**
	 *用count() 计数group表中的数据
	 */
	@Test
	public void testCount() {
		long count = identityService.createGroupQuery().count();
		System.out.println(count);
	}
	
	/**
	 *用desc() 对group表中的数据排序
	 *每次调用orderBy()都要使用desc()或asc()进行排序
	 */
	@Test
	public void testDesc() {
		List<Group> groups = identityService.createGroupQuery().
				orderByGroupName().desc().
				orderByGroupId().asc().
				orderByGroupType().desc().
				list();
		
		for (Group group : groups) {
			System.out.println(group.getId()+"---"+group.getType()+"-----"+group.getName());
		}
	}
		
	/**
	 *用singleResult() 查询group表中唯一满足条件的数据
	 *如果表中含有两个以上满足条件的数据，会抛出异常
	 */
	@Test
	public void testSingle() {
		Group group = identityService.createGroupQuery().groupName("Name_4").singleResult();
		System.out.println(group.getId()+"---"+group.getType()+"-----"+group.getName());
		
	}	

	/**
	 *使用原生sql语句 查询group表中满足条件的数据
	 */
	@Test
	public void testSql() {
		List<Group> groups = identityService.createNativeGroupQuery().
				sql("select * from act_id_group where name_ = #{name}").
				parameter("name", "Name_2").list();
		for (Group group : groups) {
			System.out.println(group.getId()+"---"+group.getType()+"-----"+group.getName());
		}
		
	}

}
