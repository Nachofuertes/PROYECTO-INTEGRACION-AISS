package aiss.GithubMiner.service;

import aiss.GithubMiner.model.FullProject;
import aiss.GithubMiner.model.IssueWithComment;
import aiss.GithubMiner.model.Project;
import aiss.GithubMiner.model.issue.Issue;
import aiss.GithubMiner.model.commit.Commit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FullProjectServiceTest {

    @Autowired
    FullProjectService fullProjectService;

    @Test
    void getProject() {
        Project proyecto = fullProjectService.getProject("torvalds", "linux","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");
        System.out.println(proyecto);
    }
    @Test
    void getCommits() {
        List<Commit> commits = fullProjectService.getCommits("torvalds", "linux", "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");
        System.out.println(commits);
    }


    @Test
    void getIssues() {
        List<Issue> issue = fullProjectService.getIssues("torvalds", "linux", "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");
        System.out.println(issue);
    }

    @Test
    void getIssueCompleta() {
        List<IssueWithComment> issue = fullProjectService.getIssueCompleta("torvalds", "linux", "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");
        System.out.println(issue);
    }



    @Test
    @DisplayName("GET FULL PROJECT")
    void getFullProject() {

        FullProject proyectoCompleto = fullProjectService.getFullProject("torvalds", "linux", "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");
        System.out.println(proyectoCompleto);


    }


}