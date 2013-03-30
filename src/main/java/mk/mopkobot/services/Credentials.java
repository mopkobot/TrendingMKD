package mk.mopkobot.services;

import twitter4j.conf.ConfigurationBuilder;

public class Credentials {
    public static ConfigurationBuilder getConfigForTwitter()
    {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("zXHEUc7uAHQH462mbIDhWQ")
                .setOAuthConsumerSecret("EfACfhiAHl2XXfKtXRINXzBSJ8p5PM9SxjtSkHSQ")
                .setOAuthAccessToken("365007365-AH862o7pTVMwmTDR9IunhUMCGdD2hnw5iERFI1u2")
                .setOAuthAccessTokenSecret("N62ji3py7gZ0Z9ifKsvGtIBHOxx3R33e95dcoRL0");
        return cb;
    }
}
