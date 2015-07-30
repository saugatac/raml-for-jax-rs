package org.raml.jaxrs.codegen.model;

import com.mulesoft.jaxrs.raml.annotation.model.ITypeParameter;

public class TypeParameterModel implements ITypeParameter{
	
	protected String name;
	protected String fullname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	
}
