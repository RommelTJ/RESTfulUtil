package edu.sandiego.restfulUtil.resources;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class USDRESTfulUtilApplication extends Application {
	
	public USDRESTfulUtilApplication(){};
	
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		set.add(USDRESTfulUtil.class);
		return set;
	}

}
