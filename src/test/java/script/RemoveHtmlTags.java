package script;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class RemoveHtmlTags {
    public static void main(String[] args) {
        String htmlStatement = "<p>Polygon&nbsp; has&nbsp; only line segments.&nbsp;</p><figure class=\"image\"><img src=\"https://tm-admin-product-images.s3.us-east-2.amazonaws.com/images/kywqiq7g\"></figure><p>is not a polygon as it has curved ends.</p><p>So, the correct answer is A.</p>";

        // Using Jsoup to remove HTML tags
        String plainText = removeHtmlTags(htmlStatement);

        //System.out.println("Original: " + htmlStatement);
      //  System.out.println("Without HTML tags: " + plainText);
    }

    public static String removeHtmlTags(String html) {
        Document document = Jsoup.parse(html);

        // Extract plain text from the document
        String plainText = document.text();

        return plainText;
    }
}