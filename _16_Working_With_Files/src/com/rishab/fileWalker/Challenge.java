package com.rishab.fileWalker;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Challenge {
    public static void main(String[] args) {

        Path startingPath = Path.of(".");
        FileVisitor<Path> statsVisitor = new StatsVisitor(Integer.MAX_VALUE);

        try {
            Files.walkFileTree(startingPath, statsVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static class StatsVisitor implements FileVisitor<Path> {
        private Path initialPath = null;
        private final Map<Path, Map<String, Long>> folderSizes = new LinkedHashMap<>();
        private int initialCount;
        private int printLevel;

        private static final String DIR_COUNT = "DirCount";
        private static final String FILE_SIZE = "FileSize";
        private static final String FILE_COUNT = "FileCount";

        public StatsVisitor(int printLevel) {
            this.printLevel = printLevel;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);

            var parentMap = folderSizes.get(file.getParent());
            if (parentMap != null) {
                long fileSize = attrs.size();
                parentMap.merge(FILE_SIZE, fileSize, (oldValue, newValue) -> oldValue += newValue);
                parentMap.merge(FILE_COUNT, 1L, Math::addExact);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            Objects.requireNonNull(file);

            if (exc != null) {
                System.out.println(exc.getClass().getSimpleName() + " - " + file);
            }
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
                this.folderSizes.put(dir, new HashMap<>());
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
                        long size = v.getOrDefault(FILE_SIZE, 0L);
                        System.out.printf("%s[%s] - %,d bytes, %d files, %d folders%n",
                            "\t".repeat(level), k.getFileName(), size,
                            v.getOrDefault(FILE_COUNT, 0L),
                            v.getOrDefault(DIR_COUNT, 0L));
                    }
                });
            } else {
                var parentMap = this.folderSizes.get(dir.getParent());
                var childMap = this.folderSizes.get(dir);

                long folderCount = childMap.getOrDefault(DIR_COUNT, 0L);
                long fileSize = childMap.getOrDefault(FILE_SIZE, 0L);
                long fileCount = childMap.getOrDefault(FILE_COUNT, 0L);

                parentMap.merge(DIR_COUNT, folderCount + 1, (oldValue, newValue) -> oldValue += newValue);
                parentMap.merge(FILE_SIZE, fileSize, Math::addExact);
                parentMap.merge(FILE_COUNT, fileCount, Math::addExact);
            }

            return FileVisitResult.CONTINUE;
        }
    }
}
