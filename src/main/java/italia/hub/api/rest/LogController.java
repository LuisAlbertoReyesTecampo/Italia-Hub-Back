package italia.hub.api.rest;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Api(value = "log")
@RequestMapping(value = "/log")
public class LogController {

    @GetMapping(value = "/getLogs", produces = "application/json; charset=utf-8")
    public Set<String> getLogs(){
        Set<String> fileList = new HashSet<>();
        try {
            Files.walkFileTree(Paths.get("C:\\IntelliJ\\Projects\\italia-hub-back\\log"), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                        throws IOException {
                    if (!Files.isDirectory(file)) {
                        fileList.add(file.getFileName().toString());
                        fileList
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }
}
