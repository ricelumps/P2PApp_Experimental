package com.P2PApp.Config;

import javax.servlet.DispatcherType;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
        	.authorizeHttpRequests(request -> request
						/* .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() */
        	.antMatchers("/login","/register","/images/**","/css/**","/js/**", "/error").permitAll()
        	.anyRequest().authenticated()
        )
        .formLogin(login -> login	// form 방식 로그인 사용
                .loginPage("/")	// 로그인 URL 
                .loginProcessingUrl("/loginOK") // 로그인 비즈니스 로직 URL
                .usernameParameter("userID") //  username 설정 
                .passwordParameter("password") // password 설정
        		.defaultSuccessUrl("/main", true)	// 성공 시 main로
        		.failureUrl("/") // 로그인 실패시 URL
                .permitAll()	// 메인 이동이 막히면 안되므로 얘는 허용
        		).logout(withDefaults());
        
        return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
