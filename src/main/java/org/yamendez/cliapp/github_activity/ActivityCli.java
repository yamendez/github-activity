package org.yamendez.cliapp.github_activity;

public class ActivityCli {
    public static void main(String[] args) {
        HttpService service = new HttpService();
        service.getGithubUserActivity();
    }
}
