package util;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

@SuppressWarnings("deprecation")
public class GeraBanco {

	
	public static void main(String[] args) {
		
		// cria uma configuracao
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		
		// le o hibernate.cfg.xml
		configuration.configure();
		
		// cria o esquema
		SchemaExport se = new SchemaExport(configuration);
		
		// Executa
		// 1 true - Para mostrar o comando SQL no console
		// 2 true - Para executar o script criado
		se.create(true, true);
	}
}
