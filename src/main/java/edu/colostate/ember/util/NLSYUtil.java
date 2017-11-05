package edu.colostate.ember.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class NLSYUtil {

    //    private static String nlsyoutput = "data/input/NLSY_question_text_response";
//    private static String nlsyroot = "rawData/NLSY97/NLSY97_Public";
//    private static String nlsyoutput = "data/input/NLSY_question_text";

    private static void processNLSYCodebook(Path path) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toString()));
            System.out.println("Processing file: " + path.toString() + "\t File size; " + Files.size(path) / 1024.0 / 1024.0);

//            System.out.println(bufferedReader.readLine());
            String line;
            String ref, year;
            String title, text;
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(StaticFields.NLSY_INPUT_PATH, true));

            while ((line = bufferedReader.readLine()) != null) {


//                line = bufferedReader.readLine();

                if (line.matches(StaticFields.NLSY_REF_PATTERN)) {
                    ref = line.split("\\s+")[0].replace(".", "");

                    year = line.split("\\s+")[4];
                    if (!year.matches("\\d+")) {
                        continue;
                    }
                    skipLines(bufferedReader, 3);
                    title = bufferedReader.readLine().trim();
                    if (title.contains("ROS ITEM")) {
                        continue;
                    }
//                    System.out.println(path + ":" + ref + ":" + title);
                    skipLines(bufferedReader, 1);
                    text = appendUntil(bufferedReader, StaticFields.NLSY_FREQ_TABLE_PATTERN).trim();
//                    System.out.println(text);
                    String[] counts = readUntil(bufferedReader, StaticFields.NLSY_COUNT_PATTERN).split("\\s+");
                    Double totalCount = Double.parseDouble(counts[StaticFields.NLSY_COUNT_TOTAL_IDX]);
                    Double skipCount = Double.parseDouble(counts[StaticFields.NLSY_COUNT_SKIP_IDX]);
                    Double nonInterviewCount = Double.parseDouble(counts[StaticFields.NLSY_COUNT_NON_INTERVIEW_IDX]);
//                    LogUtil.printErr(totalCount + ":" + skipCount + ":" + nonInterviewCount);
                    readUntil(bufferedReader, StaticFields.NLSY_DASH_PATTERN);

                    Double validPercent = totalCount / (totalCount + skipCount + nonInterviewCount);
                    if (validPercent >= StaticFields.NLSY_VALID_QUESTIONNAIRE_THRESHOLD) {
                        bufferedWriter.write(ref + "|" + title + "|" + text + "\n");
                    }

                }

            }
            bufferedReader.close();
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    private static void skipLines(BufferedReader reader, int number) throws IOException {
        for (int i = 0; i < number; i++) {
            reader.readLine();
        }

    }

    private static String appendUntil(BufferedReader reader, String pattern) throws IOException {
        String line, out = "";
        while ((line = reader.readLine()) != null) {
            if (line.matches(pattern)) {
                return out;
            }
            out = out.concat(" " + line);
        }
        return null;
    }

    private static String readUntil(BufferedReader reader, String pattern) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.matches(pattern)) {
                return line;
            }
        }
        return null;
    }

    private static boolean filterCodebook(Path path) {
        return path.toString().matches(".*(?<!Survey-Methodology)\\.cdb");
//        System.out.println(path);
//        return path.toString().endsWith("cdb");
    }

    private void phase1() {
        File out = new File(StaticFields.NLSY_INPUT_PATH);
        if (out.exists()) {
            System.out.println("HAHHAHAH");
            out.delete();
        }

        try (Stream<Path> paths = Files.walk(Paths.get(StaticFields.NLSY_RAW_PATH))) {
            paths.filter(NLSYUtil::filterCodebook)
                    .forEach(NLSYUtil::processNLSYCodebook);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void phase2() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(StaticFields.NLSY_INPUT_PATH));
        File out = new File(StaticFields.NLSY_INTERMEDIATE_PATH);
        if (out.exists()) {
            System.out.println(StaticFields.NLSY_INTERMEDIATE_PATH + " exists. Deleted");
            out.delete();
        }
        String line;

        while ((line = bufferedReader.readLine()) != null) {

        }

    }

    public static void main(String[] args) {

    }

}
