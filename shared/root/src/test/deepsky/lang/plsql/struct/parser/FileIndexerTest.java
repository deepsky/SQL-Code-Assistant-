package test.deepsky.lang.plsql.struct.parser;

import antlr.TokenStreamException;
import com.deepsky.generated.plsql.PLSqlTokenTypes;
import com.deepsky.lang.plsql.struct.index.TrivialWordIndexer;
import com.deepsky.lang.plsql.struct.parser.PLSqlIndexingLexer;
import com.deepsky.utils.FileUtils;
import junit.framework.TestCase;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class FileIndexerTest extends TestCase {

    File base;

    public void setUp() throws URISyntaxException {
        base = new File(this.getClass().getClassLoader().getResource("indexer").toURI());
    }

    public void test0() throws TokenStreamException {

        final long[] indexAllSize = {0};
        final long[] allLength = {0};

        final List<IndexEntry> ientries = new ArrayList<IndexEntry>();
        long msAll = System.currentTimeMillis();

        processFilesInDirectory(base.toString(), new SqlFileProcessor(){
            public void process(File file)  {
                Reader in = null;
                try {
                    String fileName = file.getName();
                    long length = file.length();
                    allLength[0] += length;

                    long ms = System.currentTimeMillis();
                    in = new FileReader(file);
                    PLSqlIndexingLexer lexer = new PLSqlIndexingLexer(in);

                    TrivialWordIndexer indexer = new TrivialWordIndexer();
                    lexer.setWordProcessor(indexer);

                    while(lexer.nextToken().getType() != PLSqlTokenTypes.EOF){
                        // idle
                    }

                    ms = System.currentTimeMillis() - ms;
                    int allWords = indexer.getAllWords();
                    int effWords = indexer.getEffectiveWords();
                    String index = indexer.getIndex();
                    ientries.add(new IndexEntry(index, fileName));

                    int indexSize = index.length();
                    indexAllSize[0] += indexSize;

                    System.out.println("File: " + fileName + "\t\t\t length: " + length
                            + " allWords: " + allWords + " effWords: " + effWords
                            + "\t indexSize: " + indexSize + "\t time: " + ms);

                } catch (FileNotFoundException e) {
                    throw new Error(e);
                } catch (TokenStreamException e) {
                    throw new Error(e);
                } finally {
                    if(in != null){
                        try {in.close();} catch (IOException e) {}
                    }
                }
            }
        });

        msAll = System.currentTimeMillis() - msAll;
        System.out.println("Overall time: " + msAll + " indexAllSize: " + indexAllSize[0] + " allFileLength: " + allLength[0]);

        String[] test = new String[]{"loop", "varchar2", "a_directory", "extend"};
        for(String word: test){
            int foundFiles = 0;
            long ms = System.currentTimeMillis();
            for(IndexEntry ie: ientries){
                if(ie.index.indexOf(word) >= 0 ){
                    foundFiles++;
                }
            }

            ms = System.currentTimeMillis() - ms;
            System.out.println("Search time for: " + word + " is " + ms + " found files: " + foundFiles);
        }
    }


    private void processFilesInDirectory(String path, final SqlFileProcessor sqlProcessor){
        FileUtils.processDirectoryTree(path, new FileUtils.FileProcessor(){
            public void handleEntry(File dir, File file) {
                if (file.getName().toLowerCase().endsWith(".pkb")
                        || file.getName().toLowerCase().endsWith(".pks")
                        || file.getName().toLowerCase().endsWith(".sql")) {
                    sqlProcessor.process(file);
                }
            }
        });
    }


    interface SqlFileProcessor {
        void process(File file) ;
    }

    class IndexEntry {
        public String index;
        public String fileName;

        public IndexEntry(String index, String fileName){
            this.index = index;
            this.fileName = fileName;
        }
    }
}


