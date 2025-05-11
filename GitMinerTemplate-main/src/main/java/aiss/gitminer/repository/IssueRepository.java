package aiss.gitminer.repository;

import aiss.gitminer.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue,String> {

    List<Issue> findByAuthor_Id(String authorId);                 // solo autor
    List<Issue> findByState(String state);                        // solo estado
    List<Issue> findByAuthor_IdAndState(String authorId, String state); // autor + estado
}
