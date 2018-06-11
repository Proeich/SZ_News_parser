package eich.nickelson.common.classes;

import twitter4j.HashtagEntity;

import java.util.Date;

public class SocialFileHolder {

    private String tweetText;
    private int retweetCount;
    private HashtagEntity[] hashTags;
    private Date postDate;

    public SocialFileHolder(String tweetText, int retweetCount, HashtagEntity[] hashTags, Date postDate) {
        this.tweetText = tweetText;
        this.retweetCount = retweetCount;
        this.hashTags = hashTags;
        this.postDate = postDate;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public HashtagEntity[] getHashTags() {
        return hashTags;
    }

    public void setHashTags(HashtagEntity[] hashTags) {
        this.hashTags = hashTags;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }
}
