package preguntas;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * La clase ArchivosPropiedades.
 */
public class PropertiesFile {
	
	/** El fichero. */
	FileInputStream file = null;
	
	 /**
 	 * Obtiene las propiedades del fichero properties.
 	 *
 	 * @return the properties
 	 */
 	public Properties getProperties() 
	  {
	        try
	        {
	        	 file = new FileInputStream("configuracion/parametros.properties");
	            //se crea una instancia a la clase Properties
	            Properties properties = new Properties();
	            //se leen el archivo .properties
	            properties.load( file );
	            //si el archivo de propiedades NO esta vacio retornan las propiedes leidas
	            if (!properties.isEmpty()) 
	            {                
	                return properties;
	            } else {//sino  retornara NULL
	                return null;
	            }
	        } catch (IOException ex) {
	            return null;
	        }
	   }
}
