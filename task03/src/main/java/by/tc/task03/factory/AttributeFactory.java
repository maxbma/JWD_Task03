package by.tc.task03.factory;

import java.util.Map;

import by.tc.task03.entity.Attribute;

public class AttributeFactory {
	public Attribute createAttribute(Map<String, Object> attributeMap) {
		Attribute attribute = new Attribute();
		
		attribute.setName((String)attributeMap.get("Attribute name"));
		attribute.setContent((String)attributeMap.get("Attribute content"));
		
		return attribute;
	}
}
