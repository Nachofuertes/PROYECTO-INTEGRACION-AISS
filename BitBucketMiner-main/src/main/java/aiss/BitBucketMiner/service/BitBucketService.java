package aiss.BitBucketMiner.service;

import aiss.BitBucketMiner.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class BitBucketService {

    private final RestTemplate restTemplate;

    @Value("${bitbucket.api.url}")
    private String bitbucketUrl;

    @Value("${bitbucket.api.username}")
    private String bitbucketUsername;

    @Value("${bitbucket.api.token}")
    private String bitbucketToken;

    @Value("${bitbucket.api.default.ncommits}")
    private Integer defaultNCommits;

    @Value("${bitbucket.api.default.nissues}")
    private Integer defaultNIssues;

    @Value("${bitbucket.api.default.maxpages}")
    private Integer defaultMaxPages;

    @Autowired
    public BitBucketService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Obtiene datos de un repositorio (commits + issues)
    public Project getProject(String workspace, String repoSlug, Integer nCommits, Integer nIssues, Integer maxPages) {
        if (nCommits == null) nCommits = defaultNCommits;
        if (nIssues == null) nIssues = defaultNIssues;
        if (maxPages == null) maxPages = defaultMaxPages;

        String repoUrl = String.format("%s/repositories/%s/%s", bitbucketUrl, workspace, repoSlug);

        try {
            ResponseEntity<Project> response = restTemplate.exchange(
                    repoUrl,
                    HttpMethod.GET,
                    new HttpEntity<>(getHeaders()),
                    Project.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                Project project = response.getBody();
                project.setCommits(getCommits(workspace, repoSlug, nCommits, maxPages));
                project.setIssues(getIssues(workspace, repoSlug, nIssues, maxPages));
                return project;
            } else {
                throw new RuntimeException("Error al obtener el proyecto. Código de estado: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error en la API de Bitbucket: " + e.getResponseBodyAsString());
        }
    }

    // Obtiene commits paginados
    private List<Commit> getCommits(String workspace, String repoSlug, Integer perPage, Integer maxPages) {
        List<Commit> commits = new ArrayList<>();
        String commitsUrl = String.format("%s/repositories/%s/%s/commits", bitbucketUrl, workspace, repoSlug);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(commitsUrl)
                .queryParam("pagelen", perPage);

        String nextUrl = builder.toUriString();
        int pagesProcessed = 0;

        while (nextUrl != null && pagesProcessed < maxPages) {
            try {
                ResponseEntity<BitBucketCommitResponse> response = restTemplate.exchange(
                        nextUrl,
                        HttpMethod.GET,
                        new HttpEntity<>(getHeaders()),
                        BitBucketCommitResponse.class
                );

                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    commits.addAll(response.getBody().getValues());
                    nextUrl = response.getBody().getNext();
                    pagesProcessed++;
                } else {
                    break;
                }
            } catch (HttpClientErrorException e) {
                throw new RuntimeException("Error al obtener commits: " + e.getResponseBodyAsString());
            }
        }

        return commits;
    }

    // Obtiene issues paginados
    private List<Issue> getIssues(String workspace, String repoSlug, Integer perPage, Integer maxPages) {
        List<Issue> issues = new ArrayList<>();
        String issuesUrl = String.format("%s/repositories/%s/%s/issues", bitbucketUrl, workspace, repoSlug);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(issuesUrl)
                .queryParam("pagelen", perPage);

        String nextUrl = builder.toUriString();
        int pagesProcessed = 0;

        while (nextUrl != null && pagesProcessed < maxPages) {
            try {
                ResponseEntity<BitBucketIssueResponse> response = restTemplate.exchange(
                        nextUrl,
                        HttpMethod.GET,
                        new HttpEntity<>(getHeaders()),
                        BitBucketIssueResponse.class
                );

                if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                    issues.addAll(response.getBody().getValues());
                    nextUrl = response.getBody().getNext();
                    pagesProcessed++;
                } else {
                    break;
                }
            } catch (HttpClientErrorException e) {
                throw new RuntimeException("Error al obtener issues: " + e.getResponseBodyAsString());
            }
        }

        return issues;
    }

    // Envía datos a GitMiner
    public void postProject(Project project) {
        String gitMinerUrl = "http://localhost:8081/api/projects"; // Ajusta según tu GitMiner

        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    gitMinerUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(project, getHeaders()),
                    Void.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Error al enviar datos a GitMiner. Código: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Error en GitMiner: " + e.getResponseBodyAsString());
        }
    }

    // Configura headers con autenticación básica
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        //String auth = bitbucketUsername + ":" + bitbucketToken;
        //String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        //headers.set("Authorization", "Basic " + encodedAuth);
        //headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}