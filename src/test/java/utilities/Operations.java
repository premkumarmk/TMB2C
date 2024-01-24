package utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Operations {


    private static String removeHtmlTags(String html) 
    {
        Document document = Jsoup.parse(html);
        // Extract plain text from the document
        String plainText = document.text();
        return plainText;
    }
}
