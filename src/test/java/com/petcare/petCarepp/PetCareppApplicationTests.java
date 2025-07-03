package com.petcare.petCarepp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
//@SpringBootTest //간단하게 테스트 할 거면 주석처리 할 것.
@Slf4j
class PetCareppApplicationTests {


	void contextLoads() {
		log.info("Hello World");
	}

	@Test
	void 회원가입(){

	}

	@Test
	public void test() {
		int a= 10;
		int b= 20;
		assertEquals(30, a+b);
	}


}
