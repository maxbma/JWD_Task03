package by.tc.task03.factory;

import java.util.Map;

import by.tc.task03.entity.Node;

public class NodeFactory {
	public Node createNode(Map<String, Object> creationData) {
		Node node = new Node();
		
		node.setName((String)creationData.get("Name"));
		
		if(creationData.containsKey("Content")) {
			node.setContent((String)creationData.get("Content"));
		}	
		return node;
	}
}
