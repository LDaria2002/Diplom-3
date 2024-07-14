package users;

import com.github.javafaker.Faker;
import io.restassured.response.ValidatableResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String email;
    private String password;
    private String name;

    public static User getRandomUser() {
        Faker faker = new Faker();
        return User.builder()
                .email(faker.internet().emailAddress())
                .password(faker.internet().password(6, 10))
                .name(faker.name().firstName())
                .build();
    }
    public static String getToken(ValidatableResponse response) {
        return response
                .extract()
                .path("accessToken");
    }
}

