package com.sirgiy.pack.formatter;

/**
 * IFiniteAutomaton interface
 * For formatting the text with using finite automaton
 */
public interface IFiniteAutomaton {

    /**
     * Formation of a line
     * @param symbol next character
     */
    void formationline(final char symbol);

    /**
     * Check if end of string
     * @return true if end
     */
    boolean endofstring();

    /**
     * Return formatted string for writing
     * @return string for writing
     */
    String getLine();
}
