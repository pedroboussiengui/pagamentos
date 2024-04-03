package org.pag.pagamentos.controllers.eventos;

import com.fasterxml.jackson.core.JsonToken;
import org.pag.pagamentos.mock.UserSource;
import org.pag.pagamentos.model.Setor;
import org.pag.pagamentos.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/eventos/provimento")
@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('permission:provimento')")
public class ProvimentoController {
    private final UserSource userSource;

    public ProvimentoController(UserSource userSource) {
        this.userSource = userSource;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> realizarProvimento(Authentication authentication, @PathVariable Long id) {
        System.out.println(authentication.getAuthorities());
        try {
            String username = authentication.getName();
            User principalUser = userSource.getByName(username);
            User otherUser = userSource.getById(id);
            if (principalUser.equals(otherUser)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body("Not allowed to same user.");
            }
            if (principalUser.getSetor().equals(Setor.GLOBAL)) {
                return ResponseEntity.ok().body("Realizando provimento para " + otherUser.getName() + ", pois o principal possui escopo global.");
            }
            if (principalUser.getSetor().equals(otherUser.getSetor())) {
                return ResponseEntity.ok().body("Realizando provimento para " + otherUser.getName() + ", pois possuem o mesmo tipo de setor.");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body("Not allowed.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ex.getMessage());
        }
    }
}
