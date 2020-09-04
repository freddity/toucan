package com.example.toucan.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("toucan/test")
public class ControllerForTests {

    private final ServiceUserForTest serviceUserForTest;

    @Autowired
    public ControllerForTests(ServiceUserForTest serviceUserForTest) {
        this.serviceUserForTest = serviceUserForTest;
    }

    @GetMapping("/getallusers")
    public List getAllUsers() {
        return serviceUserForTest.getAllEntityUsers();
    }
}
