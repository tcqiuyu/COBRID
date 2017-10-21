package edu.colostate.ember;

import edu.colostate.ember.nlp.Sentenizer;
import edu.colostate.ember.util.StaticFields;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(StaticFields.INPUT_PATH));

        String line = "";
        String ref = "";
        String question = "";
        String supplement = "";
        while (bufferedReader.ready()) {
            line = bufferedReader.readLine();
            if (line.matches(StaticFields.REFLINE_PATTERN)) {
                ref = line.split("\\s")[1];
            }

            if (line.contains("?")) {
                Sentenizer sentenizer = new Sentenizer(new StringReader(line));
//                sentenizer.tokenize().forEach(System.out::println);
                if (line.contains("[")) {
                    System.out.println(Arrays.toString(sentenizer.tokenize().toArray()));
//
                }
                for (String sentence : sentenizer.tokenize()) {
                    if (sentence.contains("?")) {

                    }
                }
            }

        }
//        SentenceTokenizer qt = new SentenceTokenizer(new FileReader(StaticFields.INPUT_PATH));
//        SentenceTokenizer qt = new SentenceTokenizer(new StringReader("If Asian"));
//        QuestionLexParser lexParser = new QuestionLexParser();
////        List<List<HasWord>> sentences = qt.tokenize();
//
//        lexParser.parseToken("If Asian or Pacific Islander").pennPrint();
//
//
//        for (List<HasWord> sentence : sentences) {
//
////            List<CoreLabel> rawWords = SentenceUtils.toCoreLabelList(sentence.subList(0, sentence.size() - 1));
//            String rawSentence = SentenceUtils.listToString(sentence.subList(0, sentence.size() - 1));
//            String refNumber = SentenceUtils.listToString(sentence.subList(sentence.size() - 1, sentence.size()));
////            String.join(" ", rawWords);
////            System.out.println(refNumber);
////            qp.parseToken(rawWords);
//
//        }

    }
}
