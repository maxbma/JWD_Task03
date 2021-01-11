package by.tc.task03.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NodeReader {
public List<String> readFromFile() throws IOException{
		
		List<String> result = new ArrayList<>();
		String line = null;
		File file = new File(getClass().getResource("/task03.xml").getPath());
        FileReader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        line = reader.readLine();
        
        while(line!=null) {
        	if(line.isBlank()) {
        		line = reader.readLine();
        		continue;
        	}
        	String[] checker = line.split(">");
            if(checker.length > 1) {
            	if(!checker[1].contains("<")) {
            		line += reader.readLine();
            		while(!line.endsWith(">")) {
            			line += reader.readLine();
            		}
            	}
            }
            
        	result.add(line);
        	line = reader.readLine();
        }
        
        reader.close();
        fr.close();
        
        return result;
	}
}
