package com.dugi.monkey.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProfileControllerUnitTest {

//    @Test
//    public void real_profile이_조회된다() {
//        // given
//        String expectedProfile = "real1";
//        MockEnvironment mockEnvironment = new MockEnvironment();
//
//        mockEnvironment.addActiveProfile(expectedProfile);
//        mockEnvironment.addActiveProfile("oauth");
//        mockEnvironment.addActiveProfile("real-db");
//
//        ProfileController profileController = new ProfileController(mockEnvironment);
//
//        // when
//        String profile = profileController.profile();
//
//        // then
//        assertThat(profile).isEqualTo(expectedProfile);
//    }
//
//    @Test
//    public void active_profile이_없으면_default가_조회된다() {
//        String expectedProfile = "default";
//        MockEnvironment mockEnvironment = new MockEnvironment();
//        ProfileController profileController = new ProfileController(mockEnvironment);
//
//        // when
//        String profile = profileController.profile();
//
//        // then
//        assertThat(profile).isEqualTo(expectedProfile);
//    }
}