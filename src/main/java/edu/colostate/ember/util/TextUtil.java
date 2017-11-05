package edu.colostate.ember.util;

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

    public static String removePuncWordInBracket(String input) {
        input = input.replaceAll(StaticFields.PUNCWORDINBRACKET_PATTERN, "");

        return input;
    }

    public static String listToString(List<String> list) {
        String out = "";
        if (list.size() == 0) {
            return out;
        } else {
            for (String s : list) {
                out = out.concat(s).concat("|");
            }
            return out.substring(0, out.length() - 1);
        }
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
