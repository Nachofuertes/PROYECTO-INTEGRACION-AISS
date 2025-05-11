package aiss.GithubMiner.transformer;

import aiss.GithubMiner.model.issue.Issue;
import aiss.GithubMiner.modelDTO.IssueDTO;

public class IssueTransformer {

    public static IssueDTO transform(Issue issue) {
        IssueDTO dto = new IssueDTO();
        dto.setId(issue.getId());
        dto.setTitle(issue.getTitle());
        dto.setDescription(issue.getDescription());
        dto.setState(issue.getState());
        dto.setCreatedAt(issue.getCreatedAt());
        dto.setUpdatedAt(issue.getUpdatedAt());
        dto.setClosedAt(issue.getClosedAt());
        dto.setLabels(issue.getLabels());

        //Transformar la reacción antes de asignarla
        dto.setReactions(transformReaction(issue.getReactions()));

        dto.setAuthor(issue.getAuthor());
        dto.setAssignee(issue.getAssignee());
        return dto;
    }

    //Método para transformar `Issue.Reaction` a `IssueDTO.Reaction`

    private static IssueDTO.Reaction transformReaction(Issue.Reaction reaction) {
        if (reaction == null) return null;

        IssueDTO.Reaction dtoReaction = new IssueDTO.Reaction();
        dtoReaction.setVotes(reaction.getVotes());
        return dtoReaction;
    }


}
