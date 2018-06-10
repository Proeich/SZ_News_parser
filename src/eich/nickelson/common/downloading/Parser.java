package eich.nickelson.common.downloading;

import eich.nickelson.common.classes.HtmlFileHolder;

import java.util.List;

public interface Parser {
    List<HtmlFileHolder> getHolder(String input);

}
