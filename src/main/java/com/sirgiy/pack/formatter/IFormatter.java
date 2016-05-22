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
     * @throws FormatterException exception handling
     */
    void format(final IReader ireader, final IWriter iwriter) throws FormatterException;
}
