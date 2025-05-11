package aiss.GithubMiner.service;

import aiss.GithubMiner.model.*;
import aiss.GithubMiner.model.commit.Commit;
import aiss.GithubMiner.model.issue.Issue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class FullProjectService {

    @Autowired
    RestTemplate restTemplate;

    public Project getProject(String owner, String repo, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Project> request = new HttpEntity<>(null,headers);

        ResponseEntity<Project> response = restTemplate.exchange("https://api.github.com/repos/" + owner + "/" + repo, HttpMethod.GET, request, Project.class);
        return response.getBody();

        //También lo podriamos haber hecho asi, pero no hubieramos podido utilizar el token

        // return restTemplate.getForObject("https://api.github.com/repos/" + owner + "/" + repo, Project.class);
    }

    public List<Commit> getCommits(String owner, String repo, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Commit[]> request = new HttpEntity<>(null,headers);
        ResponseEntity<Commit[]> response = restTemplate.exchange("https://api.github.com/repos/" + owner + "/" + repo + "/commits", HttpMethod.GET, request, Commit[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));

        //Commit[] commits = restTemplate.getForObject("https://api.github.com/repos/" + owner + "/" + repo + "/commits", Commit[].class);
        //return Arrays.asList(commits);
    }


    public List<Issue> getIssues(String owner, String repo, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Issue[]> request = new HttpEntity<>(null,headers);

        ResponseEntity<Issue[]> response = restTemplate.exchange("https://api.github.com/repos/" + owner + "/" + repo + "/issues", HttpMethod.GET, request, Issue[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));


        //Issue[] issues = restTemplate.getForObject("https://api.github.com/repos/" + owner + "/" + repo + "/issues", Issue[].class);
        //return issues != null ? Arrays.asList(issues) : new ArrayList<>();
    }




    public List<IssueWithComment> getIssueCompleta(String owner, String repo, String token) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);

            List<Issue> issues = getIssues(owner, repo, token);
            List<IssueWithComment> res = new ArrayList<>();
            for (Issue i : issues) { //recorremos cada issue

                //Le aportamos el nombre a cada autor de cada issue

                String nombreUsuario = i.getAuthor().getUsername();

                HttpEntity<User> request = new HttpEntity<>(null,headers);
                ResponseEntity<User> response = restTemplate.exchange("https://api.github.com/users/" + nombreUsuario, HttpMethod.GET, request, User.class);
                User user = response.getBody();
                if (user != null) {
                    i.getAuthor().setName(user.getName());
                }

                //Le aportamos el nombre a cada usuario asignado

                if (i.getAssignee() != null) {
                    String nombreUsuarioAsignado = i.getAssignee().getUsername();
                    HttpEntity<User> request2 = new HttpEntity<>(null,headers);
                    ResponseEntity<User> response2 = restTemplate.exchange("https://api.github.com/users/" + nombreUsuarioAsignado, HttpMethod.GET, request2, User.class);
                    User userAsignee = response2.getBody();

                    if (userAsignee != null) {
                        i.getAssignee().setName(userAsignee.getName());
                    }
                }

                //Le metemos a cada issue sus respectivos comments

                Integer numeroIssue = i.getNumber();
                HttpEntity<Comment[]> request3 = new HttpEntity<>(null,headers);
                ResponseEntity<Comment[]> response3 = restTemplate.exchange("https://api.github.com/repos/"+ owner + "/" + repo + "/issues/"+ numeroIssue +"/comments", HttpMethod.GET, request3, Comment[].class);
                List<Comment> comments = Arrays.asList(Objects.requireNonNull(response3.getBody()));
                //Le metemos a cada autor de un comentario su respectivo nombre

                for (Comment c : comments) {
                    String nombreUsuarioComment = c.getAuthor().getUsername();
                    HttpEntity<User> request4 = new HttpEntity<>(null,headers);
                    ResponseEntity<User> response4 = restTemplate.exchange("https://api.github.com/users/" + nombreUsuarioComment, HttpMethod.GET, request4, User.class);
                    User userComment  = response4.getBody();
                    if (userComment != null) {
                        c.getAuthor().setName(userComment.getName());
                    }
                }
                res.add(new IssueWithComment(i, comments));

            }

        return res;
    }

    public FullProject getFullProject(String owner, String repo, String token) {

        return new FullProject(getProject(owner,repo,token), getCommits(owner,repo,token), getIssueCompleta(owner,repo,token));
    }

    public void postProject(FullProject project) {
        String gitMinerUrl = "http://localhost:8080/gitminer/projects"; // Ajusta según tu GitMiner
        HttpHeaders headers = new HttpHeaders();
        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    gitMinerUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(project, headers),
                    Void.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Error al enviar datos a GitMiner. Código: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error en GitMiner: " + e.getResponseBodyAsString());
        }
    }




}


