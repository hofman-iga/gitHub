package pl.hofman.gitHub.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hofman.gitHub.model.Repo;
import pl.hofman.gitHub.service.GitUserService;

import java.io.IOException;
import java.util.ArrayList;

@RequestMapping("/")
@RestController
public class GitUserController {

    @Autowired
    GitUserService gitUserService;


    @GetMapping("repos/{userLogin}")
    //Swagger:
    @ApiOperation(value = "Endpoint enabling displaying list of user's repositories' name and number of stars.",
            notes = "Only public repositories are displayed.",
            response = Repo.class)

    public ResponseEntity reposAll(@ApiParam(value = "User's GitHub login", required = true)
            @PathVariable String userLogin) {

        try {
            ArrayList<Repo> repos = gitUserService.reposList(userLogin);
            return ResponseEntity.ok(repos);

        } catch (IOException e) {
            return new ResponseEntity<>("User " + userLogin + " not found!", HttpStatus.NOT_FOUND);

        } catch (ParseException e) {
            return new ResponseEntity<>("Parser " + userLogin + " not found!", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("repos/stars/{userLogin}")
//Swagger:
    @ApiOperation(value = "Endpoint enabling displaying total number of stars in all user's repositories.",
            notes = "Only public repositories are counted.",
            response = Repo.class)

    public ResponseEntity starsSum(@ApiParam(value = "User's GitHub login", required = true)
            @PathVariable String userLogin) {

        try {
            ArrayList<Repo> repos = gitUserService.reposList(userLogin);
            return ResponseEntity.ok("Sum of stars in user's " + userLogin + " repositories: " + gitUserService.sumStars(repos));

        } catch (IOException e) {
            return new ResponseEntity<>("User " + userLogin + " not found!", HttpStatus.NOT_FOUND);

        } catch (ParseException e) {
            return new ResponseEntity<>("Parser " + userLogin + " not found!", HttpStatus.NOT_FOUND);
        }
    }
}
