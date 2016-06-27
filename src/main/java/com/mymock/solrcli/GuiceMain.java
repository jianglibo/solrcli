package com.mymock.solrcli;

import java.io.IOException;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mymock.solrcli.factory.AppModule;

public class GuiceMain {

	public static void main(String[] args) throws IOException {
		Injector injector = Guice.createInjector(new AppModule());
		
		injector.getInstance(SolrCliApp.class).run();
	}
}
