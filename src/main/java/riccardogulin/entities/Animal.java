package riccardogulin.entities;

import javax.persistence.*;

@Entity
@Table(name = "animals")
// @Inheritance(strategy = InheritanceType.SINGLE_TABLE) --> Ci genererà una singola tabella per tutti gli animali (con tanti valori null però)
@Inheritance(strategy = InheritanceType.JOINED)
// --> Ci genererà tre tabelle, una per gli attributi comuni, due per gli attributi specifici di Cat e Dog (però questo può richiedere dei join)
@DiscriminatorColumn(name = "tipo_animale") // Cambia il nome della colonna discriminante da DTYPE a tipo_animale
public abstract class Animal {
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
