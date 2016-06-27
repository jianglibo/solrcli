package com.mymock.solrcli;

import java.io.IOException;
import java.io.PrintWriter;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import jline.console.ConsoleReader;

@Singleton
public class SolrCliApp {
	
	private final ConsoleReader reader;

	@Inject
	public SolrCliApp(ConsoleReader consoleReader) {
		this.reader = consoleReader;
	}
	
	public void run() throws IOException {
        String line;
        PrintWriter out = new PrintWriter(reader.getOutput());

        while ((line = reader.readLine()) != null) {
            out.println("======>\"" + line + "\"");
            out.flush();
            if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
            	reader.close();
                break;
            }
            if (line.equalsIgnoreCase("cls")) {
                reader.clearScreen();
            }
        }	
	}
}
