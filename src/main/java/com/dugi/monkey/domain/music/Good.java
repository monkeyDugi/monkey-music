package com.dugi.monkey.domain.music;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class Good {

    @Id
    @GeneratedValue
    private Long seq;
    private String title;
    private String content;
    private Long cnt;

    @ManyToOne
    @JoinColumn(name = "SEARCH_ID")
    private Search search;

    @Builder
    public Good(String title, String content, Long cnt, Search search) {
        this.title = title;
        this.content = content;
        this.cnt = cnt;
        this.search = search;
    }
}
