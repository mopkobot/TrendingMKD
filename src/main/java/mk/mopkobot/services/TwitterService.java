package mk.mopkobot.services;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwitterService
{
    private final Twitter twitterApi;

    public TwitterService(Twitter twitter) {
        twitterApi = twitter;
    }

    public Map<String, List<String>> getLatestHashtagsAndAssociatedTweets(String user) throws TwitterException {
        String latestTweet = findLatest(user, "Топ теми");
        Map<String, List<String>> hashTags = getTitlesAndTweets(latestTweet);
        return hashTags;
    }

    public Map<String, List<String>> getLatestUsersAndAssociatedTweets(String user) throws TwitterException {
        String lastTweet = findLatest(user, "Топ твитерџии денес");
        Map<String, List<String>> users = getTitlesAndTweets(lastTweet);
        return users;
    }

    public Map<String, List<String>> getTitlesAndTweets(String latestTweet)
            throws
        TwitterException {
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        String[] parts = getParts(latestTweet.split(": ")[1]);
        for(String unit : parts){

            result.put(unit, getTweetMessages(twitterApi.getUserTimeline
                    (latestTweet)));
        }
        return result;
    }

    private String[] getParts(String s) {
        String units = s;
        return units.split(" ");
    }

    public List<String> getTweetMessages(ResponseList<Status> tweets)
    {
        List<String> result = new ArrayList<String>();
        for(Status status : tweets)
        {
            result.add(status.getText());
        }
        return result;
    }

    public String findLatest(String user, String type) throws TwitterException {
        List<Status> statuses = twitterApi.getUserTimeline(user);
        for(Status status : statuses) {
            if(status.getText().startsWith(type)){
                return status.getText();
            }
        }
        return null;
    }
}