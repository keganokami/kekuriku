package com.rakuriku.rakuriku.infra;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // ログイン以降の認証認可のためのFilter
    private final JWTAuthenticationFilter filter;

    @Autowired
    public SecurityConfig(JWTAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Value("${env}")
    private String env;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.logout().logoutUrl("/management-account/logout").deleteCookies("X-AUTH-TOKEN").invalidateHttpSession(true)
                .logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK));

        http.httpBasic().disable().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                // ログイン処理ようのURLには認証認可なしでアクセスできる
                .antMatchers("/").permitAll().antMatchers("/management-account/login").permitAll().antMatchers("/resources/**")
                .permitAll().antMatchers("/management-account/new").permitAll().antMatchers("/**").authenticated().and()
                // デフォルトのFilter設定を変える
                .addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
        ;
        // Spring Securityデフォルトでは、アクセス権限（ROLE）設定したページに未認証状態でアクセスすると403を返すので、
        // 401を返すように変更
        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
    }
}
