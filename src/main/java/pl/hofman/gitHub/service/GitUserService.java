package pl.hofman.gitHub.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.hofman.gitHub.model.GitUser;
import pl.hofman.gitHub.model.Repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Component
public class GitUserService {

    @Autowired
    GitUser gitUser;

    public GitUser setGitUser (String gitUserLogin) {
        GitUser gitUser = new GitUser();
        gitUser.setLoginGit(gitUserLogin);
        return gitUser;
    }


    public Long sumStars(ArrayList<Repo> repos) {
        Long sumStars = 0L;
        for (Repo repo : repos) {
            sumStars = sumStars + repo.getStarsRep();
        }
        return sumStars;
    }


    public ArrayList<Repo> reposList(String loginGit) throws IOException, ParseException {

        String linkUser = "https://api.github.com/users/" + loginGit;

        URL link = new URL(linkUser);
        HttpURLConnection connection = (HttpURLConnection) link.openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(bufferedReader);

        Long publicReposNo = (Long) jsonObject.get("public_repos");
        double pagesToSearch = Math.ceil((double) publicReposNo / 100);

        ArrayList<Repo> repos = new ArrayList<>();

        for (int j = 0; j < pagesToSearch; j++) {
            String linkRepo = "https://api.github.com/users/" + loginGit + "/repos?per_page=100&page=" + (j + 1);
            URL linkRep = new URL(linkRepo);
            HttpURLConnection connectionRep = (HttpURLConnection) linkRep.openConnection();
            BufferedReader bufferedReaderRep = new BufferedReader(new InputStreamReader(connectionRep.getInputStream()));
            JSONArray jsonArrayRep = (JSONArray) parser.parse(bufferedReaderRep);

            for (int i = 0; i < jsonArrayRep.size(); i++) {
                Repo repo = new Repo();
                JSONObject jsonObjRep = (JSONObject) jsonArrayRep.get(i);
                String nameRep = (String) jsonObjRep.get("name");
                Long starsRep = (Long) jsonObjRep.get("stargazers_count");
                repo.setStarsRep(starsRep);
                repo.setNameRep(nameRep);
                repos.add(repo);
            }
        }
        return repos;
    }
}
