package com.dugi.monkey.domain.music.manyToOneExample;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToOneTest {

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private SearchRepository searchRepository;

    @Test
    public void testManyToOneInert() {
        Search search1 = Search.builder()
                .id("search1")
                .password("search111")
                .name("둘리")
                .build();

        searchRepository.save(search1);

        Search search2 = Search.builder()
                .id("search2")
                .password("search222")
                .name("공실이")
                .build();

        searchRepository.save(search2);

        for(int i = 1; i <= 3; i++) {
            Good good = Good.builder()
                    .title("둘리가 등록한 글 : " + i)
                    .content("둘리가 등록한 글 내용 : " + i)
                    .cnt(0L)
                    .search(search1)
                    .build();

            goodRepository.save(good);
        }

        for(int i = 1; i <= 3; i++) {
            Good good = Good.builder()
                    .title("공실이가 등록한 글 : " + i)
                    .content("공실이가 등록한 글 내용 : " + i)
                    .cnt(0L)
                    .search(search2)
                    .build();

            goodRepository.save(good);
        }

        System.out.println("====================================================");
        Good good = goodRepository.findById(5L).get();
        System.out.println("[ " + good.getSeq() + "번 게시글 정보]");
        System.out.println("제목 : " + good.getTitle());
        System.out.println("내용 : " + good.getContent());
        System.out.println("작성자 : " + good.getSearch().getName());

        System.out.println("====================================================");
        Long c = goodRepository.findExists("공실이가 등록한 글 1");
        System.out.println("dddddddddddddd : " + c);

        System.out.println("====================================================");
        Search search = searchRepository.findById("search2").get();
        System.out.println(" : " + search.getName());
        System.out.println(" : " + search.getPassword());

        System.out.println("====================================================");
//        goodRepository.deleteAll();
    }
}
