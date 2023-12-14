package riccardogulin.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cane") // Cambia il valore della discriminator column da Dog a Cane
public class Dog extends Animal {
	private double maxSpeed;

	public Dog() {
	}

	public Dog(String name, int age, double maxSpeed) {
		this.name = name;
		this.age = age;
		this.maxSpeed = maxSpeed;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	@Override
	public String toString() {
		return "Dog{" +
				"maxSpeed=" + maxSpeed +
				", id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				"} ";
	}
}
