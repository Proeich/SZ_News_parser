package eich.nickelson.common.classes;

public class HtmlFileHolder {

    private  String data;
    private  String header;
    private  String picRef;

    public HtmlFileHolder(String data, String header, String picRef) {
        this.data = data;
        this.header = header;
        this.picRef = picRef;
    }

    public String getData() {
        return data;
    }

    public String getHeader() {
        return header;
    }

    public String getPicRef() {
        return picRef;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setPicRef(String picRef) {
        this.picRef = picRef;
    }
}

