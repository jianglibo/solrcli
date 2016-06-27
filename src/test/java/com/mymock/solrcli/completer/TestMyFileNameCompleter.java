package com.mymock.solrcli.completer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestMyFileNameCompleter {

	@Test
	public void emptyBuffer() {
		MyFileNameCompleter mfnc = new MyFileNameCompleter();
		final List<CharSequence> candinats = new ArrayList<>();
		int i = mfnc.complete(null, 1, candinats);
		assertThat("", i, equalTo(0));
		assertThat("src", candinats.size(), equalTo(17)); //include self.
	}
	
	@Test
	public void srcBuffer() {
		MyFileNameCompleter mfnc = new MyFileNameCompleter();
		final List<CharSequence> candinats = new ArrayList<>();
		int i = mfnc.complete("src", 1, candinats);
		assertThat("", i, equalTo(0));
		assertThat("src", candinats.size(), equalTo(1)); //include self.
	}

	@Test
	public void srcSlashBuffer() {
		MyFileNameCompleter mfnc = new MyFileNameCompleter();
		final List<CharSequence> candinats = new ArrayList<>();
		int i = mfnc.complete("src/", 1, candinats);
		assertThat("", i, equalTo(4));
		assertThat("src", candinats.size(), equalTo(2)); //include self.
	}
}
