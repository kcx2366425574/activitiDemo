package connection;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;

/**
 * @date 2019-11-24
 * @author kcx
 * @description 
 */
public class TestConnect {
	public static void main(String[] args) {
		ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
			      .setJdbcUrl("jdbc:mysql://localhost/act?useUnicode=true&serverTimezone=GMT")
			      .setJdbcUsername("root")
			      .setJdbcPassword("root")
			      .setJdbcDriver("com.mysql.jdbc.Driver")
			      .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
			    ProcessEngine processEngine = cfg.buildProcessEngine();
			    String pName = processEngine.getName();
			    String ver = ProcessEngine.VERSION;
			    System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");

	}
}
