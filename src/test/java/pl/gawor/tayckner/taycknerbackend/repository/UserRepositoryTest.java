package pl.gawor.tayckner.taycknerbackend.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import pl.gawor.tayckner.taycknerbackend.repository.entity.UserEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UserRepositoryTest {
    public static final long ID = 2L;
    @Autowired private UserRepository repository;

    @Test
    public void _givenKnownUsernameFromDB_whenFindUserByUsername_thenFoundUserNotNull() {
        // given
        String username = "test_user";

        // when
        UserEntity foundUser = repository.findUserEntityByUsername(username);

        // then
        assert foundUser != null;
    }

    @Test
    public void _givenUserWithId_whenSave_thenReadUserEqualsSavedUser() {
        // given
        UserEntity userWithId = new UserEntity(ID, "test_user2", "secret", "none", "none", "none@example.com");
        // when
        repository.save(userWithId);
        UserEntity readUser = repository.getById(ID);
        // then
       assert userWithId == readUser;
    }
}