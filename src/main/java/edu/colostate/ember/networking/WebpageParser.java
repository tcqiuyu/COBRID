package edu.colostate.ember.networking;

import edu.colostate.ember.util.StaticFields;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebpageParser {

    public static String getAddHealthQuestionText(String variableName) throws IOException {
        Document doc = Jsoup.connect(StaticFields.ADDHEALTH_WEBPAGE_URLBASE + variableName).get();
        String out = "";
        try {
            out = doc.getElementById("searchresults").getElementsByTag("td").get(2).html();
        } catch (IndexOutOfBoundsException e) {
            System.err.println(variableName);
        }
        return out;
    }


    public static void main(String[] args) throws IOException {
        System.out.println(getAddHealthQuestionText("H1WS6E"));
    }

}
