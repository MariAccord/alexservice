package ru.alexservice34.persistence.entity.dictionaries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "order_status_type", schema = "dictionaries")
public class OrderStatysType {

    @Id
    private UUID id;

    @NotNull
    @Size(min = 1, max = 10)
    private String code;

    @NotNull
    @Size(min = 2, max = 100)
    private String value;

    @NotNull
    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    private Boolean enabled;



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
