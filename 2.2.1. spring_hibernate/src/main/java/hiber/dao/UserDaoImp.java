package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserByCarModelAndSeries(String model, int series) {
        String hql = "from User where user_car.model = :model and user_car.series = :series";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getSingleResult();
    }


}
