package builder.javastyle;

public class UserJava {
    public Long id;
    public String name;
    public String email;
    public String password;

    public static class Builder {
        private final UserJava user = new UserJava();

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.user.id = id;
            return this;
        }

        public Builder name(String name) {
            this.user.name = name;
            return this;
        }

        public Builder email(String email) {
            this.user.email = email;
            return this;
        }

        public Builder password(String password) {
            this.user.password = password;
            return this;
        }

        public UserJava build() {
            return user;
        }
    }
}