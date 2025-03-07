package com.example.demo.core.security.permissionevaluators;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserService;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Log4j2
@Component
@NoArgsConstructor
public class UserPermissionEvaluator {
    @Autowired
    UserService userService;

    public boolean exampleEvaluator(User principal, UUID id) {
        //your code here
        return true;
    }

    public boolean evaluateBlogUpdateRights(User userID, UUID id) {
        log.info("Checking blog update rights for user: {} on blog post author ID: {}", userID.getId(), id);
        var user = userService.findById(id);

        boolean hasPermission = userID.getId().equals(user.getId());
        log.info("Permission evaluation result: {}", hasPermission);

        return hasPermission;
    }
}
