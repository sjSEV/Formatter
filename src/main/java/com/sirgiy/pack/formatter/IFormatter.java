package com.sirgiy.pack.formatter;

import com.sirgiy.pack.reader.IReader;
import com.sirgiy.pack.writer.IWriter;

/**
 * IFormatter interface
 * For formatting the text
 */
public interface IFormatter {

    /**
     * Formatting the text
     * @param ireader Reading the following character
     * @param iwriter Writing character
     * @param iFiniteAutomaton finite automaton for formatting the text
     * @throws FormatterException exception handling
     */
    void format(final IReader ireader, final IWriter iwriter, final IFiniteAutomaton iFiniteAutomaton) throws FormatterException;
}
