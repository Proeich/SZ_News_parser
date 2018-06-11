package eich.nickelson.common.parsing;

import eich.nickelson.common.classes.HtmlFileHolder;
import eich.nickelson.common.downloading.getter.HtmlGetter;
import eich.nickelson.common.downloading.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HtmlParser implements Parser {

    private final Controller controller;

    public HtmlParser(){
        this.controller = new HtmlGetter();
    }

    @Override
    public List<HtmlFileHolder> getHolder(String urlToRead) {
        String temp = controller.get(urlToRead);
        String starter = "<div id=\"sitecontent\" class=\"mainpage\" role=\"main\">";
        String seperatorStart = "<a";
        String seperatorEnd = "</a>";

        List<HtmlFileHolder> lst = new ArrayList<>();

        File file = new File("temp");


        try {
            file.createNewFile();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(temp);
            bufferedWriter.close();

            BufferedReader bf = new BufferedReader(new FileReader(file));
            while(true){
                StringBuilder sb = new StringBuilder();
                String k = bf.readLine();
                if(k == null){
                    break;
                }
                if(k.contains(seperatorStart)){
                    String m = k;
                    HtmlFileHolder htmlTemp = new HtmlFileHolder(null,null,null);
                    while(true) {

                        if(m == null){
                            continue;
                        }

                        if(m.contains("href=\"")){
                            /*
                            if(!m.substring(m.indexOf("=\"") + 2, m.substring(m.indexOf("=\"") + 2).indexOf("\"") + m.indexOf("=\"") + 2).contains("sueddeutsche.de/")){
                                break;
                            }
                            */

                            if(isAd(m.substring(m.indexOf("=\"") + 2, m.substring(m.indexOf("=\"") + 2).indexOf("\"") + m.indexOf("=\"") + 1))){
                                break;
                            }

                            htmlTemp.setData(m.substring(m.indexOf("=\"") + 2, m.substring(m.indexOf("=\"") + 2).indexOf("\"") + m.indexOf("=\"") + 2));
                        }

                        if(m.contains("data-src=\"")){

                            String tt = m.substring(m.indexOf("=\"") + 2, m.substring(m.indexOf("=\"") + 2).indexOf("\"") + m.indexOf("=\"") + 2);


                            htmlTemp.setPicRef(tt);
                            m = bf.readLine();
                            continue;
                        }

                        if(m.contains("alt=\"")){
                            htmlTemp.setHeader(m.substring(m.indexOf("=\"") + 2, m.substring(m.indexOf("=\"") + 2).indexOf("\"") + m.indexOf("=\"") + 2));
                            m = bf.readLine();
                            continue;
                        }

                        if(m.contains(seperatorEnd)){
                            if(htmlTemp.getData() != null){
                                lst.add(htmlTemp);
                            }

                            break;
                        }
                        m = bf.readLine();
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //parsing
        return lst;
    }


    private boolean isAd(String param){

        if(param.contains("politik")){
            return false;
        }
        if(param.contains("wirtschaft")){
            return false;
        }
        if(param.contains("kultur")){
            return false;
        }
        if(param.contains("wissen")){
            return false;
        }
        if(param.contains("digital")){
            return false;
        }
        if(param.contains("karriere")){
            return false;
        }
        return true;
    }
    private boolean isAdZeit(String param){
        return !param.contains("www.zeit.de");
    }
}
