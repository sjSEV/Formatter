package com.sirgiy.pack.writer;

/**
 * IWriter interface
 * For writing the following character to the file
 */
public interface IWriter {

    /**
     * Writing the following character
     * @param stringData the symbol for write
     * @throws WriterException exception handling
     */
    void write(final String stringData) throws WriterException;

    /**
     * Closing an output stream
     * @throws WriterException exception handling
     */
    void close() throws WriterException;
}
