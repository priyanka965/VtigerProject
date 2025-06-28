package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class consist of method related to read data from property file
 */
public class PropertyFileUtility {
	
	/**
	 * This method is usesd to read data from Property File provided with key
	 * @param Key
	 * @return
	 * @throws IOException
	 */
		public String toReadDataFromPropertyFile(String Key) throws IOException {
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String value = prop.getProperty(Key);
		return value;
		
		
	}

}
