package kafka.entities;

public class FeeRecord {
	private String licencePlate;
	private String ownerName;
	private String ownerAddress;
	private int speed;

	public FeeRecord() {
		super();
	}

	public FeeRecord(String licencePlate, String ownerName, String ownerAddress, int speed) {
		super();
		this.licencePlate = licencePlate;
		this.ownerName = ownerName;
		this.ownerAddress = ownerAddress;
		this.speed = speed;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FeeRecord)) return false;

		FeeRecord feeRecord = (FeeRecord) o;

		if (speed != feeRecord.speed) return false;
		if (!licencePlate.equals(feeRecord.licencePlate)) return false;
		if (!ownerName.equals(feeRecord.ownerName)) return false;
		return ownerAddress.equals(feeRecord.ownerAddress);
	}

	@Override
	public int hashCode() {
		int result = licencePlate.hashCode();
		result = 31 * result + ownerName.hashCode();
		result = 31 * result + ownerAddress.hashCode();
		result = 31 * result + speed;
		return result;
	}

	@Override
	public String toString() {
		return "FeeRecord{" +
				"licencePlate='" + licencePlate + '\'' +
				", ownerName='" + ownerName + '\'' +
				", ownerAddress='" + ownerAddress + '\'' +
				", speed=" + speed +
				'}';
	}
}
