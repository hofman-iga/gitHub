package pl.hofman.gitHub.model;

import org.springframework.stereotype.Component;


public class Repo {

    private String nameRepo;
    private Long starsRepo;

    public Repo(){
    }

    public String getNameRep() {
        return nameRepo;
    }

    public void setNameRep(String nameRepo) {
        this.nameRepo = nameRepo;
    }

    public Long getStarsRep() {
        return starsRepo;
    }

    public void setStarsRep(Long starsRepo) {
        this.starsRepo = starsRepo;
    }
}
