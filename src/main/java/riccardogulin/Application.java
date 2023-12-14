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

		/*ad.save(felix);
		ad.save(fido);*/

		// *************************************** NAMED QUERIES *********************
		ad.findByNameStartsWith("f").forEach(System.out::println);

		// *************************************** DYNAMIC QUERIES *********************
		System.out.println("---------------------- FIND ALL DOGS --------------------");
		ad.findAllDogs().forEach(System.out::println);

		ad.findAllAnimalsNames().forEach(System.out::println);

		System.out.println("---------------------- UPDATE ANIMALS --------------------");
		ad.findAnimalsByNameAndUpdateName("Fido", "Rex");

		System.out.println("---------------------- DELETE ANIMALS --------------------");
		ad.findAnimalsByNameAndDelete("Rex");


	}
}
