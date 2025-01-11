package testData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Getjsondata {
	
	public Getjsondata()
	{}
	
	public Map<String, String> getjsondata(String testcasename,String filename) throws IOException
	{
		System.out.println(testcasename);
		System.out.println(filename);
		String filedata = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//main//java//testData//"+filename),StandardCharsets.UTF_8);
		ObjectMapper objectmapper = new ObjectMapper();
		
		JsonNode objtree = objectmapper.readTree(filedata);
		JsonNode objtcname = objtree.path(testcasename);
		String tcname = objtcname.toString();
		
		Map<String, String> mapobj = objectmapper.readValue(tcname, new TypeReference<Map<String,String>>(){});
		return mapobj;
	}

}
