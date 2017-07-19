package com.auto.test.common.context;

import java.util.Set;
import java.util.HashSet;

public class ApiApplication {
	
	private Set<Integer> set = new HashSet<Integer>();

	public ApiApplication() {
		super();
	}
	
	public boolean contain(Integer id){
		return this.set.contains(id);
	}
	
	public boolean add(Integer id){
		return this.set.add(id);
	}
	
	public boolean remove(Integer id){
		return this.set.remove(id);
	}
	
	public void clear(){
		this.set.clear();
	}

	public Set<Integer> getSet() {
		return this.set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
}
