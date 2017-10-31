package edu.colostate.ember.util;

import edu.colostate.ember.nlp.QuestionLexParser;
import edu.stanford.nlp.trees.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    public static List<String> extractPatterns(String sentence, String pattern) {

        List<String> patterns = new ArrayList<>();
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(sentence);

        while (m.find()) {
            String group = m.group();
//            System.out.println(group);
            patterns.add(group);
        }
//        System.out.println(m.group(1));
        return patterns;
    }

    public static String parsingBrackets(String input, QuestionLexParser qp) {
        List<String> brackets = extractPatterns(input, StaticFields.BRACKETORPAREN_PATTERN);
        List<String> subs = new ArrayList<>();

        for (String bracket : brackets) {
            Tree parse = qp.parseToken(bracket);

            bracket = bracket.substring(1, bracket.length() - 1).trim();

            if (bracket.contains("/")) {
                subs.add(bracket.split("\\/")[0].trim());
            } else if (bracket.matches("[A-Z]*")) {
                subs.add("John");
            } else if (parse.firstChild().label().toString().equals("NP")) {
                subs.add(bracket);
            } else {
                subs.add("");
            }
        }

        for (String sub : subs) {
//            System.out.println(StaticFields.ANSI_RED + sub + StaticFields.ANSI_RESET);
            input = input.replaceFirst(StaticFields.BRACKETORPAREN_PATTERN, sub);
//            LogUtil.printErr(input);
        }
        return input;
    }

    public static String removePuncWordInBracket(String input) {
        input = input.replaceAll(StaticFields.PUNCWORDINBRACKET_PATTERN, "");

        return input;
    }


    public static void main(String[] args) {
        String sentence = "[Section 1: General Introductory] What is your birth date? (month)";
        String ss = "12) H1GI2";
        extractPatterns(ss, StaticFields.REFLINE_PATTERN);
//        extractPatterns(sentence, StaticFields.BRACKET_PATTERN);
//        extractPatterns(sentence, StaticFields.BRACKET_PAREN_PATTERN);
//        sentence = sentence.replaceAll(StaticFields.BRACKET_PATTERN, "");
//        sentence = sentence.replaceFirst(StaticFields.BRACKET_PATTERN, "{}").replaceFirst(StaticFields.BRACKET_PATTERN, "[]");

//        sentence = replacePatterns(sentence, StaticFields.BRACKET_PATTERN, "[]");
//        extractPatterns(sentence, "e.", "R");
//        System.out.println(sentence);
    }

}
