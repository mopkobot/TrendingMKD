package mk.mopkobot.persistance;

import mk.mopkobot.domain.Hashtag;
import mk.mopkobot.domain.User;
import mk.mopkobot.services.TwitterService;
import org.junit.Test;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwitterMapperTest {
    @Test
    public void shouldReturnListOfUsers() throws TwitterException {
        TwitterService twitterService = mock(TwitterService.class);
        TwitterMapper twitterMapper = new TwitterMapper(twitterService);

        List<String> tweetsOne = new ArrayList<String>();
        tweetsOne.add("testing");
        List<String> tweetsTwo = new ArrayList<String>();
        tweetsTwo.add("testing23");

        Map<String, List<String>> mapOfUserAndTweets = new HashMap<String,
                List<String>>();
        mapOfUserAndTweets.put("test", tweetsOne);
        mapOfUserAndTweets.put("test1", tweetsTwo);
        when(twitterService.getLatestUsersAndAssociatedTweets("@trendingMK")
                ).thenReturn(mapOfUserAndTweets);

        List<User> expected = new ArrayList<User>();

        expected.add(new User("test1", tweetsTwo));
        expected.add(new User("test", tweetsOne));

        assertThat(twitterMapper.users(), is(expected));
    }

    @Test
    public void shouldReturnListOfHashtags() throws TwitterException {
        TwitterService twitterService = mock(TwitterService.class);
        TwitterMapper twitterMapper = new TwitterMapper(twitterService);

        List<String> tweetsOne = new ArrayList<String>();
        tweetsOne.add("testing");
        List<String> tweetsTwo = new ArrayList<String>();
        tweetsTwo.add("testing23");

        Map<String, List<String>> mapOfHashTagsAndTweets = new HashMap<String,
                List<String>>();
        mapOfHashTagsAndTweets.put("test", tweetsOne);
        mapOfHashTagsAndTweets.put("test1", tweetsTwo);
        when(twitterService.getLatestHashtagsAndAssociatedTweets("@trendingMK")
        ).thenReturn(mapOfHashTagsAndTweets);

        List<Hashtag> expected = new ArrayList<Hashtag>();

        expected.add(new Hashtag("test1", tweetsTwo));
        expected.add(new Hashtag("test", tweetsOne));

        assertThat(twitterMapper.hashtags(), is(expected));
    }
}