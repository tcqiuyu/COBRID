package edu.colostate.ember.util;

public class StaticFields {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";


    public static final String INPUT_PATH = "input/Add_health_question_text.txt";
    public static final String LEXPARSER_MODEL = "model/edu/stanford/nlp/models/lexparser/englishPCFG.ser.gz";
    public static final String SHIFTPARSER_MODEL = "model/edu/stanford/nlp/models/srparser/englishSR.ser.gz";
    public static final String POS_TAGGER_MODEL = "model/edu/stanford/nlp/models/pos-tagger/english-left3words/english-left3words-distsim.tagger";
    public static final String DEPENDENCY_PARSER_MODEL = "model/edu/stanford/nlp/models/parser/nndep/english_SD.gz";


    public static final String HASBRACKET_PATTERN = ".*[\\(\\[\\{\\)\\]\\}].*";

    public static final String BRACKET_PATTERN = "\\[[^\\[\\]]*\\]";
    public static final String PAREN_PATTERN = "\\([^\\(\\)]*\\)";
    public static final String BRACKETORPAREN_PATTERN = "[\\(\\[\\{][^\\(\\)\\[\\]\\{\\}]*[\\)\\]\\}]";
    public static final String REFLINE_PATTERN = "^\\d+\\)\\s[\\dA-Z_]*$";
    public static final String PUNCWORDINBRACKET_PATTERN = "[:.!](?=[^\\(]*\\))";


    public static final String NLSY_REF_PATTERN = "^[A-Z][0-9]{5}.[0-9]{2}.*$";
    public static final String NLSY_FREQ_TABLE_PATTERN = "^Refusal\\(-1\\).*|^\\s+\\d+.*";
    public static final String NLSY_DASH_PATTERN = "^-+";
    public static final String NLSY_COUNT_PATTERN = "^TOTAL =+>\\s+\\d+\\s+VALID SKIP\\(-4\\)\\s+\\d+\\s+NON-INTERVIEW\\(-5\\)\\s+\\d+.*";
    public static final int NLSY_COUNT_TOTAL_IDX = 2;
    public static final int NLSY_COUNT_SKIP_IDX = 5;
    public static final int NLSY_COUNT_NON_INTERVIEW_IDX = 7;
    public static final double NLSY_VALID_QUESTIONNAIRE_THRESHOLD = 0.8;

    public static final String ADDHEALTH_WEBPAGE_URLBASE = "http://www.cpc.unc.edu/projects/addhealth/documentation/ace/tool/codebookssearch?field=varname&match=contains&text=";


    public static void main(String[] args) {
        String a = "       1           1 TO 999";
        System.out.println(a.matches(NLSY_FREQ_TABLE_PATTERN));
//        String[] b = a.split("\\s+");
//        System.out.println(b[2] + "," + b[5] + "," + b[7]);

    }
}
