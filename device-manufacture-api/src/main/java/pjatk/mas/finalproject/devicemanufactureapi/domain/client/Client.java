package pjatk.mas.finalproject.devicemanufactureapi.domain.client;

import lombok.*;
import org.hibernate.annotations.Parent;
import pjatk.mas.finalproject.devicemanufactureapi.domain.types.IdDocument;
import pjatk.mas.finalproject.devicemanufactureapi.domain.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client{

    private IdDocument idDocument;
    private String phone;
    private String regon;

    @OneToMany(mappedBy = "client.recommender")
    private List<User> recommended;

    @ManyToOne
    private User recommender;

    @Parent
    private User user;

    public Optional<IdDocument> getIdDocument() {
        return Optional.ofNullable(idDocument);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return user.equals(client.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}
