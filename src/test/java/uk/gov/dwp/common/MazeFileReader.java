package uk.gov.dwp.common;

import static com.google.common.io.Resources.getResource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MazeFileReader {

    public static List<String> getLinesAsListFromMazeFile(final String fileName) throws IOException, URISyntaxException {
        final Path path = Paths.get(getResource(fileName).toURI());
        try (final Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.toList());
        }
    }

}
