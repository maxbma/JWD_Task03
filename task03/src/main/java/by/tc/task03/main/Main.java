package by.tc.task03.main;

import java.io.IOException;
import by.tc.task03.entity.Node;

import by.tc.task03.impl.NodeImpl;
import by.tc.task03.impl.NodePrinter;

public class Main {
	public static void main(String[] args) throws IOException {
		NodeImpl nodeCreation = new NodeImpl();
		NodePrinter printer = new NodePrinter();
		
		Node node = nodeCreation.nodeImpl();
		System.out.println(printer.printNode(node));
	}
}
