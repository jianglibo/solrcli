package com.mymock.solrcli.completer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestMyFileNameCompleter {

	@Test
	public void t() {
		MyFileNameCompleter mfnc = new MyFileNameCompleter();
		final List<CharSequence> candinats = new ArrayList<>();
		int i = mfnc.complete("src", 1, candinats);
		assertThat("", i, equalTo(-1));
		assertThat("src", candinats.size(), equalTo(2));
		
	}
}
