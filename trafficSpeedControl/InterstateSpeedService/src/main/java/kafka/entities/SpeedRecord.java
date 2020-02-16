package kafka.entities;

public class SpeedRecord {
    public String licencePlate;
    public int speed;

    public SpeedRecord() {
        super();
    }

    public SpeedRecord(String licencePlate, int speed) {
        super();
        this.licencePlate = licencePlate;
        this.speed = speed;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
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
        if (!(o instanceof SpeedRecord)) return false;

        SpeedRecord that = (SpeedRecord) o;

        if (speed != that.speed) return false;
        return licencePlate.equals(that.licencePlate);
    }

    @Override
    public int hashCode() {
        int result = licencePlate.hashCode();
        result = 31 * result + speed;
        return result;
    }

    @Override
    public String toString() {
        return "SpeedRecord{" +
                "licencePlate='" + licencePlate + '\'' +
                ", speed=" + speed +
                '}';
    }
}
