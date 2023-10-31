package com.magicpost.controller.Account;
import com.magicpost.model.Account;
import com.magicpost.model.dto.AccountDTO;
import com.magicpost.service.IAccount;
import com.magicpost.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    IAccount accountService;


    @PostMapping
    public ResponseEntity<?> getLogin(@RequestBody Account account) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            account = accountService.getAccountLogin(account.getUsername(), account.getPassword());
            String token = jwtService.createToken(authentication);
            if (account.getStatus().getId() == 2) {
                String errorMessage = "Unauthorized access.";
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
            }
            AccountDTO accountToken = new AccountDTO(account.getId(), account.getUsername(), account.getStatus(), account.getRole(), token);
            return ResponseEntity.ok(accountToken);
        } catch (AuthenticationException e) {
            String errorMessage = "Invalid username or password.";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
    }
}
