package edu.colostate.ember.util;

public class StaticFields {
    public static String INPUT_PATH = "input/Add Health Wave I.TXT";
    public static String LEXPARSER_MODEL = "model/edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
    public static String SHIFTPARSER_MODEL = "model/edu/stanford/nlp/models/srparser/englishSR.ser.gz";
    public static String POS_TAGGER_MODEL = "model/edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";

    public static String BRACKET_PATTERN = "\\[[^\\[\\]]*\\]";
    public static String PAREN_PATTERN = "\\([^\\(\\)]*\\)";
    public static String BRACKET_PAREN_PATTERN = "[\\(\\[][^\\(\\)\\[\\]]*[\\)\\]]";
    public static String REFLINE_PATTERN = "^\\d+\\)\\s[\\dA-Z_]*$";
}
