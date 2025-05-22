package aiss.GithubMiner.service;

import aiss.GithubMiner.model.*;
import aiss.GithubMiner.model.commit.Commit;
import aiss.GithubMiner.model.issue.Issue;
import aiss.GithubMiner.parse.model.*;
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

    /*
    public ProjectDTO getFullProject(String owner, String repo, String token) {

        Project project = getProject(owner, repo, token);
        List<Commit> commit = getCommits(owner, repo, token);
        List<IssueWithComment> issue= getIssueCompleta(owner, repo, token);


        return new ProjectDTO(project,commit,issue);
    }

     */

    public ProjectDTO getFullProject(String owner, String repo, String token) {
        ProjectDTO projectDTO = new ProjectDTO();
        Project project = getProject(owner, repo, token);

        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setWebUrl(project.getWebUrl());

        List<CommitDTO> commitDTO = new ArrayList<>();
        List<Commit> commits = getCommits(owner, repo, token);

        for (Commit c : commits) {
            CommitDTO  commit = new CommitDTO();
            commit.setId(c.getId());
            commit.setTitle(c.getTitle());
            commit.setMessage(c.getMessage());
            commit.setAuthorName(c.getAuthor_name());
            commit.setAuthorEmail(c.getAuthor_email());
            commit.setAuthoredDate(c.getAuthor_date());
            commit.setWebUrl(c.getWeb_urlC());
            commitDTO.add(commit);

        }

        List<IssueDTO> issueDTO = new ArrayList<>();
        List<IssueWithComment> issues = getIssueCompleta(owner, repo, token);

        for(IssueWithComment c : issues){
            IssueDTO issue = new IssueDTO();
            List<CommentDTO> comments = new ArrayList<>();

            issue.setId(c.getIssue().getId());
            issue.setTitle(c.getIssue().getTitle());
            issue.setDescription(c.getIssue().getDescription());
            issue.setState(c.getIssue().getState());
            issue.setCreatedAt(c.getIssue().getCreatedAt());
            issue.setUpdatedAt(c.getIssue().getUpdatedAt());
            issue.setClosedAt(c.getIssue().getClosedAt());
            issue.setLabels(c.getIssue().getLabels());
            issue.setVotes(c.getIssue().getVotes());
            issue.setAuthor(c.getIssue().getAuthor());
            issue.setAssignee(c.getIssue().getAssignee());

            for (Comment comment : c.getComments()) {
                CommentDTO commentDTO = new CommentDTO();
                commentDTO.setId(comment.getId());
                commentDTO.setBody(comment.getBody());
                commentDTO.setCreatedAt(comment.getCreatedAt());
                commentDTO.setUpdatedAt(comment.getUpdatedAt());
                commentDTO.setAuthor(comment.getAuthor());
                comments.add(commentDTO);
            }
            issue.setComments(comments);
            issueDTO.add(issue);


        }
        projectDTO.setIssues(issueDTO);
        projectDTO.setCommits(commitDTO);

        return projectDTO;


    }







    public void postProject(ProjectDTO project) {
        String gitMinerUrl = "http://localhost:8080/gitminer/projects";
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


