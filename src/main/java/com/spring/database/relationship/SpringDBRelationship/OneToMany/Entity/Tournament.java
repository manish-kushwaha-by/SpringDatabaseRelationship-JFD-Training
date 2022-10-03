package com.spring.database.relationship.SpringDBRelationship.OneToMany.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    private String location;

    public Tournament() {
    }

    public List<Registeration> getRegisterations() {
        return registerations;
    }

    public void setRegisterations(List<Registeration> registerations) {
        this.registerations = registerations;
    }

    public Tournament(int id, String name, String location, List<Registeration> registerations) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.registerations = registerations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @OneToMany(cascade = CascadeType.ALL) // If somechange happen then it will also call FK Registration class for updates.
    @JoinColumn(name="tournament_id", referencedColumnName = "id")
    private List<Registeration> registerations = new ArrayList<>();


    // setup One to many relationship
    public void addRegistration(Registeration registeration){
        registerations.add(registeration);
    }

    public void deleteRegistration(Registeration registeration){
        if(!registerations.isEmpty()){
            registerations.remove(registeration);
        }

    }



    /*
    Hibernate: create table player (id integer not null, name varchar(255), profile_id integer, primary key (id))
    Hibernate: create table player_profile (id integer generated by default as identity, insta_profile varchar(255), primary key (id))

    --> Hibernate: create table registeration (id integer not null, tournament_id integer, primary key (id))

    Hibernate: create table tournament (id integer not null, location varchar(255), name varchar(255), primary key (id))
     */

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", registerations=" + registerations.toString() +
                '}';
    }
}