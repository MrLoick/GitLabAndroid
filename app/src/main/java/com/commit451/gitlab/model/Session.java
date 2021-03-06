package com.commit451.gitlab.model;

import org.parceler.Parcel;

import java.util.Date;
@Parcel
public class Session {

    long id;
	String username;
	String email;
	String name;
	String private_token;
	boolean blocked;
    Date created_at;
	String bio;
	String skype;
	String linkedin;
	String twitter;
	boolean dark_scheme;
	long theme_id;
	boolean is_admin;
    boolean can_create_group;
	boolean can_create_team;
	boolean can_create_project;

    public Session(){}

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPrivateToken() {
        return private_token;
    }
    public void setPrivateToken(String private_token) {
        this.private_token = private_token;
    }

    public boolean isBlocked() {
        return blocked;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Date getCreatedAt() {
        return created_at;
    }
    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSkype() {
        return skype;
    }
    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getLinkedin() {
        return linkedin;
    }
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getTwitter() {
        return twitter;
    }
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public boolean isDarkScheme() {
        return dark_scheme;
    }
    public void setDarkScheme(boolean dark_scheme) {
        this.dark_scheme = dark_scheme;
    }

    public long getThemeId() {
        return theme_id;
    }
    public void setThemeId(long theme_id) {
        this.theme_id = theme_id;
    }

    public boolean isIsAdmin() {
        return is_admin;
    }
    public void setIsAdmin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public boolean isCanCreateGroup() {
        return can_create_group;
    }
    public void setCanCreateGroup(boolean can_create_group) {
        this.can_create_group = can_create_group;
    }

    public boolean isCanCreateTeam() {
        return can_create_team;
    }
    public void setCanCreateTeam(boolean can_create_team) {
        this.can_create_team = can_create_team;
    }

    public boolean isCanCreateProject() {
        return can_create_project;
    }
    public void setCanCreateProject(boolean can_create_project) {
        this.can_create_project = can_create_project;
    }
}
