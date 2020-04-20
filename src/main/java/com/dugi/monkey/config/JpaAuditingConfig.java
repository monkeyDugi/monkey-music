/**
 * @WebMvcTest는
 * - @EnableJpaAuditing를 스캔할 수 없다.
 * - 그래서 @SpringBootTest만 스캔할 수 있도록 @Configuration(@Component를 달아도 상관 없음)을 달아 Application.java에 사용하지 않고, 클래스로 따로 빼서 사용한다.
 */

package com.dugi.monkey.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JPA Auditing 활성화
public class JpaAuditingConfig {
}