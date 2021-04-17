package com.empik.interview.exercise;

import com.empik.interview.exercise.dto.UserDataDto;
import com.empik.interview.exercise.dto.UserDataDtoApi;
import com.empik.interview.exercise.exception.UserDataProcessorException;
import com.empik.interview.exercise.service.inf.UserDataProcessor;
import com.empik.interview.exercise.service.utils.UserDataProcessorUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InterviewExerciseApplicationTests {

	@Autowired
	private UserDataProcessorUtils userDataProcessorUtils;

	@Test
	public void testCalculateMethod(){
		Assertions.assertEquals(4, userDataProcessorUtils.calculate(0,2));
		Assertions.assertEquals(12,userDataProcessorUtils.calculate(2,2));
		Assertions.assertNotEquals(0,userDataProcessorUtils.calculate(0,0));
		Assertions.assertNotEquals(90,userDataProcessorUtils.calculate(-2,-2));
	}

	@Test
	public void testMakeCalculationsAndConvertMethod() {
		UserDataDtoApi dtoApi = UserDataDtoApi.builder()
				.id(1L)
				.login("test1")
				.followers(0)
				.publicRepos(2).build();

		UserDataDto dto = UserDataDto.builder()
				.id(1L)
				.login("test1")
				.calculations(4).build();

		Assertions.assertEquals(dto,userDataProcessorUtils.makeCalculationsAndConvertDto(dtoApi));

		dto.setLogin("testerror");
		Assertions.assertNotEquals(dto,userDataProcessorUtils.makeCalculationsAndConvertDto(dtoApi));
	}

}
