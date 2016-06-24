package com.mymock.solrcli.completer;

import static jline.internal.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jline.console.completer.Completer;

public class MyStringCompleter implements Completer {
	
	private static Logger LOGGER = LogManager.getLogger(MyStringCompleter.class);

    private final SortedSet<String> strings = new TreeSet<String>();

    public MyStringCompleter() {
        // empty
    }

    public MyStringCompleter(final Collection<String> strings) {
        checkNotNull(strings);
        getStrings().addAll(strings);
    }

    public MyStringCompleter(final String... strings) {
        this(Arrays.asList(strings));
        LOGGER.error("init:{}", "MyStringCompleter");
    }

    public Collection<String> getStrings() {
        return strings;
    }
    
    /**
     * we fill candidates here. Caller receive it. 
     */
    @Override
    public int complete(final String buffer, final int cursor, final List<CharSequence> candidates) {
    	LOGGER.error("buffer: {}", buffer);
    	LOGGER.error("cursor: {}", cursor);
    	
        // buffer could be null
        checkNotNull(candidates);
        

        if (buffer == null) {
            candidates.addAll(strings);
        }
        else {
            for (String match : strings.tailSet(buffer)) {
                if (!match.startsWith(buffer)) {
                    break;
                }

                candidates.add(match);
            }
        }

        for(CharSequence cs : candidates) {
        	LOGGER.error("--{}--", cs);
        }
        int r = candidates.isEmpty() ? -1 : 0;
        LOGGER.error("return from complete:{}", r);
        return r;
    }
}
