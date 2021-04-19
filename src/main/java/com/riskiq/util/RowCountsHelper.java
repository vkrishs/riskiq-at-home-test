package com.riskiq.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class RowCountsHelper {

    public long getRowCounts(URL url) throws URISyntaxException, IOException {
        Stream<String> lines = Files.lines(Paths.get(url.toURI()));
        return lines.count();
    }
}
