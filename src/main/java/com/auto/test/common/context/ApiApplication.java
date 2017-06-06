package com.auto.test.common.context;

import java.util.ArrayList;
import java.util.List;

public class ApiApplication {
	
	List<Integer> list = new ArrayList<Integer>();

	public ApiApplication() {
		super();
	}
	
	public boolean contain(Integer id){
		return list.contains(id);
	}
	
	public boolean add(Integer id){
		return list.add(id);
	}
	
	public boolean remove(Integer id){
		return list.remove(id);
	}
	
	public void clear(){
		list.clear();
	}

	public List<Integer> getList() {
		return list;
	}
	public void setList(List<Integer> list) {
		this.list = list;
	}

}
