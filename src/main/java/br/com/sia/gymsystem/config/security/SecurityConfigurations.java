package br.com.sia.gymsystem.config.security;

import br.com.sia.gymsystem.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

//@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
//@Profile(value = {"prod", "test"})
public class SecurityConfigurations {

	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

//	@Bean
//	public EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean() {
//		EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean =
//				EmbeddedLdapServerContextSourceFactoryBean.fromEmbeddedLdapServer();
//		contextSourceFactoryBean.setPort(0);
//		return contextSourceFactoryBean;
//	}
//
//	@Bean
//	AuthenticationManager ldapAuthenticationManager(
//			BaseLdapPathContextSource contextSource) {
//		LdapBindAuthenticationManagerFactory factory =
//				new LdapBindAuthenticationManagerFactory(contextSource);
//		factory.setUserDnPatterns("uid={0},ou=people");
//		factory.setUserDetailsContextMapper();
//		return factory.createAuthenticationManager();
//	}

//	@Bean
//	public EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean() {
//		EmbeddedLdapServerContextSourceFactoryBean contextSourceFactoryBean =
//				EmbeddedLdapServerContextSourceFactoryBean.fromEmbeddedLdapServer();
//		contextSourceFactoryBean.setPort(0);
//		return contextSourceFactoryBean;
//	}
//
//	@Bean
//	public AuthenticationManager authenticationManager(BaseLdapPathContextSource contextSource) {
//		LdapAuthenticationManagerFactory factory = LdapAuthenticationManagerFactory
//				.usingBindAuthentication(contextSource);
//		factory.setUserDnPatterns("uid={0},ou=people");
//		factory.setUserDetailsContextMapper(new PersonContextMapper());
//		return factory.createAuthenticationManager();
//	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, AutenticacaoService autenticacaoDetailService)
			throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(autenticacaoDetailService)
				.passwordEncoder(bCryptPasswordEncoder)
				.and()
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//Configuracoes de autorizacao
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers("/**").permitAll()
				.and().csrf().disable()
//				.anyRequest().authenticated()
//				.and().csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http
//				.httpBasic()
//				.and()
//				.authorizeHttpRequests()
//				.anyRequest().authenticated()
//				.and()
//				.csrf().disable();
//		return http.build();
//	}
	
	
	//Configuracoes de recursos estaticos(js, css, imagens, etc.)
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
//	}
	
}
