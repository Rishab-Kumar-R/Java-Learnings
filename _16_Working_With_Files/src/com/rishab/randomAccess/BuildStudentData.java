package com.rishab.randomAccess;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildStudentData {
    public static void build(String dataFileName, boolean separateIndex) {
        Path studentJson = Path.of("student-backup.json");
        String dataFile = dataFileName + ".dat";
        Map<Long, Long> indexedIds = new LinkedHashMap<>();

        try {
            Files.deleteIfExists(Path.of(dataFile));
            String data = Files.readString(studentJson);
            data = data.replaceFirst("^(\\[)", "")
                .replaceFirst("(\\])$", "");
            var records = data.split(System.lineSeparator());
            System.out.println("Number of records: " + records.length);

            long startingPos = separateIndex ? 0 : 4 + (16L * records.length);

            Pattern idPattern = Pattern.compile("studentId\":([0-9]+)");
            try (RandomAccessFile raf = new RandomAccessFile(dataFile, "rw")) {
                raf.seek(startingPos);
                for (String record : records) {
                    Matcher matcher = idPattern.matcher(record);
                    if (matcher.find()) {
                        long id = Long.parseLong(matcher.group(1));
                        indexedIds.put(id, raf.getFilePointer());
                        raf.writeUTF(record);
                    }
                }
                writeIndex((separateIndex) ?
                    new RandomAccessFile(dataFileName + ".idx", "rw") : raf, indexedIds);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeIndex(RandomAccessFile raf, Map<Long, Long> indexMap) {
        try {
            raf.seek(0);
            raf.writeInt(indexMap.size());
            for (Map.Entry<Long, Long> entry : indexMap.entrySet()) {
                raf.writeLong(entry.getKey());
                raf.writeLong(entry.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
