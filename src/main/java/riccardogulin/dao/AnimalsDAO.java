package riccardogulin.dao;

import riccardogulin.entities.Animal;
import riccardogulin.entities.Cat;
import riccardogulin.entities.Dog;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

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

	public List<Animal> findByName(String name) {
		// Alternativamente alla TypedQuery potrei utilizzare Query ma così non avrei certezza sui tipi
		TypedQuery<Animal> getAnimalsByName = em.createNamedQuery("findByName", Animal.class);
		getAnimalsByName.setParameter("name", name);
		return getAnimalsByName.getResultList();
	}

	public List<Animal> findByNameStartsWith(String partialName) {
		// Alternativamente alla TypedQuery potrei utilizzare Query ma così non avrei certezza sui tipi
		TypedQuery<Animal> getAnimalsByName = em.createNamedQuery("findByNameStartsWith", Animal.class);
		getAnimalsByName.setParameter("partialname", partialName);
		return getAnimalsByName.getResultList();
	}

	public List<Animal> findAllAnimals() {
		TypedQuery<Animal> getAllQuery = em.createQuery("SELECT a FROM Animal a", Animal.class); // SELECT * FROM animals
		return getAllQuery.getResultList();
	}

	public List<Dog> findAllDogs() {
		TypedQuery<Dog> getAllQuery = em.createQuery("SELECT d FROM Dog d", Dog.class); // SELECT * FROM animals WHERE tipo_animale = 'Cane'
		return getAllQuery.getResultList();
	}

	public List<Cat> findAllCats() {
		TypedQuery<Cat> getAllQuery = em.createQuery("SELECT c FROM Cat c", Cat.class); // SELECT * FROM animals WHERE tipo_animale = 'Gatto'
		return getAllQuery.getResultList();
	}

	public List<String> findAllAnimalsNames() {
		TypedQuery<String> getAllQuery = em.createQuery("SELECT a.name FROM Animal a", String.class); // SELECT name FROM animals
		return getAllQuery.getResultList();
	}

	public void findAnimalsByNameAndUpdateName(String oldName, String newName) {
		// Quando si parla di operazioni che vanno a modificare il db come save, update, delete devo usare le transazioni
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Query modifyNameQuery = em.createQuery("UPDATE Animal a SET a.name = :newName WHERE a.name = :oldName"); // UPDATE animals SET name = 'nuovonome' WHERE name = 'nomevecchio'
		modifyNameQuery.setParameter("newName", newName); // Non dimentichiamoci di settare i parametri della query altrimenti eccezione
		modifyNameQuery.setParameter("oldName", oldName);
		int numeroDiAnimaliModificati = modifyNameQuery.executeUpdate(); // Le query di update a differenza di quelle select restituiscono un numero intero che rappresenta quante righe ha aggiornato
		transaction.commit();

		if (numeroDiAnimaliModificati > 0) System.out.println("Animali modificati");
		else System.out.println("Non ho trovato nessun animale di nome " + oldName);

	}

	public void findAnimalsByNameAndDelete(String name) {
		// Quando si parla di operazioni che vanno a modificare il db come save, update, delete devo usare le transazioni
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		Query modifyNameQuery = em.createQuery("DELETE FROM Animal a WHERE a.name = :name"); // DELETE FROM animals WHERE name = 'nomedacancellare'
		modifyNameQuery.setParameter("name", name); // Non dimentichiamoci di settare i parametri della query altrimenti eccezione
		int numeroDiAnimaliCancellati = modifyNameQuery.executeUpdate(); // Le query di update a differenza di quelle select restituiscono un numero intero che rappresenta quante righe ha aggiornato
		transaction.commit();

		if (numeroDiAnimaliCancellati > 0) System.out.println("Animali eliminati");
		else System.out.println("Non ho trovato nessun animale di nome " + name);

	}

}
