package pl.gawor.tayckner.taycknerbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import pl.gawor.tayckner.taycknerbackend.web.security.AuthFilter;

@SpringBootApplication
public class TaycknerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaycknerBackendApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/api/activities/*");
		registrationBean.addUrlPatterns("/api/categories/*");
		registrationBean.addUrlPatterns("/api/schedules/*");
		registrationBean.addUrlPatterns("/api/habits/*");
		registrationBean.addUrlPatterns("/api/habit-events/*");
		return registrationBean;
	}

}
