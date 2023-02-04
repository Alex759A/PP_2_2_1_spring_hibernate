package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

//      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));



      User user1 = new User("Ivan", "Ivanov", "IvanIvan@mail.ru");
      User user2 = new User("Petr", "Petrov", "PetrPetr@mail.ru");
      User user3 = new User("Bob", "Bobov", "Bob@mail.ru");

      Car car1 = new Car("BMW", 12);
      Car car2 = new Car("Mazda", 12346);
      Car car3 = new Car("Lada", 12347);

// добавление объекта car в user
      // One-to-One --- ( bi-directional )
      user1.setCar(car1);
      car1.setUser(user1);

      user2.setCar(car2);
      car2.setUser(user2);

      user3.setCar(car3);
      car3.setUser(user3);

// сохранение user в таблице
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);


      User user5 = userService.getUserCarModelSeries("BMW", 12);
         System.out.println("Id = "+user5.getId());
         System.out.println("First Name = "+user5.getFirstName());
         System.out.println("Last Name = "+user5.getLastName());
         System.out.println("Email = "+user5.getEmail());
         System.out.println("model = "+user5.getCar().getModel());
         System.out.println("series = "+user5.getCar().getSeries());


//      List<User> users = userService.listUsers();
//      for (User user : users) {
//         System.out.println("Id = "+user.getId());
//         System.out.println("First Name = "+user.getFirstName());
//         System.out.println("Last Name = "+user.getLastName());
//         System.out.println("Email = "+user.getEmail());
//      }

      context.close();
   }
}
