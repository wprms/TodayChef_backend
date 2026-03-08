package com.youandjang.todaychef;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.youandjang.todaychef.TodaychefApplication;

@SpringBootTest(
	classes = TodaychefApplication.class,
	properties = { "spring.sql.init.mode=never" }
)
class TodaychefApplicationTests {

	@Test
	void contextLoads() {
	}

}
