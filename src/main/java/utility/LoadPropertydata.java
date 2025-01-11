package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadPropertydata {
	
	private static String brow;
	private static String Url;
	Properties prop;
	
	public LoadPropertydata() throws IOException{
	
		prop = new Properties();
		try {
			System.out.println(System.getProperty("user.dir"));
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//utility//config.properties");
			prop.load(ip);
			setBrow(prop.getProperty("browser"));
			setUrl(prop.getProperty("url"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getBrow() {
		return brow;
	}

	public static void setBrow(String brow) {
		LoadPropertydata.brow = brow;
	}

	public static String getUrl() {
		return Url;
	}

	public static void setUrl(String url) {
		LoadPropertydata.Url = url;
	}

}
