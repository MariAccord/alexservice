package ru.alexservice34.persistence.entity.dictionaries;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "street", schema = "dictionaries")
public class Street {

    @EmbeddedId
    private StreetEmbedded pk;

    @NotNull
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    public StreetEmbedded getPk() {
        return pk;
    }

    public void setPk(StreetEmbedded pk) {
        this.pk = pk;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
