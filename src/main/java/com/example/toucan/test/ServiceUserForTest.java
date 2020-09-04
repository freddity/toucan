package com.example.toucan.test;

import com.example.toucan.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUserForTest {

    private final RepositoryUser repositoryUser;

    @Autowired
    public ServiceUserForTest(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    public List getAllEntityUsers() {
        return (List) repositoryUser.findAll();
    }
}
