package edu.colostate.ember;

import edu.colostate.ember.nlp.QuestionLexParser;
import edu.colostate.ember.nlp.Sentenizer;
import edu.colostate.ember.util.LogUtil;
import edu.colostate.ember.util.StaticFields;
import edu.colostate.ember.util.TextUtil;
import edu.stanford.nlp.trees.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(StaticFields.INPUT_PATH));

        String line = "";
        String ref = "";
        String main_text = "";
        List<String> supplement = new ArrayList<>();


        QuestionLexParser qp = new QuestionLexParser();

//        qp.loadShiftReduceModel();

        while (bufferedReader.ready()) {
            line = bufferedReader.readLine();

            ref = line.split("\\|")[0];
            line = TextUtil.removePuncWordInBracket(line.split("\\|")[1]);

            List<String> sentences = new Sentenizer(new StringReader(line)).tokenize();
            for (String sentence : sentences) {
                if (sentence.matches(StaticFields.HASBRACKET_PATTERN)) {

                    sentence = TextUtil.parsingBrackets(sentence, qp);
//                    System.out.println(sentence + "\t------------->\t" + out);

                }

                sentence = sentence.trim();
                Tree parseTree = qp.parseToken(sentence);
                String firstLabel = parseTree.firstChild().label().toString();


//                System.out.println(sentence);
                if (firstLabel.matches("SBARQ|SINV|SQ")) {
//                    System.out.println("\t" + sentence);
                } else if (sentence.contains("?") && !firstLabel.matches("SBARQ|SINV|SQ")) {
//                    System.out.println(StaticFields.ANSI_RED + sentence + StaticFields.ANSI_RESET);
                    LogUtil.printErr(sentence);
                    parseTree.pennPrint();
                }

            }

//            System.out.println(ref + ":" + main_text);
//            Tree parse = qp.parseToken(main_text);
//            parse.pennPrint();
//            for ( Tree d : parse.getLeaves()) {
//                System.out.println(d.toString());
//            }
//            parse.printLocalTree();
//            for (Tree leaf : parse.getLeaves()) {
//                System.out.println(leaf.parent().);
//            }

//            System.out.println(parse.firstChild().firstChild().label());

        }


//            if (line.contains("?")) {
//                Sentenizer sentenizer = new Sentenizer(new StringReader(line));
////                sentenizer.tokenize().forEach(System.out::println);
//                if (line.contains("[")) {
//                    System.out.println(Arrays.toString(sentenizer.tokenize().toArray()));
////
//                }
//                for (String sentence : sentenizer.tokenize()) {
//                    if (sentence.contains("?")) {
//
//                    }
//                }
//            }

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

        bufferedReader.close();
    }
}
