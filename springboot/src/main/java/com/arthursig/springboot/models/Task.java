package com.arthursig.springboot.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "task")
public class Task {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", unique = true)
    private long id;

    @ManyToOne // várias tarefas são de 1 usuário
    @JoinColumn(name = "user_id", nullable = false, updatable = false) // fazer referência a chave primária do usuário
    // não pode passar uma tarefa de um usuário com outro
    private User user;

    @Column(name = "description", length = 255, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 255)
    private String description;


    // MÉTODOS
    public Task(long id, User user, @NotNull @NotEmpty @Size(min = 1, max = 255) String description) {
        this.id = id;
        this.user = user;
        this.description = description;
    }

    public Task () {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Task [id=" + id + ", user=" + user + ", description=" + description + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
