package com.dugi.monkey.domain.music;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Search {

    @Id
    @Column(name="SEARCH_ID")
    private String id;
    private String password;
    private String name;

    @Builder
    public Search(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
}
