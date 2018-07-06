package com.engcorner.epose;

import com.engcorner.epose.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	private UserRepository userRepository;

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new LoginAuthSuccess();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/app-assets/**").permitAll()
                .antMatchers("/register").permitAll()
				.anyRequest().authenticated()
                .and()

            .formLogin()
                .loginPage("/login").permitAll()
            .successHandler(authenticationSuccessHandler()) // 登录成功后存储用户信息
                .and()
            .logout()
                .logoutSuccessUrl("/index") //退出登录后的默认网址是”/home”
                .permitAll();
        http
        .headers()
           .frameOptions()
              .sameOrigin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.userDetailsService(username -> userRepository.findByUsername(username)).passwordEncoder(bcryptEncoder);
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/*.css");
		web.ignoring().antMatchers("/*.js");
	}
}