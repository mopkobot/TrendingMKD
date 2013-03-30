package mk.mopkobot.domain;


import java.util.List;

public class User {
    private String user;

    private List<String> tweets;
    public User(String user, List<String> tweets) {
        this.user = user;
        this.tweets = tweets;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user1 = (User) o;

        if (!tweets.equals(user1.tweets)) return false;
        if (!user.equals(user1.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + tweets.hashCode();
        return result;
    }

    public String getUser() {
        return user;
    }

    public List<String> getTweets() {
        return tweets;
    }
}
