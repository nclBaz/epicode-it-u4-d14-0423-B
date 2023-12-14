package riccardogulin.entities;

import javax.persistence.*;

@Entity
@Table(name = "animals")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// --> Ci genererà una singola tabella per tutti gli animali (con tanti valori null però)
// @Inheritance(strategy = InheritanceType.JOINED)
// --> Ci genererà tre tabelle, una per gli attributi comuni, due per gli attributi specifici di Cat e Dog (però questo può richiedere dei join)
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// --> Anche detta table per CONCRETE class, nel senso che verrà creata una tabella per ogni classe concreta (anche il padre)
@DiscriminatorColumn(name = "tipo_animale") // Cambia il nome della colonna discriminante da DTYPE a tipo_animale
// @MappedSuperclass
// <-- Alternativa alle 3 strategie di sopra, molto simile a table_per_class. La differenza è che se la classe padre dovesse essere concreta,
// non verrebbe creata una tabella per essa. N.B. Con mappedsuperclass la classe padre non sarà una Entity

@NamedQuery(name = "findByName", query = "SELECT a FROM Animal a WHERE a.name = :name")
// <-- Le NamedQuery sono query associate all'entità che posso richiamare poi ovunque io voglia tramite il loro name
@NamedQuery(name = "findByNameStartsWith", query = "SELECT a FROM Animal a WHERE LOWER(a.name) LIKE LOWER(CONCAT(:partialname, '%'))")
// <-- con una combinazione di CONCAT E LOWER riesco ad implementare ILIKE (che non sarebbe disponibile altrimenti)
public abstract class Animal { // Se la classe Animal non fosse Abstract, con la strategia TABLE_PER_CLASS avremmo una tabella anche per gli Animal
	@Id
	@GeneratedValue
	protected long id;
	protected String name;
	protected int age;

	public Animal() {
	}

	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
