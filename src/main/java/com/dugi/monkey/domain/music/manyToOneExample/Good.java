package com.dugi.monkey.domain.music.manyToOneExample;

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
    // join할 search 테이블의 id가 아니라 Good 테이블의 join전용 컬럼명을 설정
    // search 테이블에서 자동으로 @id와 조인이 됨.
    @JoinColumn(name = "search_id")
    private Search search;

    @Builder
    public Good(String title, String content, Long cnt, Search search) {
        this.title = title;
        this.content = content;
        this.cnt = cnt;
        this.search = search;
    }
}
