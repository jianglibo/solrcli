package com.mymock.solrcli.hc;

import org.apache.http.client.fluent.Executor;

import com.google.inject.Inject;

public class ApacheHcInvoker implements RestInvoker {
	
	private final Executor executor;
	
	@Inject
	public ApacheHcInvoker(Executor executor) {
		this.executor = executor;
	}

	@Override
	public void invoke(RestResultHandler handler) {

		
	}

}
