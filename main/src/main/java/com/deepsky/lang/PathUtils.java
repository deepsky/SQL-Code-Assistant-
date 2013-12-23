package com.deepsky.lang;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PathUtils {

    public static File chooseCSVFileToImport(Project project, String defaultPath) {
        FileChooserDescriptor desc = new FileChooserDescriptor(true, false, false, false, false, false) {
            @Override
            public boolean isFileVisible(final VirtualFile file, final boolean showHiddenFiles) {
                return true; //showHiddenFiles || !file.getName().startsWith(".");
            }

            @Override
            public boolean isFileSelectable(final VirtualFile file) {
                return super.isFileSelectable(file);
            }
        };

        // Check default path if file exists open file chooser
        VirtualFile vf = null;
        if (defaultPath != null && defaultPath.length() > 0 &&
                (new File(defaultPath).exists() || new File(defaultPath).getParentFile().exists())) {
            File f = new File(defaultPath);
            if (f.exists()) {
                // Choose the file
                vf = LocalFileSystem.getInstance().findFileByIoFile(f);
            } else {
                // Choose parent directory
                vf = LocalFileSystem.getInstance().findFileByIoFile(f.getParentFile());
            }
        } else {
            // Use work directory
            vf = LocalFileSystem.getInstance().findFileByIoFile(new File("."));
        }
        VirtualFile[] files = FileChooser.chooseFiles(desc, project, vf);
        if (files.length > 0) {
            return new File(files[0].getPath());
        }

        return null;
    }



    public static File choosePKfile(Project project, String defaultPath) {
        FileChooserDescriptor desc = new FileChooserDescriptor(true, false, false, false, false, false) {
            @Override
            public boolean isFileVisible(final VirtualFile file, final boolean showHiddenFiles) {
                return true; //showHiddenFiles || !file.getName().startsWith(".");
            }

            @Override
            public boolean isFileSelectable(final VirtualFile file) {
                return super.isFileSelectable(file);
            }
        };

        // Check default path if file exists open file chooser
        VirtualFile vf = null;
        if (defaultPath != null && defaultPath.length() > 0 &&
                (new File(defaultPath).exists() || new File(defaultPath).getParentFile().exists())) {
            File f = new File(defaultPath);
            if (f.exists()) {
                // Choose the file
                vf = LocalFileSystem.getInstance().findFileByIoFile(f);
            } else {
                // Choose parent directory
                vf = LocalFileSystem.getInstance().findFileByIoFile(f.getParentFile());
            }
        } else {
            // Use user home directory
            String userHome = System.getProperties().getProperty("user.home");
            if (userHome == null || !new File(userHome).exists()) {
                // Very very strange .. try to use user.dir
                String userDir = System.getProperties().getProperty("user.dir");
                if (userDir == null || !new File(userDir).exists()) {
                    vf = LocalFileSystem.getInstance().findFileByIoFile(new File("."));
                } else {
                    vf = LocalFileSystem.getInstance().findFileByIoFile(new File(userDir));
                }
            } else {
                vf = LocalFileSystem.getInstance().findFileByIoFile(new File(userHome));
            }
        }
        VirtualFile[] files = FileChooser.chooseFiles(desc, project, vf);
        if (files.length > 0) {
            return new File(files[0].getPath());
        }

        return null;
    }


    public static File chooseTNSNamesSource(Project project, String defaultPath) {
        FileChooserDescriptor desc = new FileChooserDescriptor(true, false, false, false, false, false) {
            @Override
            public boolean isFileVisible(final VirtualFile file, final boolean showHiddenFiles) {
                return showHiddenFiles || !file.getName().startsWith(".");
            }

            @Override
            public boolean isFileSelectable(final VirtualFile file) {
                return super.isFileSelectable(file);
            }
        };
        // Check default path if file exists open file chooser
        VirtualFile vf = null;
        if (defaultPath != null && defaultPath.length() > 0 &&
                (new File(defaultPath).exists() || new File(defaultPath).getParentFile().exists())) {
            File f = new File(defaultPath);
            if (f.exists()) {
                // Choose the file
                vf = LocalFileSystem.getInstance().findFileByIoFile(f);
            } else {
                // Choose parent directory
                vf = LocalFileSystem.getInstance().findFileByIoFile(f.getParentFile());
            }
        } else {
            // No file saved, try to guess location of tnsnames.ora file
            File f = PathUtils.locateTNSAdminDir();
            if (f != null) {
                vf = LocalFileSystem.getInstance().findFileByIoFile(f);
            } else {
                // Use user home directory
                String userHome = System.getProperties().getProperty("user.home");
                if (userHome == null || !new File(userHome).exists()) {
                    // Very very strange .. try to use user.dir
                    String userDir = System.getProperties().getProperty("user.dir");
                    if (userDir == null || !new File(userDir).exists()) {
                        vf = LocalFileSystem.getInstance().findFileByIoFile(new File("."));
                    } else {
                        vf = LocalFileSystem.getInstance().findFileByIoFile(new File(userDir));
                    }
                } else {
                    vf = LocalFileSystem.getInstance().findFileByIoFile(new File(userHome));
                }
            }
        }

        VirtualFile[] files = FileChooser.chooseFiles(desc, project, vf);
        if (files.length > 0) {
            return new File(files[0].getPath());
        }

        return null;
    }

    /*
    By default, tnsnames.ora is located in the $ORACLE_HOME/network/admin directory on UNIX
    operating systems and in the %ORACLE_HOME%\network\admin directory on Windows operating systems.
    tnsnames.ora can also be stored the following locations:

    The directory specified by the TNS_ADMIN environment variable or registry value

    On UNIX operating systems, the global configuration directory. For example, on the Solaris Operating System,
    this directory is /var/opt/oracle.
    */
    public static File locateTNSAdminDir() {
        // Check ORA_HOME first
        String oracleHome = System.getenv().get("ORACLE_HOME");
        if (oracleHome != null) {
            File home = new File(oracleHome);
            if (home.exists()) {
                File network = null;
                if (new File(home, "network").exists()) {
                    network = new File(home, "network");
                } else if (new File(home, "NETWORK").exists()) {
                    network = new File(home, "NETWORK");
                }

                if (network != null) {
                    if (new File(network, "admin").exists()) {
                        return new File(network, "admin");
                    } else if (new File(network, "ADMIN").exists()) {
                        return new File(network, "ADMIN");
                    }
                }
            }
        }

        // Check TNS_ADMIN env variable
        String tnsAdmin = System.getenv().get("TNS_ADMIN");
        if (tnsAdmin != null) {
            File tns = new File(tnsAdmin);
            if (tns.exists() && tns.isDirectory()) {
                return tns;
            }
        }

        // Now fun begins
        String path = System.getenv().get("PATH");
        String osName = (String) System.getProperties().get("os.name");
        if (path != null) {
            // Collect all paths which have ORA inside
            List<String> paths = new ArrayList<String>();
            int idxOra = path.toUpperCase().indexOf("ORA");
            while (idxOra != -1) {
                // Candidate found, save it
                PathIterator iterator = new PathIterator(path);
                int start = 0;
                while (iterator.hasNext()) {
                    int end = iterator.next();
                    if (end > idxOra) {
                        String path1 = path.substring(start == 0 ? 0 : start + 1, end);
                        paths.add(path1);
                        break;
                    }
                    start = end;
                }
                idxOra = path.toUpperCase().indexOf("ORA", idxOra + 1);
            }

            for (String path1 : paths) {
                String _path1 = path1.trim();
                File dir = new File(_path1);
                File target = traverseOverChildren(dir, new DirectoryHandler() {
                    public boolean handleDir(File dir) {
                        if ("admin".equalsIgnoreCase(dir.getName())) {
                            File parent = dir.getParentFile();
                            if ("network".equalsIgnoreCase(parent.getName())) {
                                return false;
                            }
                        }
                        return true;
                    }
                });

                if (target != null) {
                    // TNS admin directory found!
                    return target;
                }
            }
        }

        return null;
    }

    private static File traverseOverChildren(File f, DirectoryHandler handler) {
        if (f == null || !f.isDirectory()) {
            return null;
        }

        File[] files = f.listFiles();
        if (files != null)
            for (File child : files) {
                if (child.isDirectory()) {
                    if (!handler.handleDir(child)) {
                        return child;
                    }
                    File result = traverseOverChildren(f, handler);
                    if (result != null) {
                        return result;
                    }
                }
            }
        return null;
    }

    private interface DirectoryHandler {
        /**
         * @param dir
         * @return true keep iteration, false break
         */
        boolean handleDir(File dir);
    }


    private static class PathIterator {

        private String path;
        private int index = 0;

        public PathIterator(@NotNull String path) {
            this.path = path;
        }

        public boolean hasNext() {
            if (index != -1) {
                index = path.indexOf(File.pathSeparatorChar, index == 0 ? 0 : index + 1);
            }
            return index != -1;
        }

        public int next() {
            return index;
        }
    }


}
