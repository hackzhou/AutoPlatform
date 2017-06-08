package com.auto.test.common.context;

import java.util.Set;
import java.util.HashSet;

public class ApiApplication {
	
	Set<Integer> set = new HashSet<Integer>();

	public ApiApplication() {
		super();
	}
	
	public boolean contain(Integer id){
		return set.contains(id);
	}
	
	public boolean add(Integer id){
		return set.add(id);
	}
	
	public boolean remove(Integer id){
		return set.remove(id);
	}
	
	public void clear(){
		set.clear();
	}

	public Set<Integer> getSet() {
		return set;
	}
	public void setSet(Set<Integer> set) {
		this.set = set;
	}
}
