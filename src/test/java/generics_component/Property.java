package generics_component;

import java.io.FileInputStream;
import java.util.Properties;

public class Property {
	
	public static String getPropertyValue(String path, String key)
	{
		String prop = "";
		
		Properties ppt = new Properties();
				
		try
		{
			ppt.load(new FileInputStream(path));
			prop = ppt.getProperty(key);			
		} catch(Exception e)
		{
			e.printStackTrace();
		}	
		return prop;
	}
}