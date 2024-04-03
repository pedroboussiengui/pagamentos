package org.pag.pagamentos.config;

import org.pag.pagamentos.mock.GroupSource;
import org.pag.pagamentos.mock.UserSource;
import org.pag.pagamentos.model.Group;
import org.pag.pagamentos.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.util.HashSet;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final AccessDeniedHandler customAccessDeniedHandler;
    private final UserSource userSource;
    private final GroupSource groupSource;

    public SecurityConfig(AccessDeniedHandler customAccessDeniedHandler, UserSource userSource, GroupSource groupSource) {
        this.customAccessDeniedHandler = customAccessDeniedHandler;
        this.userSource = userSource;
        this.groupSource = groupSource;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/public/**")
                    .permitAll()
                .requestMatchers("/admin/**").hasAnyAuthority(
                    Role.ADMIN.name()
                )
                .requestMatchers("/eventos/**").hasAnyAuthority(
                    Role.ADMIN.name(),
                    Role.GESTOR_RH.name()
                )
                .requestMatchers("/pessoas/**").hasAnyAuthority(
                    Role.ADMIN.name(),
                    Role.CADASTRANTE.name()
                )
                .requestMatchers("/pccr/**").hasAnyAuthority(
                    Role.ADMIN.name(),
                    Role.ANALISTA_RH.name()
                )
                .requestMatchers("/rpps/**").hasAnyAuthority(
                    Role.ADMIN.name(),
                    Role.GESTOR_RH.name()
                )
            )
            .httpBasic(Customizer.withDefaults())
            .exceptionHandling((exception) -> exception
                    .accessDeniedHandler(customAccessDeniedHandler)
            );
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) throws Exception {

        org.pag.pagamentos.model.User ana = userSource.getByName("Ana");
        Group group = groupSource.getGroupById(1L);
        ana.setGroup(group);
        System.out.println(ana);

        List<String> listAutorities = new java.util.ArrayList<>(ana.getAuthorities().stream().toList());
        List<String> listGroupAuthorities = ana.getGroup().getAuthorities().stream().toList();

        listAutorities.addAll(listGroupAuthorities);

        System.out.println(new HashSet<>(listAutorities));

        UserDetails user = User.withUsername(ana.getName())
                .password(passwordEncoder.encode("1234"))
                .authorities(listAutorities.toArray(String[]::new))
                .build();
        System.out.println(user);

        // ---------------------------------------------------------------------------------------------

        org.pag.pagamentos.model.User lucas = userSource.getByName("Lucas");

        List<String> listAutorities2 = lucas.getAuthorities().stream().toList();

        UserDetails user2 = User.withUsername(lucas.getName())
                .password(passwordEncoder.encode("1234"))
                .roles(listAutorities2.toArray(String[]::new))
                .build();
        System.out.println(user2);

        return new InMemoryUserDetailsManager(user, user2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
