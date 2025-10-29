package com.appsdeveloperblog.ws.emailNotificationMicroservice;

import com.appsdeveloperblog.ws.emailNotificationMicroservice.Exception.Test1;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailNotificationMicroserviceApplicationTests {

	@Test
	void contextLoads() {

		Test1 test1  = new Test1() ;

		int result = test1.IntegerDivision(4,2) ;
        assertEquals(2 , result , ()->" Expected two only");

	}

}
