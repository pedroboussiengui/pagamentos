package org.pag.pagamentos;

import org.pag.pagamentos.model.Permisson;
import org.pag.pagamentos.model.Role;
import org.pag.pagamentos.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PagamentosApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PagamentosApplication.class, args);

//        User user = new User();
//        user.setId(1L);
//        user.setName("Pedro");
//        user.setEmail("pedro@email.com");
//        user.addAllAuthority(Role.GESTOR_RH);
//
//        System.out.println(user);
//
//        User user2 = new User();
//        user2.setId(2L);
//        user2.setName("Ana");
//        user2.setEmail("ana@email.com");
//        user2.addRole(Role.GESTOR_RH);
//
//        System.out.println(user2);
//
//        user2.addPermission(Permisson.PROVIMENTO);
//
////        System.out.println(user2);
////
////        user2.addPermission(Permisson.PESSOAFISICA);
//
//        user.removeRole(Role.GESTOR_RH);
//
//        System.out.println(user);
//
//        user.addAllAuthority(Role.GESTOR_RH);
//        user.removePermisson(Permisson.PROVIMENTO);
//
//        System.out.println(user);
//
//        User user3 = new User();
//        user3.setId(3L);
//        user3.setName("Maria");
//        user3.setEmail("maria@email.com");
//        user3.addRole(Role.ADMIN);
//
//        System.out.println(user3);

    }
}
