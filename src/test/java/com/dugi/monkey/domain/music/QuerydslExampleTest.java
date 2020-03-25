package com.dugi.monkey.domain.music;

import com.dugi.monkey.domain.music.AcademyRepository;
import com.dugi.monkey.domain.music.AcademyRepositorySupport;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuerydslExampleTest {

    @Autowired
    private AcademyRepository academyRepository;

    @After
    public void tearDown() throws Exception {
        academyRepository.deleteAllInBatch();
    }

    @Test
    public void querydsl_기본기능확인() {
        // given
        String name = "dugi";
        String address = "bagnwha";
        academyRepository.save(new Academy(name, address));

        // when
        List<Academy> result = academyRepository.findNyName(name);

        // then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getAddress()).isEqualTo(address);
    }
}
