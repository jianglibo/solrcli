package com.mymock.solrcli.vo;

import java.util.Map;

/**
 * https://cwiki.apache.org/confluence/display/solr/Collections+API
 * @author jianglibo@gmail.com
 *
 */
public class CollectionSubCompleter {

	private Map<String, Map<String, UrlParameter>> actions;

	public Map<String, Map<String, UrlParameter>> getActions() {
		return actions;
	}

	public void setActions(Map<String, Map<String, UrlParameter>> actions) {
		this.actions = actions;
	}


	
	
}
