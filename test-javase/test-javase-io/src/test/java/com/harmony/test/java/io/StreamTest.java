package com.harmony.test.java.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author wuxii@foxmail.com
 */
public class StreamTest {

    private static final InputStream is = System.in;
    private static final PrintStream os = System.out;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(is));

    public static void main(String[] args) throws IOException {
        while (true) {
            os.print("stream>");
            List<String> cmds = cmds();
            String cmd = cmds.get(0);
            if ("help".equals(cmd) || "?".equals(cmd) || "-h".equals(cmd)) {
                usage();
            } else if (cmd.startsWith("copy") && !cmds.contains("-nio") && cmds.size() > 1) {
                String[] contents = cmds.get(cmds.size() - 1).split(">");
                if (contents.length != 2) {
                    usage();
                } else {
                    File srcFile = new File(contents[0].trim());
                    File destFile = new File(contents[1].trim());
                    if (!isValid(srcFile, destFile)) {
                        continue;
                    }
                    long start = System.currentTimeMillis();
                    copy(srcFile, destFile);
                    os.println("copy file " + srcFile.getAbsolutePath() + " to " + destFile.getAbsolutePath() + ", use "
                            + (System.currentTimeMillis() - start) + "ms");
                }
            } else if (cmd.equals("copy") && cmds.contains("-nio") && cmds.size() > 2) {
                String[] contents = cmds.get(cmds.size() - 1).split(">");
                if (contents.length != 2) {
                    usage();
                } else {
                    File srcFile = new File(contents[0].trim());
                    File destFile = new File(contents[1].trim());
                    if (!isValid(srcFile, destFile)) {
                        continue;
                    }
                    long start = System.currentTimeMillis();
                    copyByNIO(srcFile, destFile);
                    os.println("copy file " + srcFile.getAbsolutePath() + " to " + destFile.getAbsolutePath() + ", use "
                            + (System.currentTimeMillis() - start) + "ms");
                }
            } else if ("exit".equals(cmd) || ".q".equals(cmd)) {
                System.exit(0);
            }
        }
    }

    private static List<String> cmds() throws IOException {
        List<String> cmds = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
        while (st.hasMoreTokens()) {
            cmds.add(st.nextToken().trim());
        }
        return cmds;
    }

    private static void usage() {
        os.println("usage: \n\tcopy [input]>[output]\tcopy [input] file to [output] file");
    }

    private static boolean isValid(File srcFile, File destFile) {
        if (!srcFile.exists()) {
            os.println(srcFile.getAbsolutePath() + " source file not exists");
            return false;
        }
        if (!srcFile.isFile()) {
            os.println(srcFile.getAbsolutePath() + " source file not a file");
            return false;
        }
        if (destFile.isDirectory()) {
            os.println(destFile.getAbsolutePath() + " destination os directory");
            return false;
        }
        if (!destFile.exists()) {
            File parent = destFile.getParentFile();
            if (parent.isFile()) {
                os.println(parent.getPath() + " not a directory");
                return false;
            }
            if (!parent.exists() && !destFile.getParentFile().mkdirs()) {
                os.println("can't create directory " + destFile.getParent());
                return false;
            }
        }
        return true;
    }

    private static void copy(File srcFile, File destFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            byte[] buf = new byte[1024 * 4];
            int index = fis.read(buf);
            while (index != -1) {
                fos.write(buf, 0, index);
                index = fis.read(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            try {
                fos.close();
                fis.close();
            } catch (IOException e) {
            }
        }
    }

    private static void copyByNIO(File srcFile, File destFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            FileChannel fic = fis.getChannel();
            FileChannel foc = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024 * 4);

            while (fic.read(buffer) != -1) {
                buffer.flip();// 翻转位置指针
                foc.write(buffer);
                buffer.clear();// 清空buffer
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
            }
        }

    }

}
