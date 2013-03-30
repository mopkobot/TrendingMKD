package mk.mopkobot.domain;

import java.util.List;

public class Hashtag {
    private String hashtag;
    private final List<String> tweets;


    public Hashtag(String hashtag, List<String> tweets) {
        this.hashtag = hashtag;
        this.tweets = tweets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hashtag hashtag1 = (Hashtag) o;

        if (!hashtag.equals(hashtag1.hashtag)) return false;
        if (!tweets.equals(hashtag1.tweets)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hashtag.hashCode();
        result = 31 * result + tweets.hashCode();
        return result;
    }

    public String getHashtag() {
        return hashtag;
    }

    public List<String> getTweets() {
        return tweets;
    }
}
