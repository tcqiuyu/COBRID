package edu.colostate.ember.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class NLSYUtil {

    private static String nlsyroot = "rawData/NLSY97/NLSY97_Public";

    private static void processNLSYCodebook(Path path) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toString()));
            System.out.println("Processing file: " + path.toString() + "\t File size; " + Files.size(path) / 1024.0 / 1024.0);

//            System.out.println(bufferedReader.readLine());
            String line;
            String ref;
            String title, text;

            while ((line = bufferedReader.readLine()) != null) {


//                line = bufferedReader.readLine();

                if (line.matches(StaticFields.NLSY_REF_PATTERN)) {
                    ref = line.split("\\s")[0].replace(".", "");
                    skipLines(bufferedReader, 3);
                    title = bufferedReader.readLine();
                    System.out.println(path + ":" + ref + ":" + title);
                    skipLines(bufferedReader, 1);
                    text = appendUntil(bufferedReader, StaticFields.NLSY_FREQ_TABLE_PATTERN);
                    System.out.println(text);
                    String[] counts = readUntil(bufferedReader, StaticFields.NLSY_COUNT_PATTERN).split("\\s+");
                    Integer totalCount = Integer.parseInt(counts[StaticFields.NLSY_COUNT_TOTAL_IDX]);
                    Integer skipCount = Integer.parseInt(counts[StaticFields.NLSY_COUNT_SKIP_IDX]);
                    Integer nonInterviewCount = Integer.parseInt(counts[StaticFields.NLSY_COUNT_NON_INTERVIEW_IDX]);
                    LogUtil.printErr(totalCount + ":" + skipCount + ":" + nonInterviewCount);
                    readUntil(bufferedReader, StaticFields.NLSY_DASH_PATTERN);

                }

            }
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
            out = out.concat(line);
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
        return path.toString().endsWith("cdb");
    }

    public static void main(String[] args) {
        try (Stream<Path> paths = Files.walk(Paths.get(nlsyroot))) {
            paths.filter(NLSYUtil::filterCodebook)
                    .forEach(NLSYUtil::processNLSYCodebook);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(TextUtil.removePuncWordInBracket("sadfa.asdfad:(aaa:as.fasf)adfjaklsd,adsf:"));

    }

}
