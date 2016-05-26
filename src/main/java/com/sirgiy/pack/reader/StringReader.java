package com.sirgiy.pack.reader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * StringReader Class
 * for reading from the string
 */
public class StringReader implements IReader {

    private static final int NULL_CHARACTER = 0;

    private InputStream inputStream;
    private int nextSymbol;
    private boolean hasNext;

    /**
     * Default constructor
     * @param stringData input data
     * @throws ReaderException exception handling
     */
    public StringReader(final String stringData) throws ReaderException {
        try {
            inputStream = new ByteArrayInputStream(stringData.getBytes("UTF8"));
            hasNext = true;
        } catch (Exception e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Checks existence of the following character
     * @throws ReaderException if file not found
     */
    private void hasnextsymbol() throws ReaderException {
        int nextInt;
        try {
            nextInt = inputStream.read();
            if (nextInt == -1) {
                nextSymbol = NULL_CHARACTER;
                hasNext = false;
            } else {
                nextSymbol = nextInt;
                hasNext = true;
            }
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Return true if has next character
     * @throws ReaderException if file not found
     * @return true if has following character
     */
    @Override
    public boolean hasnext() throws ReaderException {
        if (hasNext) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Reading the following character
     * @return following character
     * @throws ReaderException if string is not read
     */
    @Override
    public char readnext() throws ReaderException {
        hasnextsymbol();
        return (char) nextSymbol;
    }

    /**
     * Closing an input stream
     * @throws ReaderException if the stream is not close
     */
    @Override
    public void close() throws ReaderException {
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }
}
