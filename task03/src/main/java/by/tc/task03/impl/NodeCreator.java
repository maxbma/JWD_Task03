package by.tc.task03.impl;

import java.util.*;

import by.tc.task03.entity.Attribute;
import by.tc.task03.entity.Node;
import by.tc.task03.factory.AttributeFactory;
import by.tc.task03.factory.NodeFactory;

public class NodeCreator {
	
	private static final String TAG_END;
	private static final String TAG_BEGIN;
	private static final String CLOSING_TAG_SIGN;
	private static final String ATTRIBUTE_EQUAL;
	private static final String ATTRIBUTE_SPLITTER;
	private static final String ATTRIBUTE_IDENTIFIER;
	
	static {
		TAG_END = ">";
		TAG_BEGIN = "<";
		CLOSING_TAG_SIGN = "/";
		ATTRIBUTE_EQUAL = "=";
		ATTRIBUTE_SPLITTER = " ";
		ATTRIBUTE_IDENTIFIER = "\"";
	}
	
	public Node splitFileData(List<String> data) {
		
		Node result = null;
		Node node = null;
		Node parentNode = null;
		AttributeFactory attributeCreator = new AttributeFactory();
		NodeFactory nodeCreator = new NodeFactory();
		Stack<Node> stack = new Stack<>();
		String line = null;
		
		for(int i = 0; i < data.size(); i++) {
			Map<String, Object> nodeMap = new HashMap<>();
			
			line = data.get(i);
			String temp[] = line.split(TAG_END);
			if(temp.length > 1) {
				temp[0] = temp[0].substring(temp[0].indexOf(TAG_BEGIN) + 1);
				nodeMap.put("Name", temp[0]);
				nodeMap.put("Content", temp[1].split(TAG_BEGIN)[0]);
				node = nodeCreator.createNode(nodeMap);
				parentNode = stack.pop();
				if(parentNode.getChildNodes() == null) {
					parentNode.setChildNodes(new ArrayList<Node>());
				}
				List<Node> childNodes = parentNode.getChildNodes();
				childNodes.add(node);
				parentNode.setChildNodes(childNodes);
				stack.add(parentNode);
			} else {
				temp[0] = temp[0].substring(temp[0].indexOf(TAG_BEGIN) + 1);
				if(!temp[0].contains(CLOSING_TAG_SIGN)) {
					
					if(temp[0].contains(ATTRIBUTE_IDENTIFIER)) {
						String[] lineParts = temp[0].split(ATTRIBUTE_SPLITTER);
						nodeMap.put("Name", lineParts[0]);
						node = nodeCreator.createNode(nodeMap);
						List<Attribute> attributes = new ArrayList<>();
						
						for(int j = 1; j < lineParts.length; j++){
							Map<String, Object> attributeMap = new HashMap<>();
							Attribute attribute = new Attribute();
							
							String[] attributeParts = lineParts[j].split(ATTRIBUTE_EQUAL);
							attributeMap.put("Attribute name", attributeParts[0]);
							attributeMap.put("Attribute content", attributeParts[1].substring(1,attributeParts[1].length()-1));
							
							attribute = attributeCreator.createAttribute(attributeMap);
							attributes.add(attribute);
						}
						
						node.setAttributes(attributes);
						
						stack.add(node);
					} else {
						nodeMap.put("Name", temp[0]);
						node = nodeCreator.createNode(nodeMap);
						stack.add(node);
					}
				} else {
					node = stack.pop();
					if(!stack.empty()) {
						parentNode = stack.pop();
						if(parentNode.getChildNodes() == null) {
							parentNode.setChildNodes(new ArrayList<Node>());
						}
						List<Node> childNodes = parentNode.getChildNodes();
						childNodes.add(node);
						parentNode.setChildNodes(childNodes);
						stack.add(parentNode);
					} else {
						return node;
					}
				}
			}
		}
		return result;
	}
}
