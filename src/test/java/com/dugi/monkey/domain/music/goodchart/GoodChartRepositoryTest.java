package com.dugi.monkey.domain.music.goodchart;

import com.dugi.monkey.web.goodchart.dto.RequestGoodChartDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodChartRepositoryTest {

    private RequestGoodChartDto requestGoodChartDto;

    private String title = "maroon5";
    private String email = "test@test.com";
    private String videoId = "videoId";
    private String image = "image";

    @Autowired
    private GoodChartRepository goodChartRepository;

    @Before
    public void setUp() {
        goodChartRepository.deleteAll();

        // given
        requestGoodChartDto =
                RequestGoodChartDto.builder()
                                    .title(title)
                                    .videoId(videoId)
                                    .image(image)
                                    .email(email)
                                    .build();

        // save
        goodChartRepository.save(requestGoodChartDto.toEntity());
    }

    @Test
    public void 마이리스트에서_음악_제거() {
        // when
//        goodChartRepository.deleteByVideoIdAndEmail(videoId, email);

        // then
//        boolean t = goodChartRepository.existsByVideoIdAndEmail(videoId, email);
//
//        assertThat(t).isEqualTo(false);
    }

    @Test
    public void 마이리스트에_존재하는_videoId() {
        boolean result = goodChartRepository.existsByVideoIdAndEmail(videoId, email);

        assertThat(result).isEqualTo(true);
    }

    @Test
    public void 마이리스트에_존재하지_않는_videoId() {
        // given
        // @Before에서 만든 given과 다른 videoId를 넣기 위해 재생성
        requestGoodChartDto =
                RequestGoodChartDto.builder()
                        .title("title")
                        .videoId("notVideoId")
                        .image("image")
                        .email("test@test.com")
                        .build();

        boolean result = goodChartRepository.existsByVideoIdAndEmail("notVideoId", email);

        assertThat(result).isEqualTo(false);
    }
}