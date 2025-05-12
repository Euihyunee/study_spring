package spring3_4_5.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class ProjectJava17Application {

	public static void main(String[] args) {
		SpringApplication.run(ProjectJava17Application.class, args);
	}

}
