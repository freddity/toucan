package com.example.toucan.service;

import com.example.toucan.model.entity.EntityUser;
import com.example.toucan.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUser {

    private final RepositoryUser repositoryUser;

    @Autowired
    public ServiceUser(RepositoryUser repositoryUser){
        this.repositoryUser = repositoryUser;
    }

    //1 = successfully
    //0 = unsuccessfully
    public int createUser(String email ,String username, String password){
        if (repositoryUser.save(new EntityUser(
                email, username, password)) != null){
            return 1;
        }
        return 0;
    }

    public List getAllNotes(String username){
        return repositoryUser
                .findEntityUserByUsername(username).getNoteList();
    }


    //0 = not blocked
    //1 = blocked
    public int setBlockStatus(String username, int status) {
        return repositoryUser
                .findEntityUserByUsername(username).setBlockedStatus(status);
    }
}
