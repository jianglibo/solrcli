package com.mymock.solrcli.completer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class TestLog {
	
	private static final Logger logger = LogManager.getLogger(TestLog.class);
	
	@Test
	public void t() {
		logger.info("hello");
	}

}
