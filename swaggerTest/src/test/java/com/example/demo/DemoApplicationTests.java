package com.example.demo;

import com.example.demo.Model.DAO.MemoDAO;
import com.example.demo.Repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MemoRepository memoRepository;

	@Test
	void contextLoads() {
		IntStream.rangeClosed(1, 10).forEach(i -> {
			memoRepository.save(new MemoDAO("content"));
		});
	}

}
