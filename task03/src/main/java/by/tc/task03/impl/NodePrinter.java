package by.tc.task03.impl;

import by.tc.task03.entity.Node;

public class NodePrinter {
	public String printNode(Node node, int level) {
		
		String tab = new String();
		for(int i =0; i < level; i++) {
			tab+="\t";
		}
		
		String output = tab + node.getName();
		if(node.getChildNodes() == null) {
			output += " ";
			output += node.getContent() + "\n";
		} else {
			for(Node childNode : node.getChildNodes()) {
				if(!output.endsWith("\n")) {
					output += "\n";
				}
				level++;
				output += printNode(childNode, level);
				level--;
			}
		}
		
		return output;
	}
	
	public String printNode(Node node) {
		return printNode(node, 0);
	}
}
