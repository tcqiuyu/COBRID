package edu.colostate.ember.nlp;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sentenizer implements Tokenizer<String> {

    private String[] puncWord = new String[]{".", "?", "!"};
    private DocumentPreprocessor dp;
    private String ptb3Escaping = "false";
    private List<String> sentences;
    private Iterator<String> iter;
    private int index = 0;

    public Sentenizer(Reader input) {

        dp = new DocumentPreprocessor(input);
        dp.setSentenceFinalPuncWords(puncWord);
        dp.setTokenizerFactory(PTBTokenizer.coreLabelFactory("ptb3Escaping=" + ptb3Escaping));

        sentences = new ArrayList<>();
        for (List<HasWord> sentenceTokenList : dp) {
            String sentence = SentenceUtils.listToString(sentenceTokenList);
            sentences.add(sentence);
        }


        iter = sentences.iterator();

    }

    public void setPuncWord(String[] puncWordList) {
        puncWord = puncWord;
    }

    public void parseSentences() {

    }


    @Override
    public String next() {
        index++;
        return iter.next();
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public void remove() {

    }

    @Override
    public String peek() {
        return sentences.get(index);
    }

    @Override
    public List<String> tokenize() {
        return sentences;
    }

    public static void main(String[] args) {
        Sentenizer sentenizer = new Sentenizer(new StringReader("What is your Asian background? You may give more than one answer: Chinese.\n"));
    }
}
