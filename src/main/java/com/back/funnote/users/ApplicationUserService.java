package com.back.funnote.users;


import java.util.List;

public interface ApplicationUserService {

    List<ApplicationUser>   getAllApplicationUser();
    ApplicationUser         saveApplicationUser(RegisterUser registerUser);
    ApplicationUser         updateApplicationUser(RegisterUser user);
}
