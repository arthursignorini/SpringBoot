package com.arthursig.springboot.models;

import java.util.ArrayList;
import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity // define como uma entidade do banco
@Table(name = "\"user\"") // define o nome da tabela
public class User {

    public interface CreateUser {
    }

    public interface Updateuser {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // para gerar com os ids certos
    @Column(name = "id", unique = true)
    private Long id; // os ids podem crescer muito

    // nao vou atualizar o nome
    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class) // quando vai crirar o usuario, o username nao pode ser nulo
    @NotEmpty(groups = CreateUser.class) // quando vai criar o usuário
    @Size(groups = CreateUser.class, min = 2, max = 100) // quando criar o usuario, tem que respeitar esse tamanho
    private String username;

    // vou poder atualizar a senha
    @JsonProperty(access = Access.WRITE_ONLY) // garante que a senha vai ser apenas lida e não retornada 
    @Column(name = "password", length = 60, nullable = false)
    @NotBlank(groups = { CreateUser.class, Updateuser.class }) // verifica se é null ou vazio ao mesmo tempo
    @Size(groups = { CreateUser.class, Updateuser.class }, min = 8, max = 60)
    private String password;

    @OneToMany (mappedBy = "user")// um usuário pode ter várias tasks
    // quem está mapeando de quem são essas tasks - variável user
    private ArrayList<Task> tasks = new ArrayList<>();


    // MÉTODOS
    public User() {

    }

    public User(Long id,
            @NotNull(groups = CreateUser.class) @NotEmpty(groups = CreateUser.class) @Size(groups = CreateUser.class, min = 2, max = 100) String username,
            @NotBlank(groups = { CreateUser.class, Updateuser.class }) @Size(groups = { CreateUser.class,
                    Updateuser.class }, min = 8, max = 60) String password,
            ArrayList<Task> tasks) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", tasks=" + tasks + "]";
    }

}
