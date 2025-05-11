package aiss.BitBucketMiner.service;

import aiss.BitBucketMiner.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
public class BitBucketService {

    @Value("${bitbucket.api.url}")
    private String bitbucketUrl;

    @Value("${bitbucket.api.default.ncommits:2}")
    private Integer defaultNCommits;

    @Value("${bitbucket.api.default.nissues:2}")
    private Integer defaultNIssues;

    @Value("${bitbucket.api.default.maxpages:1}")
    private Integer defaultMaxPages;

    private final RestTemplate restTemplate;

    public BitBucketService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Project fetchAndProcessRepository(String workspace, String repoSlug, Integer nCommits, Integer nIssues, Integer maxPages) {
        // Validación de parámetros con valores por defecto
        nCommits = Optional.ofNullable(nCommits).orElse(defaultNCommits);
        nIssues = Optional.ofNullable(nIssues).orElse(defaultNIssues);
        maxPages = Optional.ofNullable(maxPages).orElse(defaultMaxPages);

        // Obtener proyecto base
        Project project = fetchProject(workspace, repoSlug);

        // Obtener commits e issues
        project.setCommits(getCommits(workspace, repoSlug, nCommits, maxPages));
        project.setIssues(getIssues(workspace, repoSlug, nIssues, maxPages));

        return project;
    }

    private Project fetchProject(String workspace, String repoSlug) {
        String url = buildUrl("/repositories/{workspace}/{repoSlug}", workspace, repoSlug);

        try {
            ResponseEntity<Project> response = restTemplate.getForEntity(url, Project.class);

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                throw new RuntimeException("Failed to fetch project: " + response.getStatusCode());
            }

            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Bitbucket API Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        }
    }

    private List<Commit> getCommits(String workspace, String repoSlug, Integer perPage, Integer maxPages) {
        String url = buildUrl("/repositories/{workspace}/{repoSlug}/commits", workspace, repoSlug)
                + "?pagelen=" + perPage;
        return fetchPaginatedData(url, maxPages, Commit.class);
    }

    private List<Issue> getIssues(String workspace, String repoSlug, Integer perPage, Integer maxPages) {
        String url = buildUrl("/repositories/{workspace}/{repoSlug}/issues", workspace, repoSlug)
                + "?pagelen=" + perPage;
        return fetchPaginatedData(url, maxPages, Issue.class);
    }

    private <T> List<T> fetchPaginatedData(String initialUrl, int maxPages, Class<T> type) {
        List<T> results = new ArrayList<>();
        String nextUrl = initialUrl;
        int pagesFetched = 0;

        // Jackson ObjectMapper para convertir Map a objeto directamente
        com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

        while (nextUrl != null && pagesFetched < maxPages) {
            try {
                ResponseEntity<Map> response = restTemplate.getForEntity(nextUrl, Map.class);

                if (response.getBody() != null) {
                    List<Map<String, Object>> values = (List<Map<String, Object>>) response.getBody().get("values");
                    if (values != null) {
                        for (Map<String, Object> item : values) {
                            try {
                                String json = objectMapper.writeValueAsString(item);
                                T obj = objectMapper.readValue(json, type);
                                if (obj != null) results.add(obj);
                            } catch (Exception e) {
                                System.out.println("❌ Error deserializando item: " + e.getMessage());
                            }
                        }
                    }

                    nextUrl = (String) response.getBody().get("next");
                    pagesFetched++;
                }
            } catch (Exception e) {
                throw new RuntimeException("Error fetching paginated data: " + e.getMessage());
            }
        }
        return results;
    }


    public void postProject(Project project) {
        String gitMinerUrl = "http://localhost:8080/gitminer/projects";

        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(gitMinerUrl, project, Void.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("GitMiner API Error: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("GitMiner API Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        }
    }

    private String buildUrl(String path, String workspace, String repoSlug) {
        return UriComponentsBuilder.fromUriString(bitbucketUrl)
                .path(path)
                .buildAndExpand(Map.of(
                        "workspace", workspace,
                        "repoSlug", repoSlug
                ))
                .toUriString();
    }
}
