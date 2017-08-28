package org.harmony.test.javaee.jpa.events;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "T_EVENT")
@EntityListeners({ EntityEventListener.class })
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public EventEntity() {
    }

    public EventEntity(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PrePersist
    private void prePersist() {
        System.out.println("pre persist");
    }

    @PostPersist
    private void postPersist() {
        System.out.println("post persist");
    }

    @PreUpdate
    private void preUpdate() {
        System.out.println("pre update");
    }

    @PostUpdate
    private void postUpdate() {
        System.out.println("post update");
    }

    @PreRemove
    private void preRemove() {
        System.out.println("pre remove");
    }

    @PostRemove
    private void postRemove() {
        System.out.println("post remove");
    }

    @PostLoad
    private void postLoad() {
        System.out.println("post load");
    }

}