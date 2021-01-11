package by.tc.task03.impl;

import java.io.IOException;
import java.util.List;

import by.tc.task03.entity.Node;

public class NodeImpl {
	public Node nodeImpl() throws IOException {
		NodeReader reader = new NodeReader();
		NodeCreator creator = new NodeCreator();
		
		List<String> fileLines = reader.readFromFile();
		Node resultNode = creator.splitFileData(fileLines);
				
		return resultNode;
	}
}
