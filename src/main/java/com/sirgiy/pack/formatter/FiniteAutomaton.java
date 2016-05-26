package com.sirgiy.pack.formatter;

import com.sirgiy.pack.reader.IReader;
import com.sirgiy.pack.reader.ReaderException;

/**
 * FiniteAutomaton Class
 * For formatting the text with using finite automaton
 */
public class FiniteAutomaton implements IFiniteAutomaton {

    private static final int BEGINNING_OF_LINE = 1;
    private static final int MIDDLE_OF_LINE = 2;
    private static final int END_OF_LINE = 3;
    private static final String LINEBREAK = "\r\n";

    private int state;

    private char indent;
    private int countIndent;

    private int level;
    private StringBuilder stringData;
    private boolean ifEndOfString;

    /**
     * Default constructor
     * @param ireader interface IReader for reading the file
     * @throws FiniteAutomatonException exception handling
     */
    public FiniteAutomaton(final IReader ireader) throws FiniteAutomatonException {
        level = 0;
        countIndent = 0;
        state = BEGINNING_OF_LINE;
        ifEndOfString = false;

        try {
            readProperties(ireader);
        } catch (FiniteAutomatonException e) {
            throw new FiniteAutomatonException(e);
        }
    }

    /**
     * Reading the file .properties
     * @param ireader interface IReader for reading the file
     * @throws FiniteAutomatonException exception handling
     */
    private void readProperties(final IReader ireader) throws FiniteAutomatonException {
        try {
            indent = ireader.readnext();
            countIndent = Character.getNumericValue(ireader.readnext());
        } catch (ReaderException e) {
            throw new FiniteAutomatonException(e);
        }
        try {
            ireader.close();
        } catch (ReaderException e) {
            throw new FiniteAutomatonException(e);
        }
    }

    /**
     * Add indents in the blocks
     */
    private void addindent() {
        int count = level * countIndent;
        for (int i = 0; i < count; i++) {
            stringData.insert(0, indent);
        }
    }

    /**
     * Formation of a line
     * @param symbol next character
     */
    @Override
    public void formationline(final char symbol) {
        switch (state) {
            case BEGINNING_OF_LINE:
                stringData = new StringBuilder();
                ifEndOfString = false;
                if (symbol != '\r' & symbol != '\n') {
                    if (symbol != ' ') {
                        if (symbol == ';' | symbol == '{' | symbol == '}') {
                            state = MIDDLE_OF_LINE;
                        } else {
                            stringData.append(symbol);
                            state = MIDDLE_OF_LINE;
                            break;
                        }
                    }
                } else {
                    break;
                }
            case MIDDLE_OF_LINE:
                if (symbol != '\r' & symbol != '\n') {
                    if (symbol == ';') {
                        stringData.append(symbol);
                        addindent();
                        state = END_OF_LINE;
                    } else if (symbol == '{') {
                        stringData.append(symbol);
                        addindent();
                        level++;
                        state = END_OF_LINE;
                    } else if (symbol == '}') {
                        stringData.append(symbol);
                        if (level > 0) {
                            level--;
                        }
                        if (level == 0) {
                            stringData.append(LINEBREAK);
                        }
                        addindent();
                        state = END_OF_LINE;
                    } else {
                        stringData.append(symbol);
                        break;
                    }
                }
            case END_OF_LINE:
                stringData.append(LINEBREAK);
                ifEndOfString = true;
                state = BEGINNING_OF_LINE;
                break;
        }
    }

    /**
     * Check if end of string
     * @return true if end
     */
    @Override
    public boolean endofstring() {
        return ifEndOfString;
    }

    /**
     * Return formatted string for writing
     * @return string for writing
     */
    @Override
    public String getLine() {
        return stringData.toString();
    }
}
