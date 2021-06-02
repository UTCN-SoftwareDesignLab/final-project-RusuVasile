package com.lab4.demo;

public class UrlMapping {
    public static final String API_PATH = "/api";
    public static final String TEAM= API_PATH + "/team";
    public static final String RESULT= API_PATH + "/result";
    public static final String CONTEST= API_PATH + "/contest";
    public static final String ENTITY = "/{id}";
    public static final String FILE= TEAM+"/file";

    public static final String AUTH = API_PATH + "/auth";
    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";

    public static final String USERS = API_PATH + "/users";

}
