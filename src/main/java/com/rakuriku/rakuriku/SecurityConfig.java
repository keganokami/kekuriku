package com.rakuriku.rakuriku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
 
    @Value("${env}")
    private String env;
 
    @Autowired
    private DataSource dataSource;
 
    //ユーザIDとパスワードを取得するSQL文
    // 使用可否は全てTRUEで設定
    private static final String USER_SQL = "SELECT "
            + "userId AS username, "
            + "password AS password, "
            + "true "
            + "FROM admins "
            + "WHERE userId = ?";
 
    private static final String ROLE_SQL = "SELECT "
            + "userId AS username, "
            + "role "
            + "FROM admins "
            + "WHERE userId = ?";

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(USER_SQL)
                .authoritiesByUsernameQuery(ROLE_SQL)
                .passwordEncoder(bCryptPasswordEncoder());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // System.out.println("環境：" + env);
		// if(env.equals("heroku")) {
		// 	CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 	corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
		// 	corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
		// 	corsConfiguration.addAllowedOrigin("[ドメイン]");
		// 	UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
		// 	corsSource.registerCorsConfiguration("/**", corsConfiguration); // すべてのパスを対象にする
		// 	http.cors().configurationSource(corsSource);
		// }
        
        http.logout().logoutUrl("/api/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessHandler((req, res, auth) -> res.setStatus(HttpServletResponse.SC_OK))
        ;
 
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/api/**").authenticated()
        ;
 
        // Spring Securityデフォルトでは、アクセス権限（ROLE）設定したページに未認証状態でアクセスすると403を返すので、
        // 401を返すように変更
        http.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
 
        http.addFilter(new SpaAuthenticationFilter(authenticationManager(), bCryptPasswordEncoder()));
        http.csrf().ignoringAntMatchers("/api/login").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
