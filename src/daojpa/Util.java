/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package daojpa;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Util {

	//utiliza as configuracoes do log4j.properties
	protected static final Log logger = LogFactory.getLog(DAO.class);

	public static EntityManager conectarBanco(){
		EntityManager manager = null;
		EntityManagerFactory factory;
		
		try {
			// ler dados do arquivo dados.properties
			Properties dados = new Properties();
			logger.info("DAO.open - lendo arquivo dados.properties: ");
			dados.load(DAO.class.getResourceAsStream("/dados.properties"));  //dentro de src
			String sgbd = dados.getProperty("sgbd");
			String provedor = dados.getProperty("provedor") ;
			String ip = dados.getProperty("ip");
			String banco = dados.getProperty("banco");
			logger.info("unidade de persistencia selecionada=> "+ provedor);

			// reconfigurar algumas propriedades do persistence.xml
			Properties configuracoes = new Properties();
			if(sgbd.equals("postgresql")) {
				logger.info("configurando postgresql");
				configuracoes.setProperty("jakarta.persistence.jdbc.driver",  "org.postgresql.Driver" );
				configuracoes.setProperty("jakarta.persistence.jdbc.url", "jdbc:postgresql://"+ip+":5432/"+banco);
				configuracoes.setProperty("jakarta.persistence.jdbc.user", "postgres");
				configuracoes.setProperty("jakarta.persistence.jdbc.password", "admin");
				if(provedor.equals("hibernate")) {
					configuracoes.setProperty("hibernate.dialect",  "org.hibernate.dialect.PostgreSQLDialect" );
				}
			}
			if(sgbd.equals("mysql")) {
				logger.info("configurando mysql");
				configuracoes.setProperty("jakarta.persistence.jdbc.driver",  "com.mysql.cj.jdbc.Driver" );
				configuracoes.setProperty("jakarta.persistence.jdbc.url", "jdbc:mysql://"+ip+":3306/"+banco+"?createDatabaseIfNotExist=true");
				configuracoes.setProperty("jakarta.persistence.jdbc.user", "root");
				configuracoes.setProperty("jakarta.persistence.jdbc.password", "");
				if(provedor.equals("hibernate")) {
					configuracoes.setProperty("hibernate.dialect",  "org.hibernate.dialect.MySQLDialect" );
				}
			}


			factory = Persistence.createEntityManagerFactory(provedor, configuracoes);
			manager = factory.createEntityManager();
		}
		catch (Exception e) {
			logger.info("DAO.open - problema de configuracao: "+ e.getMessage());
			System.exit(0);
		} 
		return manager;
	}

}
