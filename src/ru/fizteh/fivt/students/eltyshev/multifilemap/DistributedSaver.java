package ru.fizteh.fivt.students.eltyshev.multifilemap;

import ru.fizteh.fivt.students.eltyshev.filemap.base.AbstractTable;
import ru.fizteh.fivt.students.eltyshev.filemap.base.FilemapWriter;
import ru.fizteh.fivt.students.eltyshev.filemap.base.SimpleTableBuilder;
import ru.fizteh.fivt.students.eltyshev.filemap.base.TableBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DistributedSaver {
    private static final int BUCKET_COUNT = 16;
    private static final int FILES_PER_DIR = 16;

    public static void save(TableBuilder builder) throws IOException
    {
        File tableDirectory = builder.getTableDirectory();
        ArrayList<Set<String>> keysToSave = new ArrayList<Set<String>>();
        boolean isBucketEmpty;

        for (int bucketNumber = 0; bucketNumber < BUCKET_COUNT; ++bucketNumber) {
            keysToSave.clear();
            for (int index = 0; index < FILES_PER_DIR; ++index) {
                keysToSave.add(new HashSet<String>());
            }
            isBucketEmpty = true;

            for (final String key : builder.getKeys()) {
                if (getDirNumber(key) == bucketNumber) {
                    int fileNumber = getFileNumber(key);
                    keysToSave.get(fileNumber).add(key);
                    isBucketEmpty = false;
                }
            }

            String bucketName = String.format("%d.dir", bucketNumber);
            File bucketDirectory = new File(tableDirectory, bucketName);

            if (isBucketEmpty) {
                MultifileMapUtils.deleteFile(bucketDirectory);
            }

            for (int fileNumber = 0; fileNumber < FILES_PER_DIR; ++fileNumber) {
                String fileName = String.format("%d.dat", fileNumber);
                File file = new File(bucketDirectory, fileName);
                if (keysToSave.get(fileNumber).isEmpty()) {
                    MultifileMapUtils.deleteFile(file);
                    continue;
                }
                if (!bucketDirectory.exists()) {
                    bucketDirectory.mkdir();
                }
                FilemapWriter.saveToFile(file.getAbsolutePath(), keysToSave.get(fileNumber), builder);
            }
        }
    }

    private static int getDirNumber(String key) {
        byte[] bytes = key.getBytes(AbstractTable.CHARSET);
        int firstSymbol = Math.abs(bytes[0]);
        return firstSymbol % BUCKET_COUNT;
    }

    private static int getFileNumber(String key) {
        byte[] bytes = key.getBytes(AbstractTable.CHARSET);
        int firstSymbol = Math.abs(bytes[0]);
        return firstSymbol / BUCKET_COUNT % FILES_PER_DIR;
    }
}
