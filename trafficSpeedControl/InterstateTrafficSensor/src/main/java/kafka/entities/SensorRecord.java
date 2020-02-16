package kafka.entities;

public class SensorRecord {
	public String licencePlate;
	public int minute;
	public int second;
	public int cameraId;



	public SensorRecord(String licencePlate, int minute, int second, int cameraId) {
		super();
		this.licencePlate = licencePlate;
		this.minute = minute;
		this.second = second;
		this.cameraId = cameraId;
	}

	public SensorRecord() {
		super();
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}


	public int getCameraId() {
		return cameraId;
	}

	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof SensorRecord)) return false;

		SensorRecord that = (SensorRecord) o;

		if (minute != that.minute) return false;
		if (second != that.second) return false;
		if (cameraId != that.cameraId) return false;
		return licencePlate.equals(that.licencePlate);
	}

	@Override
	public int hashCode() {
		int result = licencePlate.hashCode();
		result = 31 * result + minute;
		result = 31 * result + second;
		result = 31 * result + cameraId;
		return result;
	}

	@Override
	public String toString() {
		return "SensorRecord{" +
				"licencePlate='" + licencePlate + '\'' +
				", minute=" + minute +
				", second=" + second +
				", cameraId=" + cameraId +
				'}';
	}
}
