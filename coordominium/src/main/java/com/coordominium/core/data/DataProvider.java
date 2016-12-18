package com.coordominium.core.data;

import java.util.Collection;

public interface DataProvider<R, P> {
	
	Collection<R> get(P parameters);
	
}
