package com.mymock.solrcli.completer;

import static jline.internal.Preconditions.checkNotNull;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jline.console.completer.Completer;
import jline.internal.Configuration;

public class MyFileNameCompleter implements Completer {
	
	private static Logger LOGGER = LogManager.getLogger(MyFileNameCompleter.class);

    private static final boolean OS_IS_WINDOWS;

    static {
        String os = Configuration.getOsName();
        OS_IS_WINDOWS = os.contains("windows");
    }

    public int complete(String buffer, final int cursor, final List<CharSequence> candidates) {
        // buffer can be null
        checkNotNull(candidates);

        if (buffer == null) {
            buffer = "";
        }

        if (OS_IS_WINDOWS) {
            buffer = buffer.replace('/', '\\');
        }

        String translated = buffer;

        File homeDir = getUserHome();

        // Special character: ~ maps to the user's home directory
        if (translated.startsWith("~" + separator())) {
            translated = homeDir.getPath() + translated.substring(1);
        }
        else if (translated.startsWith("~")) {
            translated = homeDir.getParentFile().getAbsolutePath();
        }
        else if (!(new File(translated).isAbsolute())) {
            String cwd = getUserDir().getAbsolutePath();
            translated = cwd + separator() + translated;
        }

        File file = new File(translated);
        final File dir;

        if (translated.endsWith(separator())) {
            dir = file;
        }
        else {
            dir = file.getParentFile();
        }

        File[] entries = dir == null ? new File[0] : dir.listFiles();

        return matchFiles(buffer, translated, entries, candidates);
    }

    protected String separator() {
        return File.separator;
    }

    protected File getUserHome() {
        return Configuration.getUserHome();
    }

    protected File getUserDir() {
        return new File(".");
    }

    protected int matchFiles(final String buffer, final String translated, final File[] files, final List<CharSequence> candidates) {
        if (files == null) {
            return -1;
        }
        String trimedTranslated = translated.trim();
        int matches = 0;

        // first pass: just count the matches
        for (File file : files) {
            if (file.getAbsolutePath().startsWith(trimedTranslated)) {
                matches++;
            }
        }
        for (File file : files) {
            if (file.getAbsolutePath().startsWith(trimedTranslated)) {
                CharSequence name = file.getName() + ((matches == 1 && file.isDirectory()) ? separator() : " ");
                LOGGER.error("name lentgh: {}", name.length());
                candidates.add(render(file, name).toString());
            }
        }

        final int index = buffer.lastIndexOf(separator());
//        return index;
        int i = index + separator().length();
        LOGGER.error("buffer: {}", buffer);
        LOGGER.error("return i is: {}", i);
        return i;
    }

    protected CharSequence render(final File file, final CharSequence name) {
        return name;
    }
}
