package eich.nickelson.common.classes;


import eich.nickelson.common.downloading.Parser;
import eich.nickelson.common.parsing.Controller;
import org.junit.jupiter.api.Test;


public class HtmlParserTest {

    @Test
    public void getHolder() {
    Controller controller = new HtmlGetter();
    Parser parser = new HtmlParser();

        for (HtmlFileHolder htmlFileHolder : parser.getHolder(("https://www.sueddeutsche.de//"))){
            System.out.println(htmlFileHolder.getData());
            System.out.println(htmlFileHolder.getHeader());
            System.out.println(htmlFileHolder.getPicRef());
            System.out.println();
        }
    }
}