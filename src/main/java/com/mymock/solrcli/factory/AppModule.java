package com.mymock.solrcli.factory;

import java.io.IOException;

import org.apache.http.client.fluent.Executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.mymock.solrcli.completer.MyFileNameCompleter;
import com.mymock.solrcli.hc.MyHttpClient;
import com.mymock.solrcli.vo.CollectionSubCompleter;

import jline.console.ConsoleReader;
import jline.console.completer.CandidateListCompletionHandler;
import jline.console.completer.Completer;

public class AppModule  extends AbstractModule {

	@Override
	protected void configure() {
		bind(Completer.class).to(MyFileNameCompleter.class).in(Singleton.class);
	}
	
	@Provides @Singleton
	ConsoleReader providerConsoleReader(Completer compl) {
        ConsoleReader reader;
		try {
			reader = new ConsoleReader();
	        CandidateListCompletionHandler cch = new CandidateListCompletionHandler();
	        cch.setPrintSpaceAfterFullCompletion(false);
	        reader.setCompletionHandler(cch);
	        reader.setPrompt("prompt> ");
	        reader.addCompleter(compl);
	        return reader;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Provides @Singleton
	Executor providerExecutor() {
		return Executor.newInstance(new MyHttpClient().getHttpClient());
	}
	
	@Provides @Singleton
	CollectionSubCompleter providerCollectionSubCompleter(@Named("yml") ObjectMapper mapper) {
		try {
			return mapper.readValue(this.getClass().getResourceAsStream("/collection-parameternames.yml"), CollectionSubCompleter.class);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Provides @Singleton @Named("yml")
	ObjectMapper providerYmlObjectMapper() {
		return new ObjectMapper(new YAMLFactory());
	}

}
