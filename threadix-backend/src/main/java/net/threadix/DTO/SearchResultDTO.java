package net.threadix.DTO;
import net.threadix.model.Post;

import javax.annotation.processing.SupportedOptions;
import java.util.List;
import net.threadix.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResultDTO {
    private List<User> users;
    private List<Post> postTitles;

    // Constructors
    public SearchResultDTO(List<User> users, List<Post> postTitles) {
        this.users = users;
        this.postTitles = postTitles;
    }
}
