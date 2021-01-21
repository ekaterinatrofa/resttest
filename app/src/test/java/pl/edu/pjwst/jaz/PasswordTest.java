package pl.edu.pjwst.jaz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PasswordTest {

    @Mock
    PasswordEncoder passwordEncoder;


    @Test
    public void checkIfPasswordIsEncrypted() {
        String password = "qwerty";
        String encryptedPassword = "$2a$10$yuiSwUBGma1C29WQCa5dBuBg2rq0r6PeoaAfSVWL8en5SkRWWLLFu";
        when(passwordEncoder.encode(password)).thenReturn(encryptedPassword);
        assertEquals(encryptedPassword, passwordEncoder.encode(password));
    }
}
