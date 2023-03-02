package Runners;

import java.io.IOException;
import java.util.stream.Stream;

import settings.config.Actition;

public class Run {
	public static void Runners(Actition Action, String uri) {
		String command;
		if (Action == Actition.BROW) {
			command="chrome/Chrome.exe --start-maximized --app="+uri;
		}else {
			command=uri;
		}
		try
		{ 
        // Running the above command 
			Runtime run  = Runtime.getRuntime(); 
			Process proc = run.exec(command); 

		} 

		catch (IOException e) 
		{ 
			e.printStackTrace(); 
		} 
	}
}
