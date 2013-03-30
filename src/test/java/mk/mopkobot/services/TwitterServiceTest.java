package mk.mopkobot.persistance;

import mk.mopkobot.services.TwitterService;
import org.junit.Test;
import org.mockito.Matchers;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TwitterServiceTest {

    @Test
    public void shouldReturnLatestTweetsWithASpecificBeggingFromASpecificUser() throws TwitterException {
        Twitter twitter = mock(Twitter.class);
        TwitterService twitterService = spy(new TwitterService(twitter));

        ResponseList<Status> mockResponse = mock(ResponseList.class);

        Status tweetOne =
                mock
                        (Status
                                .class);
        Status tweetTwo = mock(Status.class);
        Status tweetThree = mock(Status.class);
        when(tweetOne.getText()).thenReturn("test for twitter");
        when(tweetTwo.getText()).thenReturn("test for fb");
        when(tweetThree.getText()).thenReturn("testing for twitter");
        mockResponse.add(tweetOne);
        mockResponse.add(tweetTwo);
        mockResponse.add(tweetThree);

        when(mockResponse.iterator()).thenReturn(Arrays.asList(tweetOne,
                tweetTwo, tweetThree)
                .iterator());

        when(twitter.getUserTimeline("test")).thenReturn(mockResponse);

        assertThat(twitterService.findLatest("test", "testing"),
                is("testing for twitter"));
    }

    @Test
    public void shouldReturnNullIfItCantFindATweetWithSpecificStart() throws
            TwitterException {
        Twitter twitter = mock(Twitter.class);
        TwitterService twitterService = spy(new TwitterService(twitter));

        ResponseList<Status> mockResponse = mock(ResponseList.class);

        Status tweetOne =
                mock
                        (Status
                                .class);
        Status tweetTwo = mock(Status.class);
        Status tweetThree = mock(Status.class);
        when(tweetOne.getText()).thenReturn("test for twitter");
        when(tweetTwo.getText()).thenReturn("test for fb");
        when(tweetThree.getText()).thenReturn("test for twitter");
        mockResponse.add(tweetOne);
        mockResponse.add(tweetTwo);
        mockResponse.add(tweetThree);

        when(mockResponse.iterator()).thenReturn(Arrays.asList(tweetOne,
                tweetTwo, tweetThree)
                .iterator());

        when(twitter.getUserTimeline("test")).thenReturn(mockResponse);

        assertNull(twitterService.findLatest("test", "testing"));
    }


    @Test
    public void shouldReturnStringListOfTweets() throws TwitterException {
        Twitter twitter = mock(Twitter.class);
        TwitterService twitterService = spy(new TwitterService(twitter));

        ResponseList<Status> mockResponse = mock(ResponseList.class);

        Status tweetOne =
                mock
                        (Status
                                .class);
        Status tweetTwo = mock(Status.class);
        Status tweetThree = mock(Status.class);
        String tweetTextOne = "test for twitter";
        String tweetTextTwo = "test for fb";
        when(tweetOne.getText()).thenReturn(tweetTextOne);
        when(tweetTwo.getText()).thenReturn(tweetTextTwo);
        when(tweetThree.getText()).thenReturn(tweetTextOne);
        mockResponse.add(tweetOne);
        mockResponse.add(tweetTwo);
        mockResponse.add(tweetThree);

        when(mockResponse.iterator()).thenReturn(Arrays.asList(tweetOne,
                tweetTwo, tweetThree)
                .iterator());

        when(twitter.getUserTimeline("test")).thenReturn(mockResponse);
        List<String> expected = new ArrayList<String>();
        expected.add(tweetTextOne);
        expected.add(tweetTextTwo);
        expected.add(tweetTextOne);
        assertThat(twitterService.getTweetMessages(twitter.getUserTimeline
                ("test")), is(expected));
    }

    @Test
    public void shouldReturnHashMapOfTitlesAndAssociatedTweets() throws
            TwitterException {
        Twitter twitter = mock(Twitter.class);
        TwitterService twitterService = spy(new TwitterService(twitter));

        String tweetTextOne = "test: test for twitter";

        List<String> messages = new ArrayList<String>();
        messages.add("test");
        messages.add("testing");

        doReturn(messages).when(twitterService).getTweetMessages(Matchers
                .<ResponseList<Status>>any());
        Map<String, List<String>> expected = new HashMap<String,
                List<String>>();

        expected.put("test", messages);
        expected.put("for", messages);
        expected.put("twitter", messages);

        assertThat(twitterService.getTitlesAndTweets(tweetTextOne), is(expected));
    }


}
