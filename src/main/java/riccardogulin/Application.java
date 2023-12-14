package riccardogulin;

import riccardogulin.dao.AnimalsDAO;
import riccardogulin.entities.Cat;
import riccardogulin.entities.Dog;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d14");

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();

		AnimalsDAO ad = new AnimalsDAO(em);

		Cat felix = new Cat("Felix", 10, 50.5);
		Dog fido = new Dog("Fido", 15, 200);

		ad.save(felix);
		ad.save(fido);
		
		System.out.println(ad.findCatById(18));
		System.out.println(ad.findDogById(19));

	}
}
