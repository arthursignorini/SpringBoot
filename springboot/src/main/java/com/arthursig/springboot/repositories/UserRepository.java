package com.arthursig.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arthursig.springboot.models.User;

@Repository
// JPARepository tem funções de sql já
// devo passar o nosso modelo e o tipo do seu identificador
public interface UserRepository extends JpaRepository<User, Long>{ // trocar para interface
    
    

}
