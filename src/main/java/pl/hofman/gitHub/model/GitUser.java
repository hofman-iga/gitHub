package pl.hofman.gitHub.model;

import org.springframework.stereotype.Component;


import java.util.ArrayList;


@Component
public class GitUser {


    private String loginGit;
    private Long reposNumber;
    private ArrayList<String> repos;

    public GitUser() {
    }

    public String getLoginGit() {
        return loginGit;
    }

    public void setLoginGit(String loginGit) {
        this.loginGit = loginGit;
    }

    public Long getReposNumber() {
        return reposNumber;
    }

    public void setReposNumber(Long reposNumber) {
        this.reposNumber = reposNumber;
    }

    public ArrayList<String> getRepos() {
        return repos;
    }

    public void setRepos(ArrayList<String> repos) {
        this.repos = repos;
    }
}

