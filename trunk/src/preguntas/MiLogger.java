package preguntas;

import java.util.logging.Logger;

public class MiLogger extends Logger{
	
	private static MiLogger singleton = null;
	
		
	private MiLogger(String name) {
		super(name,null);
	}
	
	public static MiLogger getInstancia(String name) {
		if (singleton == null)
			singleton = new MiLogger(name);
		return singleton;
	}
	
}
