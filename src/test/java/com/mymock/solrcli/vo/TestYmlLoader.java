package com.mymock.solrcli.vo;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.mymock.solrcli.factory.AppModule;

public class TestYmlLoader {

	@Test
	public void d() {
		Injector injector = Guice.createInjector(new AppModule());
		CollectionSubCompleter csc = injector.getInstance(CollectionSubCompleter.class);
	}
}
