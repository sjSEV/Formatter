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

    /**
     * Default constructor
     */
    public Formatter() {

    }

     /**
      * Formatting the text
      * @param ireader Reading the following character
      * @param iwriter Writing character
      * @param iFiniteAutomaton finite automaton for formatting the text
      * @throws FormatterException exception handling
     */
     @Override
     public void format(final IReader ireader, final IWriter iwriter, final IFiniteAutomaton iFiniteAutomaton) throws FormatterException {
         String stringData = null;
         try {
            while (ireader.hasnext()) {
                char symbol = ireader.readnext();
                if (iFiniteAutomaton.endofstring()) {
                    stringData = iFiniteAutomaton.getLine();
                    iwriter.write(stringData);
                    iFiniteAutomaton.formationline(symbol);
                } else {
                    iFiniteAutomaton.formationline(symbol);
                }
            }
        } catch (ReaderException | WriterException e) {
            throw new FormatterException(e);
        }
    }
}
