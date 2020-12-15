package com.huzz.model;

import java.util.List;

//创作音乐的个人或团队
public class Artist {
    //艺术家名字
    private String name;

    //成员，可以为空
    private List<Artist> members;

    //乐队来源哪里
    private String origin;

    private String nationality;

    public Artist() {
    }

    public Artist(String name, String origin, String nationality) {
        this.name = name;
        this.origin = origin;
        this.nationality = nationality;
    }

    public Artist(String name, List<Artist> members, String origin, String nationality) {
        this.name = name;
        this.members = members;
        this.origin = origin;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artist> getMembers() {
        return members;
    }

    public void setMembers(List<Artist> members) {
        this.members = members;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", members=" + members +
                ", origin='" + origin + '\'' +
                '}';
    }
}
