package com.sirgiy.pack.writer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * FileWriter Class
 * For writing to a file
 */
public class FileWriter implements IWriter {

    private OutputStream outputStream;

    /**
     * Default constructor
     * @param fileName file name to save result
     * @throws WriterException exception handling
     */
    public FileWriter(final String fileName) throws WriterException {
        try {
            outputStream = new FileOutputStream(new File(fileName));
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }

    /**
     * Writing the following character
     * @param stringData the symbol for write
     * @throws WriterException exception handling
     */
    @Override
    public void write(final String stringData) throws WriterException {
        try {
            outputStream.write(stringData.getBytes());
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }

    /**
     * Closing an output stream
     * @throws WriterException exception handling
     */
    @Override
    public void close() throws WriterException {
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }
}
