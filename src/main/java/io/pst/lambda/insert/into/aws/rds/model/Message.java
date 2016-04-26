/**
 * Copyright 2016 Pentasoft Sistemas SL.
 */
package io.pst.lambda.insert.into.aws.rds.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Message model class.
 *
 * @author Daniel Ibanez
 *
 */
@Entity(name = "mytable")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private Long id;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "lastName", nullable = false, length = 150)
    private String lastName;
    @Column(name = "registrationDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    @Column(name = "age")
    private Integer age;
    @Column(name = "score", nullable = false)
    private Float score;
    @Column(name = "businessOwner")
    private Boolean businessOwner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Boolean isBusinessOwner() {
        return businessOwner;
    }

    public void setBusinessOwner(Boolean businessOwner) {
        this.businessOwner = businessOwner;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", registrationDate=" + registrationDate +
                ", age=" + age +
                ", score=" + score +
                ", businessOwner=" + businessOwner +
                '}';
    }
}
