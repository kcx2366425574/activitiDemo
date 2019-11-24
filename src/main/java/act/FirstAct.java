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
		 * 创建流程引擎
		 */
		ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
		/**
		 * 得到流程存储服务组件
		 */
		RepositoryService repositoryService = engine.getRepositoryService();
		/**
		 * 得到运行时服务组件
		 */
		RuntimeService runtimeService = engine.getRuntimeService();
		
		/**
		 * 获取流程任务组件
		 */
		TaskService taskService = engine.getTaskService();
		/**
		 * 部署流程文件
		 */
		repositoryService.createDeployment().
			addClasspathResource("first.bpmn").deploy();
		/**
		 * 启动流程
		 */
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");
		
		/**
		 * 普通员工填写请假申请
		 */
		Task task = taskService.createTaskQuery().
				processInstanceId(processInstance.getId()).singleResult();
		System.out.println("当前流程节点："+task.getName());
		taskService.complete(task.getId());
		
		/**
		 * 经理审核
		 */
		task = taskService.createTaskQuery().
				processInstanceId(processInstance.getId()).singleResult();
		System.out.println("当前流程节点："+task.getName());
		taskService.complete(task.getId());
		
		/**
		 * 流程结束
		 */
		task = taskService.createTaskQuery().
				processInstanceId(processInstance.getId()).singleResult();
		System.out.println("流程结束："+task);
		engine.close();
		System.exit(0);
	}
}
