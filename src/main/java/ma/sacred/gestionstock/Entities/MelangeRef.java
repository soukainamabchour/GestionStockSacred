package ma.sacred.gestionstock.Entities;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MelangeRef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String reference;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "reference")
    private List<MelangeEmplacement> emplacements;
    @OneToMany(mappedBy = "reference")
    private List<Melange> melanges;
}
