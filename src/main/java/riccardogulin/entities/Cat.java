package riccardogulin.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Gatto") // Cambia il valore della discriminator column da Cat a Gatto
public class Cat extends Animal {
	private double maxJumpHeight;

	public Cat() {
	}

	public Cat(String name, int age, double maxJumpHeight) {
		this.name = name;
		this.age = age;
		this.maxJumpHeight = maxJumpHeight;
	}

	public double getMaxJumpHeight() {
		return maxJumpHeight;
	}

	public void setMaxJumpHeight(double maxJumpHeight) {
		this.maxJumpHeight = maxJumpHeight;
	}

	@Override
	public String toString() {
		return "Cat{" +
				"maxJumpHeight=" + maxJumpHeight +
				", id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				"} ";
	}
}
