package aiss.BitBucketMiner.service;

import aiss.BitBucketMiner.model.*;
import aiss.BitBucketMiner.dto.*;
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

    @Value("${bitbucket.api.default.ncomments:2}")
    private Integer defaultNComments;

    @Value("${gitminer.api.url}")
    private String gitMinerUrl;

    private final RestTemplate restTemplate;

    public BitBucketService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Project fetchAndProcessRepository(String workspace, String repoSlug, Integer nCommits, Integer nIssues, Integer maxPages) {
        nCommits = Optional.ofNullable(nCommits).orElse(defaultNCommits);
        nIssues = Optional.ofNullable(nIssues).orElse(defaultNIssues);
        maxPages = Optional.ofNullable(maxPages).orElse(defaultMaxPages);
        if (maxPages <= 0) {
            maxPages = Integer.MAX_VALUE; // fetch all pages if non-positive
        }

        Project project = fetchProject(workspace, repoSlug);
        project.setCommits(getCommits(workspace, repoSlug, nCommits, maxPages));
        project.setIssues(getIssues(workspace, repoSlug, nIssues, maxPages));
        System.out.println("Obtenidas issues: " + project.getIssues());

        return project;
    }

    public ProjectDTO fetchAndConvertToDTO(String workspace, String repoSlug, Integer nCommits, Integer nIssues, Integer maxPages) {
        Project project = fetchAndProcessRepository(workspace, repoSlug, nCommits, nIssues, maxPages);
        return mapToDTO(project, workspace, repoSlug, maxPages);
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
        String url = buildUrl("/repositories/{workspace}/{repoSlug}/commits", workspace, repoSlug) + "?pagelen=" + perPage;
        return fetchPaginatedData(url, maxPages, Commit.class);
    }

    private List<Issue> getIssues(String workspace, String repoSlug, Integer perPage, Integer maxPages) {
        String url = buildUrl("/repositories/{workspace}/{repoSlug}/issues", workspace, repoSlug) + "?pagelen=" + perPage;
        return fetchPaginatedData(url, maxPages, Issue.class);
    }

    private List<Comment> getComments(String workspace, String repoSlug, String issueId,
                                      Integer perPage, Integer maxPages) {
        perPage = Optional.ofNullable(perPage).orElse(defaultNComments);
        maxPages = Optional.ofNullable(maxPages).orElse(defaultMaxPages);
        if (maxPages <= 0) {
            maxPages = Integer.MAX_VALUE;
        }

        String url = UriComponentsBuilder.fromUriString(bitbucketUrl)
                .path("/repositories/{workspace}/{repoSlug}/issues/{issueId}/comments")
                .buildAndExpand(
                        Map.of("workspace", workspace,
                               "repoSlug", repoSlug,
                               "issueId", issueId))
                .toUriString() + "?pagelen=" + perPage;
        System.out.println("Obtenidos comments de: " + url + "\n Max Pages: " + maxPages);
        try {
            return fetchPaginatedData(url, maxPages, Comment.class);
        } catch (RuntimeException e) {
            System.out.println("Error fetching comments for issue " + issueId + ": " + e.getMessage());
            return java.util.Collections.emptyList();
        }
    }

    private <T> List<T> fetchPaginatedData(String initialUrl, int maxPages, Class<T> type) {
        List<T> results = new ArrayList<>();
        String nextUrl = initialUrl;
        int pagesFetched = 0;
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
                                System.out.println("JSON del comentario:\n" + json);
                                T obj = objectMapper.readValue(json, type);
                                if (obj != null) results.add(obj);
                            } catch (Exception e) {
                                System.out.println("Error deserializando item: " + e.getMessage());
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

    public ProjectDTO postProject(ProjectDTO dto) {
        try {
            ResponseEntity<ProjectDTO> response = restTemplate.postForEntity(gitMinerUrl, dto, ProjectDTO.class);
            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                throw new RuntimeException("GitMiner API Error: " + response.getStatusCode());
            }
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("GitMiner API Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        }
    }

    private String buildUrl(String path, String workspace, String repoSlug) {
        return UriComponentsBuilder.fromUriString(bitbucketUrl)
                .path(path)
                .buildAndExpand(Map.of("workspace", workspace, "repoSlug", repoSlug))
                .toUriString();
    }

    public ProjectDTO mapToDTO(Project project, String workspace, String repoSlug, Integer maxPages) {
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setWebUrl(project.getWebUrl());

        List<CommitDTO> commitDTOs = new ArrayList<>();
        if (project.getCommits() != null) {
            for (Commit commit : project.getCommits()) {
                CommitDTO c = new CommitDTO();
                c.setId(commit.getId());
                c.setTitle(commit.getTitle());
                c.setMessage(commit.getMessage());
                c.setAuthorName(commit.getAuthorName());
                c.setAuthorEmail(commit.getAuthorEmail());
                c.setAuthoredDate(commit.getAuthoredDate());
                c.setWebUrl(commit.getWebUrl());
                commitDTOs.add(c);
            }
        }
        dto.setCommits(commitDTOs);

        List<IssueDTO> issueDTOs = new ArrayList<>();
        if (project.getIssues() != null) {
            System.out.println("Número de issues obtenidas: " + project.getIssues().size());
            for (Issue issue : project.getIssues()) {
                IssueDTO i = new IssueDTO();
                i.setId(issue.getId());
                i.setTitle(issue.getTitle());
                i.setDescription(issue.getDescription());
                i.setState(issue.getState());
                i.setCreatedAt(issue.getCreatedAt());
                i.setUpdatedAt(issue.getUpdatedAt());
                i.setClosedAt(issue.getClosedAt());
                i.setVotes(issue.getVotes());
                i.setLabels(issue.getLabels());
                i.setAuthor(issue.getAuthor() != null ? mapUser(issue.getAuthor()) : null);
                i.setAssignee(issue.getAssignee() != null ? mapUser(issue.getAssignee()) : null);

                List<Comment> comments = getComments(workspace, repoSlug, issue.getId(),
                        defaultNComments, maxPages);
                List<CommentDTO> commentDTOs = new ArrayList<>();
                for (Comment comment : comments) {
                    CommentDTO cd = new CommentDTO();
                    cd.setId(comment.getId());

                    String body = comment.getBody();
                    if (body == null || body.trim().isEmpty()) {
                        body = "No message provided";  // Puedes cambiar este mensaje por el que prefieras
                    }
                    cd.setBody(body);
                    cd.setCreatedAt(comment.getCreatedAt());
                    cd.setUpdatedAt(comment.getUpdatedAt());
                    cd.setAuthor(comment.getAuthor() != null ? mapUser(comment.getAuthor()) : getDefaultUser());
                    commentDTOs.add(cd);
                }
                i.setComments(commentDTOs);
                issueDTOs.add(i);
            }
        }
        dto.setIssues(issueDTOs);

        return dto;
    }

    private UserDTO mapUser(User user) {
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setWebUrl(user.getWebUrl());
        return dto;
    }

    private UserDTO getDefaultUser() {
        UserDTO defaultUser = new UserDTO();
        defaultUser.setId("0");
        defaultUser.setUsername("unknown");
        defaultUser.setName("Unknown User");
        defaultUser.setAvatarUrl(null);
        defaultUser.setWebUrl(null);
        return defaultUser;
    }

}
