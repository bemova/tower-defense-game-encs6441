package core.domain.warriors.defenders.towers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Utility {
	public List<String> getAllFeatures(){
		List<String> lst = new ArrayList<>();
		File dir = new File("./src/defenders/towers/features");
		File[] files = dir.listFiles();
		for (File file : files) {
			if(!file.isDirectory()){
				String name = file.getName();
				String[] str = name.split(".j");
				lst.add(str[0]);
			}
		}
		return lst;
	}
	public static String getDetail(Map<String, Map<String, Integer>> map){
		String str = "";
		for (Entry<String, Map<String, Integer>> entry : map.entrySet()) {
			str += entry.getKey();
			Map<String,Integer> value = entry.getValue();
			for (Map.Entry<String, Integer> element : value.entrySet()) {
				str += "," + element.getValue() + "X" +element.getKey();
			}
		}
		return str;
	}
}