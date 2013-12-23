
package antlr;

import antlr.collections.impl.BitSet;
import antlr.collections.impl.Vector;

import java.io.*;

public class ToolPatched extends Tool{

    /** Perform processing on the grammar file.  Can only be called
     * from main() @param args The command-line arguments passed to
     * main().  This wrapper does the System.exit for use with command-line.
     */
    public void doEverythingWrapper(String[] args) {
        int exitCode = doEverything(args);
        System.exit(exitCode);
    }

    /** Process args and have ANTLR do it's stuff without calling System.exit.
     *  Just return the result code.  Makes it easy for ANT build tool.
     */
    public int doEverything(String[] args) {
        // run the preprocessor to handle inheritance first.

        // Start preprocessor. This strips generates an argument list
        // without -glib options (inside preTool)
        antlr.preprocessor.Tool preTool = new antlr.preprocessor.Tool(this, args);

        boolean preprocess_ok = preTool.preprocess();
        String[] modifiedArgs = preTool.preprocessedArgList();

        // process arguments for the Tool
        processArguments(modifiedArgs);
        if (!preprocess_ok) {
            return 1;
        }

        f = getGrammarReader();

        ANTLRLexer lexer = new ANTLRLexer(f);
        TokenBuffer tokenBuf = new TokenBuffer(lexer);
        LLkAnalyzer analyzer = new LLkAnalyzer(this);
        MakeGrammar behavior = new MakeGrammar(this, args, analyzer);

        try {
            ANTLRParser p = new ANTLRParser(tokenBuf, behavior, this);
            p.setFilename(grammarFile);
            p.grammar();
            if (hasError()) {
                fatalError("Exiting due to errors.");
            }
// TODO            checkForInvalidArguments(modifiedArgs, cmdLineArgValid);

            // Create the right code generator according to the "language" option
            CodeGenerator codeGen;

            // SAS: created getLanguage() method so subclass can override
            //      (necessary for VAJ interface)
            String codeGenClassName = "antlr." + getLanguage(behavior) + "CodeGenerator";
            try {
                Class codeGenClass = Class.forName(codeGenClassName);
                codeGen = (CodeGenerator)codeGenClass.newInstance();
                codeGen.setBehavior(behavior);
                codeGen.setAnalyzer(analyzer);
                codeGen.setTool(this);
                codeGen.gen();

                return 0;
            }
            catch (ClassNotFoundException cnfe) {
                panic("Cannot instantiate code-generator: " + codeGenClassName);
            }
            catch (InstantiationException ie) {
                panic("Cannot instantiate code-generator: " + codeGenClassName);
            }
            catch (IllegalArgumentException ie) {
                panic("Cannot instantiate code-generator: " + codeGenClassName);
            }
            catch (IllegalAccessException iae) {
                panic("code-generator class '" + codeGenClassName + "' is not accessible");
            }
        }
        catch (RecognitionException pe) {
            fatalError("Unhandled parser error: " + pe.getMessage());
        }
        catch (TokenStreamException io) {
            fatalError("TokenStreamException: " + io.getMessage());
        }
        return -1;
    }

    private static void help() {
        System.err.println("usage: java antlr.Tool [args] file.g");
        System.err.println("  -o outputDir       specify output directory where all output generated.");
        System.err.println("  -glib superGrammar specify location of supergrammar file.");
        System.err.println("  -debug             launch the ParseView debugger upon parser invocation.");
        System.err.println("  -html              generate a html file from your grammar.");
        System.err.println("  -docbook           generate a docbook sgml file from your grammar.");
        System.err.println("  -diagnostic        generate a textfile with diagnostics.");
        System.err.println("  -trace             have all rules call traceIn/traceOut.");
        System.err.println("  -traceLexer        have lexer rules call traceIn/traceOut.");
        System.err.println("  -traceParser       have parser rules call traceIn/traceOut.");
        System.err.println("  -traceTreeParser   have tree parser rules call traceIn/traceOut.");
        System.err.println("  -h|-help|--help    this message");
    }



    public static void main(String[] args) {
        System.err.println("ANTLR Parser Generator   Version " +
                           Version.project_version + "   1989-2005 jGuru.com");
        version = Version.project_version;

        try {
            if (args.length == 0) {
                help();
                System.exit(1);
            }
            for (int i = 0; i < args.length; ++i) {
                if (args[i].equals("-h")
                    || args[i].equals("-help")
                    || args[i].equals("--help")
                ) {
                    help();
                    System.exit(1);
                }
            }

            ToolPatched theTool = new ToolPatched();
            int ret = theTool.doEverything(args);
            if(ret == 0){
                System.exit(0);
            } else {
                System.exit(-2);
            }

        } catch (Exception e) {
            System.err.println(System.getProperty("line.separator") +
                               System.getProperty("line.separator"));
            System.err.println("#$%%*&@# internal error: " + e.toString());
            System.err.println("[complain to nearest government official");
            System.err.println(" or send hate-mail to parrt@jguru.com;");
            System.err.println(" please send stack trace with report.]" +
                               System.getProperty("line.separator"));
            e.printStackTrace();
        }
        System.exit(-1);
    }

    /** This method is used by all code generators to create new output
     * files. If the outputDir set by -o is not present it will be created here.
     */
    public PrintWriter openOutputFile(String f) throws IOException {
        if( outputDir != "." ) {
            File out_dir = new File(outputDir);
            if( ! out_dir.exists() )
                out_dir.mkdirs();
        }
        return new PrintWriter(new PreservingFileWriter(outputDir + System.getProperty("file.separator") + f));
    }

    public Reader getGrammarReader() {
        Reader f = null;
        try {
            if (grammarFile != null) {
                f = new BufferedReader(new FileReader(grammarFile));
            }
        }
        catch (IOException e) {
            fatalError("cannot open grammar file " + grammarFile);
        }
        return f;
    }

    /** @since 2.7.2
     */
    public void reportException(Exception e, String message) {
        System.err.println(message == null ? e.getMessage()
                                           : message + ": " + e.getMessage());
    }

    /** @since 2.7.2
     */
    public void reportProgress(String message) {
        System.out.println(message);
    }

    /** An error occured that should stop the Tool from doing any work.
     *  The default implementation currently exits (via
     *  {@link java.lang.System.exit(int)} after printing an error message to
     *  <var>stderr</var>. However, the tools should expect that a subclass
     *  will override this to throw an unchecked exception such as
     *  {@link java.lang.IllegalStateException} or another subclass of
     *  {@link java.lang.RuntimeException}. <em>If this method is overriden,
     *  <strong>it must never return normally</strong>; i.e. it must always
     *  throw an exception or call System.exit</em>.
     *  @since 2.7.2
     *  @param s The message
     */
    public void fatalError(String message) {
        System.err.println(message);
        System.exit(1);
    }

    /** Issue an unknown fatal error. <em>If this method is overriden,
     *  <strong>it must never return normally</strong>; i.e. it must always
     *  throw an exception or call System.exit</em>.
     *  @deprecated as of 2.7.2 use {@link #fatalError(String)}. By default
     *              this method executes <code>fatalError("panic");</code>.
     */
    public void panic() {
        fatalError("panic");
    }

    /** Issue a fatal error message. <em>If this method is overriden,
     *  <strong>it must never return normally</strong>; i.e. it must always
     *  throw an exception or call System.exit</em>.
     *  @deprecated as of 2.7.2 use {@link #fatalError(String)}. By defaykt
     *              this method executes <code>fatalError("panic: " + s);</code>.
     * @param s The message
     */
    public void panic(String s) {
        fatalError("panic: " + s);
    }

    // File.getParent() can return null when the file is specified without
    // a directory or is in the root directory.
    // This method handles those cases.
    public File parent(File f) {
        String dirname = f.getParent();
        if (dirname == null) {
            if (f.isAbsolute())
                return new File(File.separator);
            else
                return new File(System.getProperty("user.dir"));
        }
        return new File(dirname);
    }

    /** Parse a list such as "f1.g;f2.g;..." and return a Vector
     *  of the elements.
     */
    public static Vector parseSeparatedList(String list, char separator) {
        java.util.StringTokenizer st =
        new java.util.StringTokenizer(list, String.valueOf(separator));
        Vector v = new Vector(10);
        while ( st.hasMoreTokens() ) {
             v.appendElement(st.nextToken());
        }
        if (v.size() == 0) return null;
        return v;
    }

    /** given a filename, strip off the directory prefix (if any)
     *  and return it.  Return "./" if f has no dir prefix.
     */
    public String pathToFile(String f) {
        String separator = System.getProperty("file.separator");
        int endOfPath = f.lastIndexOf(separator);
        if (endOfPath == -1) {
            // no path, use current directory
            return "." + System.getProperty("file.separator");
        }
        return f.substring(0, endOfPath + 1);
    }

    /** <p>Process the command-line arguments.  Can only be called by Tool.
     * A bitset is collected of all correct arguments via setArgOk.</p>
     * @param args The command-line arguments passed to main()
     *
     */
    protected void processArguments(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-diagnostic")) {
                genDiagnostics = true;
                genHTML = false;
                setArgOK(i);
            }
            else if (args[i].equals("-o")) {
                setArgOK(i);
                if (i + 1 >= args.length) {
                    error("missing output directory with -o option; ignoring");
                }
                else {
                    i++;
                    setOutputDirectory(args[i]);
                    setArgOK(i);
                }
            }
            else if (args[i].equals("-html")) {
                genHTML = true;
                genDiagnostics = false;
                setArgOK(i);
            }
            else if (args[i].equals("-docbook")) {
                genDocBook = true;
                genDiagnostics = false;
                setArgOK(i);
            }
            else {
                if (args[i].charAt(0) != '-') {
                    // Must be the grammar file
                    grammarFile = args[i];
                    setArgOK(i);
                }
            }
        }
    }

}
