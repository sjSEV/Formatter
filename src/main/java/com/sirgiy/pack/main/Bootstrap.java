package com.sirgiy.pack.main;

import com.sirgiy.pack.formatter.*;
import com.sirgiy.pack.reader.FileReader;
import com.sirgiy.pack.reader.IReader;
import com.sirgiy.pack.reader.ReaderException;
import com.sirgiy.pack.reader.StringReader;
import com.sirgiy.pack.writer.FileWriter;
import com.sirgiy.pack.writer.IWriter;
import com.sirgiy.pack.writer.WriterException;

/**
 * Bootstrap Class
 * Entry point
 */
public final class Bootstrap {

    /**
     * Default constructor
     */
    private Bootstrap() {

    }

    /**
     * Entry point
     * @param args of string
     * @throws ReaderException reading exception handling
     * @throws WriterException writing exception handling
     * @throws FormatterException formatting exception handling
     * @throws FiniteAutomatonException exception handling
     */
    public static void main(final String[] args) throws ReaderException, WriterException, FormatterException, FiniteAutomatonException {
        IReader ireader = null;
        IWriter iwriter = null;
        IFiniteAutomaton iFiniteAutomaton = null;

        if (args.length == 2) {
            try {
                if (args[0].equals("-s")) {
                    ireader = new StringReader(args[1]);
                    iwriter = new FileWriter("testOutput.txt");
                } else if (args[0].equals("-f")) {
                    ireader = new FileReader(args[1]);
                    iwriter = new FileWriter("testOutput.txt");
                } else {
                    help();
                }
            } catch (ReaderException e) {
                throw new ReaderException(e);
            } catch (WriterException e2) {
                throw new WriterException(e2);
            }

            Formatter formatter = new Formatter();

            try {
                iFiniteAutomaton = new FiniteAutomaton(new FileReader(".properties"));
            } catch (FiniteAutomatonException e) {
                throw new FiniteAutomatonException(e);
            }

            formatter.format(ireader, iwriter, iFiniteAutomaton);

            try {
                iwriter.close();
            } catch (NullPointerException e) {
                throw new WriterException(e);
            }

            try {
                ireader.close();
            } catch (NullPointerException e) {
                throw new ReaderException(e);
            }
        } else {
            help();
        }
    }

    /**
     * Conclusion of reference information
     */
    private static void help() {
        System.out.println("Usage: [-options] <input>");
        System.out.println("where options include:");
        System.out.println("    -s      for input from string");
        System.out.println("    -f      for input from file");
        System.out.println("where <input>:");
        System.out.println("    if -s string to read");
        System.out.println("    if -f file name to open input stream");
    }
}
