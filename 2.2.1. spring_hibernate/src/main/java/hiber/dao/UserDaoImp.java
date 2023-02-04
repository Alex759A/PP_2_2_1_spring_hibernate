package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;// подтянулись все настройки для создания сессии

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);// сохранение юзера в табл
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);// сохранение авто в табл
   }

   @Override
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User getUserCarModelSeries(String model, int series) {
      // запрос с параметрами
      String hQL = "from User u where u.carUser.model=:pModel and u.carUser.series=:pSeries";
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hQL);
      query.setParameter("pModel", model);
      query.setParameter("pSeries", series);
      Object result = query.getSingleResult();

      return (User) result;
   }
}

   /*
   https://javarush.com/quests/lectures/questhibernate.level10.lecture03

*/


