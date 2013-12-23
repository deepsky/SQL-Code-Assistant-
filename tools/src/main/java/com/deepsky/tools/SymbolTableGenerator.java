/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Generate:
 *  SymbolTable.java,
 *  PlSqlTokenTypesMapping.java,
 *  PlSqlBaseTokenTypes.java
 *
 * Sources for generation:
 *  1. lexer types:    PLSql2TokenTypes.txt
 *  2. parser types:   PLSqlTokenTypes.txt
 *  3. ansi92_list.txt parser_01.12.2009.txt
 *
 */
public class SymbolTableGenerator {

    Map<String, Integer> lexerTokens = new HashMap<String, Integer>();
    Map<String, Integer> symboltab = new HashMap<String, Integer>();

    final static String KEYWORD_EXT_DIRPATH_PROP = "keyword.source.dir";
    final static String KEYWORD_EXT_FILES_PROP = "keyword.files";

    final String SYMBOL_TABLE_PATH = "com/deepsky/generated/plsql";
    final String TOKEN_TABLE_PATH = "com/deepsky/integration/lexer/generated";

    File srcDir;
    int tableSize = 0;

    // OPEN_PAREN=23
    Pattern lexerPat = Pattern.compile("^(.*)=([0-9]+)");

    // LITERAL_trailing="trailing"=487
    // "**"=433
    // "nvarchar2"=407
    private static Pattern parserPat = Pattern.compile("^\"(.*)\"=([0-9]+)");
    private static Pattern parserPat1 = Pattern.compile("^LITERAL_.*=\"(.*)\"=([0-9]+)");

    private static Pattern keywordExtPat = Pattern.compile("(//)? *([A-Z0-9_]+)");

    List<File> keywordExtSources = new ArrayList<File>();

    Map<String, Boolean> reservedKeywords = new HashMap<String, Boolean>();
    final String KEYWORD_PREFIX = "KEYWORD_";


    /**
     *
     * @param lexer_tab  PLSql2TokenTypes.txt
     * @param parser_tab PLSqlTokenTypes.txt
     * @param srcPath
     * @throws IOException
     */
    public SymbolTableGenerator(File lexer_tab, File parser_tab, File srcPath) throws IOException {
        // 1. load Lexer table
        BufferedReader r1 = new BufferedReader(new FileReader(lexer_tab));
        String line;
        int lineCounter = 0;
        while ((line = r1.readLine()) != null) {
            // skip first two strings
            if (++lineCounter < 3) {
                continue;
            } else {
                Matcher m = lexerPat.matcher(line);
                if (m.find()) {
                    String tokenName = m.group(1);
                    String tokenCode = m.group(2);
                    lexerTokens.put(tokenName, Integer.parseInt(tokenCode));
                }
            }
        }
        r1.close();

        // 2. load keywords from Parser table
        BufferedReader r2 = new BufferedReader(new FileReader(parser_tab));
        String line2;
        int lineCounter2 = 0;
        while ((line2 = r2.readLine()) != null) {
            // skip first two strings
            if (++lineCounter2 < 3) {
                continue;
            } else {
                Matcher m = parserPat1.matcher(line2);
                if (m.find()) {
                    String keyword = m.group(1);
                    String tokenCode = m.group(2);
                    symboltab.put(keyword, Integer.parseInt(tokenCode));
                } else {
                    Matcher m2 = parserPat.matcher(line2);
                    if (m2.find()) {
                        String keyword = m2.group(1);
                        String tokenCode = m2.group(2);
                        symboltab.put(keyword, Integer.parseInt(tokenCode));
                    }
                }
            }
        }
        r2.close();

        // 3. verify keyword: ensure that ketwords have no whitespaces
        for (Map.Entry<String, Integer> e : symboltab.entrySet()) {
            if (e.getKey().indexOf(' ') != -1) {
                System.out.println("Found keyword with WS: '" + e.getKey() + "'");
                System.exit(-1);
            }
            tableSize = Math.max(tableSize, e.getValue());
        }
        System.out.println("Table size: " + tableSize);

        srcDir = srcPath;
    }

    private void setKeywordExtSources(List<File> keywordExtSources) {
        this.keywordExtSources = keywordExtSources;
    }

    private void generateClasses() throws IOException {
        // 4. generate SymbolTable class
        generateSymbolTable();

        // 5. Generate PlSqlTokenTypesMapping.java
        // generate mapping table: ANTLR Token type to IElementType (PlSqlBaseLexer)
        generateTokenMapTable(tableSize + 1);

        // 6. populate keyword table from external sources
        reservedKeywords = populateKeywordTable(keywordExtSources);

        // 7. generate Token Table: PlSqlBaseTokenTypes.java
        generateTokenTable();

    }

    public static Map<String, Boolean> populateKeywordTable(List<File> _keywordExtSources) {
        System.out.println("Populate keywords from external files.");
        Map<String, Boolean> reservedKeywords = new HashMap<String, Boolean>();
        for (File f : _keywordExtSources) {
            System.out.println("Load keywords from file: " + f.getPath());
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(f));

                String line = null;
                while ((line = in.readLine()) != null) {
                    // primitive validation
                    if (line.matches("[/A-Z0-9_]+")) {
                        // "(//)? *([A-Z_]+)"
                        Matcher m2 = keywordExtPat.matcher(line);
                        if (m2.find()) {
                            String comment = m2.group(1);
                            String second = m2.group(2);

                            reservedKeywords.put(second.toUpperCase(), comment == null);
                        }
                    } else {
                        // skip, only alphabet characters allowed
                        System.out.println("\tstring does not match criteria: " + line);
                    }
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        return reservedKeywords;
    }


    /**
     * PlSqlBaseTokenTypes.java
     *
     * Input:
     * - package name:
     *      com.deepsky.integration.lexer.generated
     * - interface name
     *      PlSqlBaseTokenTypes
     * - object type
     *      PlSqlTokenType
     *
     * @throws IOException
     */
    private void generateTokenTable() throws IOException {
/*
EXAMPLE OF OUTPUT

package com.deepsky.integration.lexer.generated;

public interface PlSqlTokenTypes {

    IElementType NUMBER = new PlSqlTokenType(38, "NUMBER");
    IElementType IDENTIFIER = new PlSqlTokenType(13, "IDENTIFIER");
}
*/
        String header =
                "package com.deepsky.integration.lexer.generated;\n\n" +
                        "import com.intellij.psi.tree.IElementType;\n" +
                        "import com.intellij.psi.tree.TokenSet;\n" +
                        "import com.deepsky.integration.plsql.PlSqlTokenType;\n\n" +
                        "public interface PlSqlBaseTokenTypes {\n\n";

        String templ = "\t\tIElementType <token_name> = new PlSqlTokenType(<token_id>, \"<keyword>\");";

        String static_end = "}\n";

        File path = new File(srcDir, TOKEN_TABLE_PATH);
        OutputStream out = new FileOutputStream(new File(path, "PlSqlBaseTokenTypes.java"));

        out.write(header.getBytes());

        for (Map.Entry<String, Integer> e : lexerTokens.entrySet()) {
            String t = templ.replace("<token_id>", e.getValue().toString());
            t = t.replace("<keyword>", e.getKey().toUpperCase());

            if (e.getKey().matches(".*[^a-zA-Z0-9_]+.*")) {
                // replace token name
                String tokenName2 = KEYWORD_PREFIX + e.getValue();
                t = t.replace("<token_name>", tokenName2);
                t = t + " // " + e.getKey();
            } else {
                t = t.replace("<token_name>", e.getKey());
            }
            t += "\n";
            out.write(t.getBytes());
        }

        List<String> tokenNames = new ArrayList<String>();
        for (Map.Entry<String, Integer> e : symboltab.entrySet()) {
            String t = templ.replace("<token_id>", e.getValue().toString());
            String tokenName = KEYWORD_PREFIX + e.getKey().toUpperCase();
            t = t.replace("<keyword>", e.getKey().toUpperCase());

            if (tokenName.matches(".*[^a-zA-Z0-9_]+.*")) {
                // replace token name
                String tokenName2 = KEYWORD_PREFIX + e.getValue();
                t = t.replace("<token_name>", tokenName2);
                t = t + " // " + tokenName;

                tokenNames.add(tokenName2);
            } else {
                t = t.replace("<token_name>", tokenName);

                tokenNames.add(tokenName);
            }

            t += "\n";
            out.write(t.getBytes());
        }

        String keywords = "\n\tTokenSet KEYWORDS = TokenSet.create(\n";
        out.write(keywords.getBytes());
        for (int i = 0; i < tokenNames.size(); i++) {
            String tokenName = tokenNames.get(i);

            String t = "\t\t" + createTableItem(tokenName);
            out.write((t + ",\n").getBytes());
        }

        // put border array element
        out.write("\t\tnew PlSqlTokenType(9999, \"$$$END_OF_ARRAY_$$$\")\n".getBytes());

        String keywords_end = "\t\t);\n";
        out.write(keywords_end.getBytes());

        out.write(static_end.getBytes());
        out.close();
    }


    private String createTableItem(String tokenName) {
        if (tokenName.startsWith(KEYWORD_PREFIX)) {
            String keyword = tokenName.substring(KEYWORD_PREFIX.length(), tokenName.length());
            Boolean b = reservedKeywords.get(keyword);
            if (b != null && !b) {
                return "//" + tokenName;
            } else {
                return tokenName;
            }
        } else {
            return tokenName;
        }
    }


    private void ensureDirExists(File dir) throws IOException {
        System.out.println("Make sure the directory exists: " + dir.toString());
        if (!dir.exists()) {
            String sep = "\\\\"; // todo -- it works on Windows platform only! File.separator;

            String[] names = dir.getPath().split(sep);
            File path = null;
            for (int i = 0; i < names.length; i++) {
                if(i == 0){
                    path = new File(names[i]);
                } else {
                    path = new File(path, names[i]);
                }
                if (!path.exists()) {
                    // create directory
                    if (!path.mkdir()) {
                        throw new IOException("Could not create directory: " + path.toString());
                    } else {
                        System.out.println("    Directory " + path.toString() + " created.");
                    }
                }

            }
        }
    }


    /**
     * Generate mapping table
     *
     * Input:
     * - package name:
     *      com.deepsky.integration.lexer.generated
     * - class name
     *      PlSqlTokenTypesMapping
     * - object type
     *      PlSqlBaseTokenTypes
     *
     * @param tableSize
     * @throws IOException
     */
    private void generateTokenMapTable(int tableSize) throws IOException {
/*
package com.deepsky.integration.lexer.generated;

public class PlSqlTokenTypesMapping {
    protected static IElementType[] table;

    static {
        table = new IElementType[515];

        // IElementType[1] = null; -- EOF
        ....
        table[13] = PlSqlTokenTypes.IDENTIFIER;
        table[38] = PlSqlTokenTypes.IDENTIFIER;
    }
}
 */

        String header =
                "package com.deepsky.integration.lexer.generated;\n\n" +
                        "import com.intellij.psi.tree.IElementType;\n\n" +
                        "public class PlSqlTokenTypesMapping {\n" +
                        "\tpublic final static IElementType[] table;\n" +
                        "\n" +
                        "\tstatic {\n" +
                        "\t\ttable = new IElementType[<table_size>];\n\n";

        String static_end = "\t}\n}\n";
        String templ = "\t\ttable[<token_id>] = PlSqlBaseTokenTypes.<token_name>;";

        File path = new File(srcDir, TOKEN_TABLE_PATH);
        ensureDirExists(path);
        OutputStream out = new FileOutputStream(new File(path, "PlSqlTokenTypesMapping.java"));

        String th = header.replace("<table_size>", Integer.toString(tableSize));
        out.write(th.getBytes());

        for (Map.Entry<String, Integer> e : lexerTokens.entrySet()) {
            String t = templ.replace("<token_id>", e.getValue().toString());
            if (e.getKey().matches(".*[^a-zA-Z0-9_]+.*")) {
                // replace token name
                String tokenName2 = "KEYWORD_" + e.getValue();
                t = t.replace("<token_name>", tokenName2);
                t = t + " // " + e.getKey();
            } else {
                t = t.replace("<token_name>", e.getKey());
            }

            t += "\n";
            out.write(t.getBytes());
        }

        for (Map.Entry<String, Integer> e : symboltab.entrySet()) {
            String t = templ.replace("<token_id>", e.getValue().toString());
            String tokenName = "KEYWORD_" + e.getKey().toUpperCase();
//            t = t.replace("<token_name>", "KEYWORD_" + e.getKey().toUpperCase());

            if (tokenName.matches(".*[^a-zA-Z0-9_]+.*")) {
                // replace token name
                String tokenName2 = "KEYWORD_" + e.getValue();
                t = t.replace("<token_name>", tokenName2);
                t = t + " // " + tokenName;
            } else {
                t = t.replace("<token_name>", tokenName);
            }

            t += "\n";
            out.write(t.getBytes());
        }

        out.write(static_end.getBytes());
        out.close();
    }


    /**
     * Generate SymbolTable.java
     *
     * Input:
     * - package name:
     *      com.deepsky.generated.plsql
     * - class name
     *      SymbolTable
     *
     * @throws IOException
     */
    void generateSymbolTable() throws IOException {
/*
EXAMPLE OF OUTPUT

public class SymbolTable {
	public static Integer get(String text){
		return keywords.get(text.toLowerCase());
	}

	static public Map<String, Integer> keywords;
	static {
		keywords = new HashMap<String, Integer>();
		keywords.put("using", 445);
		keywords.put("error_index", 699);
    }
}
*/
        String header =
                "package com.deepsky.generated.plsql;\n" +
                        "\n" +
                        "import java.util.Map;\n" +
                        "import java.util.HashMap;\n" +
                        "\n" +
                        "public class SymbolTable {\n" +
                        "\n" +
                        "\tpublic static Integer get(String text){\n" +
                        "\t\treturn keywords.get(text.toLowerCase());\n" +
                        "\t}\n" +
                        "\n" +
                        "\tstatic public Map<String, Integer> keywords;\n" +
                        "\tstatic {\n" +
                        "\t\tkeywords = new HashMap<String, Integer>();\n";

        String templ = "\t\tkeywords.put(\"<keyword>\", <token_id>);\n";

        String static_end = "\t}\n}\n";

        File path = new File(srcDir, SYMBOL_TABLE_PATH);
        OutputStream out = new FileOutputStream(new File(path.getAbsoluteFile(), "SymbolTable.java"));

        out.write(header.getBytes());

        for (Map.Entry<String, Integer> e : symboltab.entrySet()) {
            String t = templ.replace("<keyword>", e.getKey());
            t = t.replace("<token_id>", e.getValue().toString());

            out.write(t.getBytes());
        }

        out.write(static_end.getBytes());
        out.close();
    }


    static public void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("SymbolTableGenerator");
        if (args == null || args.length != 3) {
            System.out.println("Arguments less then expected. Expected 3, actual: " + args.length);
            System.exit(-1);
        }

        File lexer_tab = new File(args[0]);
        System.out.println("1st argument: " + lexer_tab.getAbsolutePath());
        if (!lexer_tab.exists()) {
            System.out.println("Could not find Lexer Table: " + lexer_tab.getAbsolutePath());
            System.exit(-1);
        }

        File parser_tab = new File(args[1]);
        System.out.println("2nd argument: " + parser_tab.getAbsolutePath());
        if (!parser_tab.exists()) {
            System.out.println("Could not find Parser Table: " + parser_tab.getAbsolutePath());
            System.exit(-1);
        }

        File sym_tab = new File(args[2]);
        System.out.println("3d argument: " + args[2]);

        // locate external KEYWORD files
        List<File> keywordExtSources = new ArrayList<File>();
        String path = System.getProperty(KEYWORD_EXT_DIRPATH_PROP);
        String files = System.getProperty(KEYWORD_EXT_FILES_PROP);
        if (path != null && files != null) {
            if (!new File(path).exists()) {
                System.out.println("Path does not exist: " + path + ". External keywords will be ignored");
            } else {
                String[] keywordFiles = files.split(new String(new char[]{File.pathSeparatorChar}));
                for (String f : keywordFiles) {
                    if (new File(new File(path), f).exists()) {
                        keywordExtSources.add(new File(new File(path), f));
                    } else {
                        System.out.println("File does not exist: " + new File(new File(path), f).toString());
                    }
                }
            }
        }

        SymbolTableGenerator gen = new SymbolTableGenerator(lexer_tab, parser_tab, sym_tab);
        gen.setKeywordExtSources(keywordExtSources);

        System.out.println("Run Symbol Table Generator");
        gen.generateClasses();

        System.out.println("Done");
    }

}
