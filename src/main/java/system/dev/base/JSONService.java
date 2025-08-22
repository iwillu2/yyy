package system.dev.base;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JSONService {

	public List<JSONPeople> readJsonArray(String Path) {
		ObjectMapper objMap = new ObjectMapper();
		JSONPeople jpeople;
		try {
			File f = new ClassPathResource(Path).getFile();
			List<JSONPeople> retList = Arrays.asList(objMap.readValue(f, JSONPeople[].class));
			return retList;
		} catch(Exception e) {
			return null;
		}
	}
	
}
