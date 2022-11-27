package com.fzt.ktzq.util;

import com.fzt.ktzq.dao.User;

public class AuthUserContext {
    private static final ThreadLocal<User> userInfo = new ThreadLocal<>();
//    private static final ThreadLocal<OwnerAppUser> appUser = new ThreadLocal<>();

    private AuthUserContext(){

    }

    public static User getUser(){
        return userInfo.get();
    }

//    public static OwnerAppUser getOwnerAppUser(){
//        return appUser.get();
//    }

    public static void setUser(User user){
        userInfo.set(user);
    }

//    public static void setOwnerAppUser(OwnerAppUser ownerAppUser){
//        appUser.set(ownerAppUser);
//    }

    public static void remove(){
        userInfo.remove();
//        appUser.remove();
    }
}
