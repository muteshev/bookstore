package dev.zenfluxpro.bookstore.notifications;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import dev.zenfluxpro.bookstore.notifications.domain.NotificationService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(dev.zenfluxpro.bookstore.notifications.ContainersConfig.class)
public abstract class AbstractIT {
    @MockBean
    protected NotificationService notificationService;
}
