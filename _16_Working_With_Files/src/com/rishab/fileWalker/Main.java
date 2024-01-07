package com.rishab.fileWalker;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        Path startingPath = Path.of("..");
        FileVisitor<Path> statsVisitor = new StatsVisitor(0);

        try {
            Files.walkFileTree(startingPath, statsVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static class StatsVisitor extends SimpleFileVisitor<Path> {
        private Path initialPath = null;
        private final Map<Path, Long> folderSizes = new LinkedHashMap<>();
        private int initialCount;
        private int printLevel;

        public StatsVisitor(int printLevel) {
            this.printLevel = printLevel;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);

            this.folderSizes.merge(file.getParent(), 0L, (oldValue, newValue) -> oldValue += attrs.size());

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);

            if (this.initialPath == null) {
                this.initialPath = dir;
                this.initialCount = dir.getNameCount();
            } else {
                int relativeLevel = dir.getNameCount() - this.initialCount;
                if (relativeLevel == 1) {
                    this.folderSizes.clear();
                }
                this.folderSizes.put(dir, 0L);
            }

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Objects.requireNonNull(dir);
            // if (exc != null)
            //     throw exc;

            if (dir.equals(this.initialPath)) {
                return FileVisitResult.TERMINATE;
            }

            int relativeLevel = dir.getNameCount() - this.initialCount;
            if (relativeLevel == 1) {
                this.folderSizes.forEach((k, v) -> {
                    int level = k.getNameCount() - this.initialCount - 1;
                    if (level <= this.printLevel) {
                        System.out.printf("%s[%s] - %,d bytes%n", "\t".repeat(level), k.getFileName(), v);
                    }
                });
            } else {
                long folderSize = this.folderSizes.get(dir);
                this.folderSizes.merge(dir.getParent(), 0L, (oldValue, newValue) -> oldValue += folderSize);
            }

            return FileVisitResult.CONTINUE;
        }
    }
}
