package eich.nickelson.common.downloading.getter;

import eich.nickelson.common.classes.SocialFileHolder;
import eich.nickelson.common.downloading.SocialGrabber;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TwitterGetterTest {

    @Test
    public void getSocial(){
        SocialGrabber socialGrabber = new TwitterGetter();

        List<SocialFileHolder> lst = socialGrabber.getSocial();

        for(SocialFileHolder sfh : lst){
            System.out.println(sfh.getTweetText());
        }

    }

}
