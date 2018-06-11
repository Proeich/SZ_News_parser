package eich.nickelson.common.downloading.getter;

import eich.nickelson.common.classes.SocialFileHolder;
import eich.nickelson.common.downloading.SocialGrabber;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

public class TwitterGetter implements SocialGrabber {
    private final Twitter twitter;

    public TwitterGetter() {
        System.setProperty("java.net.useSystemProxies", "true");

        this.twitter= TwitterFactory.getSingleton();
    }

    @Override
    public List<SocialFileHolder> getSocial() {

        try {
            List<Status> statuses = twitter.getHomeTimeline();
            List<SocialFileHolder> lst = new ArrayList<>();
            for (Status status: statuses) {
                lst.add(new SocialFileHolder(status.getText(),status.getRetweetCount(),status.getHashtagEntities(),status.getCreatedAt()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



}
