package be.vdab.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CreateSecurityFilter extends WebSecurityConfigurerAdapter {
	private static final String ADMIN = "admin";
	private static final String USER = "user";
	@Autowired
	private DataSource dataSource;
	private static final String USERS_BY_USERNAME = "select naam as username, paswoord as password, actief as enabled"
			+ " from gebruikers where naam = ?";
	private static final String AUTHORITIES_BY_USERNAME = "select gebruikers.naam as username, rollen.naam as authorities"
			+ " from gebruikers inner join gebruikersrollen"
			+ " on gebruikers.id = gebruikersrollen.gebruikerid"
			+ " inner join rollen"
			+ " on rollen.id = gebruikersrollen.rolid"
			+ " where gebruikers.naam= ?";

	@Override
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(USERS_BY_USERNAME)
				.authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME)
				.passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**").antMatchers("/styles/**")
				.antMatchers("/scripts/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
				.loginPage("/login")
				.and()
				.logout()
				.logoutSuccessUrl("/")
				.and()
				.authorizeRequests()
				.antMatchers("/artikels/toevoegen", "/artikels/*/wijzigen",
						"/artikels/*/verwijderen", "/artikels/regio",
						"/file/uploadFile", "/file/upload",
						"/file/uploadFile", "/file/show/*", "/file/upload/*", "/artikels/contacteer/*")
				.hasAnyAuthority(USER, ADMIN)
				.antMatchers(HttpMethod.POST, "/artikels")
				.hasAnyAuthority(USER, ADMIN)
				.antMatchers(HttpMethod.PUT, "/artikels/*")
				.hasAuthority(ADMIN)
				.antMatchers(HttpMethod.DELETE, "/artikels/*")
				.hasAuthority(ADMIN)
				.antMatchers("/", "/login", "/user", "/user/toevoegen",
						"/user/forgotPassword", "/user/forgotPasswordSucces",
						"/user/forgotPassword/reset").permitAll()
				.antMatchers("/**").authenticated().and().exceptionHandling()
				.accessDeniedPage("/WEB-INF/JSP/forbidden.jsp");
		http.httpBasic();
	}
}
