package au.com.bytecode.opencsv;

import au.com.bytecode.opencsv.CSVWriter;
import junit.framework.TestCase;

import java.io.IOException;
import java.io.StringWriter;

public class OpenCSVLibTest extends TestCase {

/*
    public void test0() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("yourfile.csv"));
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            System.out.println(nextLine[0] + nextLine[1] + "etc...");
        }
    }
*/

    public void test_from_example() throws IOException {
        StringWriter stringWriter = new StringWriter();
        CSVWriter writer = new CSVWriter(stringWriter, '\t');
        // feed in your array (or convert your data to an array)
        String[] entries = "first#second#third".split("#");
        writer.writeNext(entries);
        writer.close();

        assertEquals("\"first\"\t\"second\"\t\"third\"\n", stringWriter.toString());

        stringWriter = new StringWriter();
        writer = new CSVWriter(stringWriter, '\t', CSVWriter.NO_QUOTE_CHARACTER);
        // feed in your array (or convert your data to an array)
        entries = "first#second#third".split("#");
        writer.writeNext(entries);
        writer.close();

        assertEquals("first\tsecond\tthird\n", stringWriter.toString());
    }


    public void test_double_quotes_in_field() throws IOException {
        StringWriter stringWriter = new StringWriter();
        CSVWriter writer = new CSVWriter(stringWriter, '\t', '"');
        // feed in your array (or convert your data to an array)
        String[] entries = "first#\"second\"#third".split("#");
        writer.writeNext(entries);
        writer.close();

        assertEquals("\"first\"\t\"\"\"second\"\"\"\t\"third\"\n", stringWriter.toString());


        stringWriter = new StringWriter();
        writer = new CSVWriter(stringWriter, '\t', '"');
        // feed in your array (or convert your data to an array)
        entries = "first#\"second one\"#third".split("#");
        writer.writeNext(entries);
        writer.close();

        assertEquals("\"first\"\t\"\"\"second one\"\"\"\t\"third\"\n", stringWriter.toString());

        stringWriter = new StringWriter();
        writer = new CSVWriter(stringWriter, '\t', '"');
        // feed in your array (or convert your data to an array)
        entries = "first#\"second one#third".split("#");
        writer.writeNext(entries);
        writer.close();

        assertEquals("\"first\"\t\"\"\"second one\"\t\"third\"\n", stringWriter.toString());
    }


    public void test_leading_trailing_spaces() throws IOException {
        StringWriter stringWriter = new StringWriter();
        CSVWriter writer = new CSVWriter(stringWriter, '\t', '"');
        // feed in your array (or convert your data to an array)
        String[] entries = " first#second # third ".split("#");
        writer.writeNext(entries);
        writer.close();

        assertEquals("\" first\"\t\"second \"\t\" third \"\n", stringWriter.toString());

        stringWriter = new StringWriter();
        writer = new CSVWriter(stringWriter, '\t', CSVWriter.NO_QUOTE_CHARACTER);
        // feed in your array (or convert your data to an array)
        entries = "first # second #third".split("#");
        writer.writeNext(entries);
        writer.close();

        assertEquals("\"first \"\t\" second \"\tthird\n", stringWriter.toString());
     }


    public void test_linefeed_in_fields() throws IOException {
        StringWriter stringWriter = new StringWriter();
        CSVWriter writer = new CSVWriter(stringWriter, '\t', '"');
        // feed in your array (or convert your data to an array)
        String[] entries = "first#last\none but not least #third".split("#");
        writer.writeNext(entries);
        writer.close();

        assertEquals("\"first\"\t\"last\n" +
                "one but not least \"\t\"third\"\n", stringWriter.toString());

        // WS + NO DOUBLE QUOTES
        stringWriter = new StringWriter();
        writer = new CSVWriter(stringWriter, '\t', CSVWriter.NO_QUOTE_CHARACTER);
        // feed in your array (or convert your data to an array)
        entries = "first#last\none but not least #third".split("#");
        writer.writeNext(entries);
        writer.close();

        assertEquals("first\t\"last\n" +
                "one but not least \"\tthird\n", stringWriter.toString());


        // WS + DOUBLE QUOTES
        stringWriter = new StringWriter();
        writer = new CSVWriter(stringWriter, ';', '"');
        // feed in your array (or convert your data to an array)
        entries = "first#last\none but not least #third".split("#");
        writer.writeNext(entries);
        writer.close();

        assertEquals("\"first\";\"last\n" +
                "one but not least \";\"third\"\n", stringWriter.toString());
    }
}
