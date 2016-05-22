package com.sirgiy.pack.reader;

/**
 * IReader interface
 * For reading the following character from the file or a line
 */
public interface IReader {

    /**
     * Reading the following character
     * @throws ReaderException if file not found
     * @return following character
     */
    char readnext() throws ReaderException;

    /**
     * Return true if has next character
     * @throws ReaderException if file not found
     * @return true if has following character
     */
    boolean hasnext() throws ReaderException;
}
