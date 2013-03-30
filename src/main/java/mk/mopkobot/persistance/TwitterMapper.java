package mk.mopkobot.persistance;

import mk.mopkobot.domain.Hashtag;
import mk.mopkobot.domain.User;
import mk.mopkobot.services.TwitterService;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TwitterMapper {
    TwitterService twitterService;
    public TwitterMapper(TwitterService twitterService)
    {

        this.twitterService = twitterService;
    }

    public List<Hashtag> hashtags() throws TwitterException {
        List<Hashtag> result = new ArrayList<Hashtag>();

        Map<String, List<String>> hashtagsObj = twitterService
                .getLatestHashtagsAndAssociatedTweets("@trendingMK");

        for(Map.Entry entry:hashtagsObj.entrySet()){
            result.add(new Hashtag(entry.getKey().toString(),
                    (List<String>)entry.getValue()));
        }
        return result;
    }

    public List<User> users() throws TwitterException {
        List<User> result = new ArrayList<User>();

        Map<String, List<String>> usersAndTweets = twitterService
                .getLatestUsersAndAssociatedTweets("@trendingMK");

        for(Map.Entry entry:usersAndTweets.entrySet()){
            result.add(new User(entry.getKey().toString(),
                    (List<String>)entry.getValue()));
        }
        return result;
    }
}
