package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Pavel", "Volya", "user1@mail.ru", new Car("bmv", 5)));
        userService.add(new User("Fillip", "Kirkorov", "user2@mail.ru", new Car("Lada", 2114)));
        userService.add(new User("Masha", "Rasputina", "user3@mail.ru", new Car("doodge", 15)));
        userService.add(new User("Olga", "Busova", "user4@mail.ru", new Car("fiat", 211)));


        List<User> users = userService.listUsers();
        for (User user : users) {

            System.out.println("Id = " + user.getId() + "\n" +
                    "First Name = " + user.getFirstName() + "\n" +
                    "Last Name = " + user.getLastName() + "\n" +
                    "Email = " + user.getEmail() + "\n" +
                    user.getUser_car() + "\n");
        }

        System.out.println(userService.getUserByCarModelAndSeries("Lada", 2114));
        context.close();
    }
}
