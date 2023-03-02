package Runners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;

public class User  {

	static public void Undecorate(int proc) {

		User32.INSTANCE.EnumWindows(new WNDENUMPROC() {
				int count = 0;
		         @Override
		         public boolean callback(HWND hWnd, Pointer arg1) {
		        	IntByReference IdByRef = new IntByReference();
		        	int processid = User32.INSTANCE.GetWindowTextLength(hWnd) + 1;
		        	char[] title = new char[processid];
		        	User32.INSTANCE.GetWindowText(hWnd, title, processid);

		            if (title.length==0) {
		               return true;
		            }
		            System.out.println(String.copyValueOf(title));
		            if (processid==proc) {
		            	System.out.println("equal");
		            	final int GWL_STYLE = -16;
		            	final long WS_MINIMIZEBOX = 0x00020000L;
		            	final long WS_MAXIMIZEBOX = 0x00010000L;
		            	long value = User32.INSTANCE.GetWindowLong(hWnd, GWL_STYLE);
		            	User32.INSTANCE.EnumWindows(null, null);
		            	User32.INSTANCE.SetWindowLong(hWnd, GWL_STYLE, (int)(value & ~WS_MINIMIZEBOX & ~WS_MAXIMIZEBOX));
		            	return true;
		            }
		            return true;
		         }
			}, null);
        
		
	}
	
	
	static public String getProcessNameFromPID(String pid) {
	    Process p = null;
	    try {
	        p = Runtime.getRuntime().exec("tasklist /v");
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }

	    StringBuffer sbInput = new StringBuffer();
	    BufferedReader brInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
	    String line;
	    int index=0;
	    
	    String foundLine = "UNKNOWN";
	    try {
	        while ((line = brInput.readLine()) != null) {
	        	if (line.contains("T¡tulo")){
	        		index = line.indexOf("T¡tulo"); 
	            }
	            if (line.contains(pid)){
	                foundLine = line; 
	            }
	            sbInput.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    try {
	        p.waitFor();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    p.destroy();

	    String result = foundLine.substring(index);
	    result = result.substring(0, result.indexOf("    "));

	    System.out.print("Teste:"+result);

	    return result;
	}
}
