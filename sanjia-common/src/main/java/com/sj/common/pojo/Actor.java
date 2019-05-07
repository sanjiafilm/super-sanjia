package com.sj.common.pojo;

import javax.persistence.Table;

/**
 * 描述:
 *
 * @version 1.0
 * @Date: 2019/5/7 9:12
 * @author: ls
 */
@Table(name = "t_actors")
public class Actor {
    private Integer actorId;
    private String actorName;
    private String actorRole;
    private String actorMovie;
    private String actorLocation;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActorRole() {
        return actorRole;
    }

    public void setActorRole(String actorRole) {
        this.actorRole = actorRole;
    }

    public String getActorMovie() {
        return actorMovie;
    }

    public void setActorMovie(String actorMovie) {
        this.actorMovie = actorMovie;
    }

    public String getActorLocation() {
        return actorLocation;
    }

    public void setActorLocation(String actorLocation) {
        this.actorLocation = actorLocation;
    }
}
