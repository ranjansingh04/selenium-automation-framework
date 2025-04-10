package com.ranjansingh.data;

public class LoginTestData {
    private final String username;
    private final String password;
    private final String expectedMessage;
    private final boolean shouldSucceed;
    private final String testId;
    private final String tag;

    public LoginTestData(String username, String password, String expectedMessage,
                         boolean shouldSucceed, String testId, String tag) {
        this.username = username;
        this.password = password;
        this.expectedMessage = expectedMessage;
        this.shouldSucceed = shouldSucceed;
        this.testId = testId;
        this.tag = tag;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getExpectedMessage() { return expectedMessage; }
    public boolean shouldSucceed() { return shouldSucceed; }
    public String getTestId() { return testId; }
    public String getTag() { return tag; }
}

