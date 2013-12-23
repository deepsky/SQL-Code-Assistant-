package com.deepsky.tools.completion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CC_NodeVisitorGen {


    final int OUTSIDE = 0;
    final int MAIN = 1;
    final int PERMUTATION = 2;

    final Pattern RESOURCE_DIR = Pattern.compile("^ *resources_dest_path=(.*)");
    final Pattern UNIT_TEST_DIR = Pattern.compile("^ *java_unit_test_path=(.*)");
    final Pattern UNIT_CLASS_NAME = Pattern.compile("^ *java_class_name=(.*)");


    public CC_NodeVisitorGen() throws IOException {

    }


    void process() throws IOException {
        URL url = getClass().getClassLoader().getResource("completion/stmts_and_nodes.conf");
        File f = new File(url.getFile());

        Configuration c = loadConf(f);

        for (int i = 0; i < c.correct.size(); i++) {
            for (int j = 0; j < c.incorrect.size(); j++) {
                for (int k = 0; k < c.incomplete.size(); k++) {
                    List<String> lst1 = new ArrayList<String>();
                    lst1.add(c.correct.get(i));
                    lst1.add(c.incorrect.get(j));
                    lst1.add(c.incomplete.get(k));

                    doPermutation(lst1, new ProcessPermutationCase() {
                        public void process(List<String> lst) {
                            System.out.println("----------");
                            for (String s : lst) {
                                System.out.println(s);
                            }
                        }
                    });
                }
            }
        }

    }

    public Configuration loadConf(File file) throws IOException {

        BufferedReader r = new BufferedReader(new FileReader(file));
        String str = null;

        Configuration conf = new Configuration();
        int section = OUTSIDE;

        while ((str = r.readLine()) != null) {
            if (str.matches("^ *//.*") || str.matches("^ *$")) {
                // Skip comments
                continue;
            }

            if (str.matches("^#MAIN.*")) {
                section = MAIN;
                continue;
            } else if (str.matches("^#PERMUTATIONS.*")) {
                section = PERMUTATION;
                continue;
            }

            if (section == OUTSIDE) {
                continue;
            } else if (section == MAIN) {
                Matcher m = RESOURCE_DIR.matcher(str);
                if (m.find()) {
                    conf.resourceDistPath = m.group(1);
                    continue;
                }

                m = UNIT_TEST_DIR.matcher(str);
                if (m.find()) {
                    conf.unitTestDistPath = m.group(1);
                    continue;
                }

                m = UNIT_CLASS_NAME.matcher(str);
                if (m.find()) {
                    conf.testClassName = m.group(1);
                    continue;
                }
            }
            if (section == PERMUTATION) {
                if (str.matches("^ *$")) {
                    // Skip blank lines
                    continue;
                }

                String[] parts = str.split("\\|");
                if (parts.length == 0 || parts.length == 1) {
                    System.err.println("Error line: " + str);
                    continue;
                }

                if ("0".equals(parts[0])) {
                    // Correct statement
                    conf.correct.add(parts[1]);
                } else if ("1".equals(parts[0])) {
                    // Incorrect statement
                    conf.incorrect.add(parts[1]);
                } else if ("2".equals(parts[0]) && parts.length == 3) {
                    // Incomplete statement
                    conf.incomplete.add(parts[1]);
                    conf.resultNodeList.add(parts[2]);
                }
            }
        }
        return conf;
    }


    class Configuration {
        List<String> correct = new ArrayList<String>();
        List<String> incorrect = new ArrayList<String>();
        List<String> incomplete = new ArrayList<String>();
        List<String> resultNodeList = new ArrayList<String>();

        public Configuration(){
        }

        String resourceDistPath;
        String unitTestDistPath;
        String testClassName;
    }

    private void doPermutation(List<String> lst, ProcessPermutationCase processor) {
        if (lst.size() != 3)
            throw new IllegalArgumentException("Only three elements should be in the list");

        for (int k = 0; k < lst.size() - 1; k++) {
            for (int i = 0; i < lst.size(); i++) {
                processor.process(lst);
                Collections.rotate(lst, -1);
            }

            Collections.rotate(lst.subList(1, lst.size()), -1);
        }
    }

    interface ProcessPermutationCase {
        void process(List<String> lst);
    }


    public static void main(String[] args) throws IOException {
        CC_NodeVisitorGen gen = new CC_NodeVisitorGen();
        gen.process();
    }

}
