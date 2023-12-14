package riccardogulin.dao;

import riccardogulin.entities.Animal;
import riccardogulin.entities.Cat;
import riccardogulin.entities.Dog;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class AnimalsDAO {
	private final EntityManager em;

	public AnimalsDAO(EntityManager em) {
		this.em = em;
	}

	public Animal findById(long id) {
		return em.find(Animal.class, id);
	}

	public Cat findCatById(long id) {
		return em.find(Cat.class, id);
	}

	public Dog findDogById(long id) {
		return em.find(Dog.class, id);
	}

	public void save(Animal a) {
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(a);
		transaction.commit();
		System.out.println("Nuovo animal salvato correttamente");
	}

	public void findByIdAndDelete(long id) {
		Animal found = em.find(Animal.class, id);

		if (found != null) {

			EntityTransaction transaction = em.getTransaction();

			transaction.begin();
			em.remove(found);
			transaction.commit();
			System.out.println("L'Animal è stato cancellato correttamente");
		} else {
			System.err.println("L'Animal con l'id " + id + " non è stato trovato");
		}
	}
}
