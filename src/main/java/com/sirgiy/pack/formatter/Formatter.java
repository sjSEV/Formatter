package com.sirgiy.pack.formatter;

import com.sirgiy.pack.reader.IReader;
import com.sirgiy.pack.reader.ReaderException;
import com.sirgiy.pack.writer.IWriter;
import com.sirgiy.pack.writer.WriterException;

/**
 * Formatter Class
 * For formatting the text
 */
public class Formatter implements IFormatter {

    private static final int ONE_LEVEL_SPACE = 4;
    private static final String LINEBREAK = "\r\n";

    private StringBuilder stringData;
    private int level;

    /**
     * Default constructor
     * @throws FormatterException exception handling
     */
    public Formatter() throws FormatterException {
        level = 0;
    }

    /**
     * Removal of spaces at the beginning of a line
     */
    private void removespecebegin() {
        char space = ' ';
        while (stringData.charAt(0) == space) {
            stringData.deleteCharAt(0);
        }
    }

    /**
     * To check number of spaces
     * @return true if contains the correct quantity of gaps
     */
    private boolean checklevel() {
        char space = ' ';
        int spaceCount = ONE_LEVEL_SPACE * level;
        for (int i = 0; i < spaceCount; i++) {
            if (stringData.charAt(i) != space) {
                return false;
            }
        }
        return true;
    }

    /**
     * Set indents in the blocks
     */
    private void addindents() {
        char space = ' ';
        removespecebegin();
        if (stringData.toString().contains("}")) {
            if (level > 0) {
                level--;
            }
            if (level == 0) {
                stringData.append(LINEBREAK);
            }
        }
        if (level > 0) {
            while (!(checklevel())) {
                stringData.insert(0, space);
            }
        }
        if (stringData.toString().contains("{")) {
            level++;
        }
    }

    /**
     * Formation of a line with line feed after characters: '{', '}', ';'
     * @param ireader Reading the following character
     * @param firstSymbol first character
     * @throws FormatterException exception handling
     */
    private void formationline(final IReader ireader, final char firstSymbol) throws FormatterException {
        try {
            boolean nextLine = false;
            stringData = new StringBuilder();
            if (firstSymbol != '\r' & firstSymbol != '\n') {
                stringData.append(firstSymbol);
            }
            if (firstSymbol == '{' | firstSymbol == '}' | firstSymbol == ';') {
                stringData.append(LINEBREAK);
                nextLine = true;
            } else {
                nextLine = false;
            }
            while (ireader.hasnext() & !nextLine) {
                char symbol = ireader.readnext();
                if (symbol != '\r' & symbol != '\n') {
                    stringData.append(symbol);
                }
                if (symbol == '{' | symbol == '}' | symbol == ';') {
                    stringData.append(LINEBREAK);
                    nextLine = true;
                } else {
                    nextLine = false;
                }
            }
        } catch (ReaderException e) {
            throw new FormatterException(e);
        }
    }

     /**
     * Formatting the text
     * @param ireader Reading the following character
     * @param iwriter Writing character
     * @throws FormatterException exception handling
     */
     @Override
     public void format(final IReader ireader, final IWriter iwriter) throws FormatterException {
        try {
            while (ireader.hasnext()) {
                char symbol = ireader.readnext();
                formationline(ireader, symbol);
                addindents();
                iwriter.write(stringData.toString());
            }
        } catch (ReaderException | WriterException e) {
            throw new FormatterException(e);
        }
    }
}
