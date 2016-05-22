package com.sirgiy.pack.reader;

/**
 * ICloseable interface
 * For closing the input stream
 */
public interface ICloseable {

    /**
     * Closing the input stream
     * @throws ReaderException if the stream is not close
     */
    void close() throws ReaderException;
}
