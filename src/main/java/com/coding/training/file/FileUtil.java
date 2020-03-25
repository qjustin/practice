package com.coding.training.file;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;
public class FileUtil {


    public static Set<String> readLineFromFile(String path) {
        Set<String> lines = new HashSet<>();

        try {
            File filename = new File(path);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename), Charset.forName("utf8"));
            BufferedReader br = new BufferedReader(reader);
            String line;

            while ((line = br.readLine()) != null) {
                if (!"".equals(line) && line != null) {
                    lines.add(line);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lines;
    }

    public static Set<String> readLineFromString(String str) {

        Set<String> lines = new HashSet<>();
        try {
            ByteArrayInputStream bytesInputStream = new ByteArrayInputStream(str.getBytes(Charset.forName("utf8")));
            InputStreamReader inputStreamReader = new InputStreamReader(bytesInputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);

            String line;
            while ((line = br.readLine()) != null) {
                if ((!"".equals(line)) && line != null && line.trim().contains(":")) {
                    lines.add(line);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lines;
    }

    public static String readLineFromConsole() {
        try {
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
            String input = reader2.readLine();
            return input;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static Set<String> readLineFromDefaultFile() {
        //读取jar内main/resources/keys.txt文件
        Set<String> lines = new HashSet<>();
        try {
            InputStream stream = FileUtil.class.getClassLoader().getResourceAsStream("keys.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line;

            while ((line = br.readLine()) != null) {
                if (!"".equals(line) && line != null) {
                    lines.add(line);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lines;
    }
}
