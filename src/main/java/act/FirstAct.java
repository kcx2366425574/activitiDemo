package act;
import java.util.ArrayList;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @date 2019-11-24
 * @author kcx
 * @description 
 */
public class FirstAct {
	
	
	public static void main(String[] args) {
		/**
		 * ������������
		 */
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		/**
		 * �õ����̴洢�������
		 */
		RepositoryService repositoryService = engine.getRepositoryService();
		/**
		 * �õ�����ʱ�������
		 */
		RuntimeService runtimeService = engine.getRuntimeService();
		
		/**
		 * ��ȡ�����������
		 */
		TaskService taskService = engine.getTaskService();
		/**
		 * ���������ļ�
		 */
		repositoryService.createDeployment().
			addClasspathResource("first.bpmn").deploy();
		/**
		 * ��������
		 */
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");
		
		/**
		 * ��ͨԱ����д�������
		 */
		Task task = taskService.createTaskQuery().
				processInstanceId(processInstance.getId()).singleResult();
		System.out.println("��ǰ���̽ڵ㣺"+task.getName());
		taskService.complete(task.getId());
		
		/**
		 * �������
		 */
		task = taskService.createTaskQuery().
				processInstanceId(processInstance.getId()).singleResult();
		System.out.println("��ǰ���̽ڵ㣺"+task.getName());
		taskService.complete(task.getId());
		
		/**
		 * ���̽���
		 */
		task = taskService.createTaskQuery().
				processInstanceId(processInstance.getId()).singleResult();
		System.out.println("���̽�����"+task);
		engine.close();
		System.exit(0);
	}
}
