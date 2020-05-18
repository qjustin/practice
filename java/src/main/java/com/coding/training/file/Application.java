package com.coding.training.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        List<String> resources = FileUtil.readLineFromFileList("d://mysqlBinlog14.sql");
        StringBuilder sb = new StringBuilder(" ");
        Boolean isWhere = true;

        for (String line : resources) {
            if (line != null && line.trim().length() > 0) {
                if (line.contains("WHERE")) isWhere = true;
                if (line.contains("SET")) isWhere = false;

                if (isWhere) {
                    if (line.contains("WHERE")) {
                        sb.append(");");
                        sb.append(System.getProperty("line.separator"));
                        sb.append("INSERT INTO pica.sms_doctor_send_shadow (doctor_id,total_given_num,used_num,avilable_sms_num,watched_revision_page,delete_flag,created_id,created_time,modified_id,modified_time) VALUE ( ");
                    }
                    if (!line.contains("WHERE")) {
                        String[] values = line.split("=");
                        if (!values[0].equals("@1")) {
                            sb.append(values[1] + ", ");
                        }
                    }
                }
            }
        }

        try {
            File writeName = new File("C://Users//depl_//Desktop//mysqlBinlog14_output.sql");
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)) {
                out.write(sb.toString());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
