package compulsory.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "id_person")
    private int idPerson;

    private String text;

    @ManyToOne
    @JoinColumn(name = "id_person", insertable = false, updatable = false)
    private Person person;

    public Message(int id_person, String text) {
        this.idPerson = id_person;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", idPerson=" + idPerson + ", text='" + text + '\'' + '}';
    }
}
