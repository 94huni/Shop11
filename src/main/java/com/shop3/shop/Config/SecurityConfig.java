package com.shop3.shop.Config;

import com.shop3.shop.Entity.User;
import com.shop3.shop.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 지금은 확인단계기 때문에 csrf 토큰 비활성화
        http.csrf().disable();
        http.httpBasic();
        http.authorizeRequests()
                //user, cart 부분은 user 권한 사용가능
                .antMatchers("/api/user","/api/cart").hasAnyRole("ROLE_USER")
                // admin 부분은 ROLE_ADMIN 권한사용가능
                .antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN")
                // product 부분과 나머지 요청에 대한것은 모두 허용
                .antMatchers("/product/**","/signup").permitAll()
                .anyRequest().permitAll();
        http.formLogin()
                .loginProcessingUrl("/api/login")
                .usernameParameter("userid")
                .passwordParameter("password")
                .successHandler(new SuccessHandler());
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByUserid(username);
                if(user ==  null){
                    throw new UsernameNotFoundException("유저이름이 " + username + " 인 사용자를 찾을수 없습니다.");
                }
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRoles()));
                return new org.springframework.security.core.userdetails.User(user.getUserid(), user.getPassword(), authorities);
            }
        };
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}