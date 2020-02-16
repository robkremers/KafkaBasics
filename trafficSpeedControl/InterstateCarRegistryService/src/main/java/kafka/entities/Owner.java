package kafka.entities;

public class Owner {
	private String name;
	private String address;

	public Owner() {
		super();
	}

	public Owner(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Owner)) return false;

		Owner owner = (Owner) o;

		if (!name.equals(owner.name)) return false;
		return address.equals(owner.address);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + address.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Owner{" +
				"name='" + name + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
