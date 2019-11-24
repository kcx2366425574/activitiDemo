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
 * @description activiti���ݲ�ѯ
 */
public class TestGroup {
			
	/**
	 * ������������
	 */
	ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
	
	IdentityService identityService = engine.getIdentityService();
	
	/**
	 * ��group����save����
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
	 *��list() ��ѯgroup���е�����
	 */
	@Test
	public void testList() {
		List<Group> groups = identityService.createGroupQuery().list();
		
		for (Group group : groups) {
			System.out.println(group.getId()+"---"+group.getType()+"-----"+group.getName());
		}
	}
	
	/**
	 *��listpage() ��ѯgroup���е�����
	 */
	@Test
	public void testListpage() {
		List<Group> groups = identityService.createGroupQuery().listPage(1, 5);
		
		for (Group group : groups) {
			System.out.println(group.getId()+"---"+group.getType()+"-----"+group.getName());
		}
	}
	
	/**
	 *��count() ����group���е�����
	 */
	@Test
	public void testCount() {
		long count = identityService.createGroupQuery().count();
		System.out.println(count);
	}
	
	/**
	 *��desc() ��group���е���������
	 *ÿ�ε���orderBy()��Ҫʹ��desc()��asc()��������
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
	 *��singleResult() ��ѯgroup����Ψһ��������������
	 *������к������������������������ݣ����׳��쳣
	 */
	@Test
	public void testSingle() {
		Group group = identityService.createGroupQuery().groupName("Name_4").singleResult();
		System.out.println(group.getId()+"---"+group.getType()+"-----"+group.getName());
		
	}	

	/**
	 *ʹ��ԭ��sql��� ��ѯgroup������������������
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
