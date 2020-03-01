package kafka.components;

import java.util.HashMap;
import java.util.Map;

import kafka.entities.SensorRecord;
import kafka.entities.SpeedRecord;
import kafka.services.Sender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpeedCalculator {

  private static final Log logger = LogFactory.getLog(SpeedCalculator.class);
  @Autowired
  Sender sender;

  @Value("${app.speedlimit}")
  private int speedlimit;

  private Map<String, SensorRecord> recordstream = new HashMap<>();

  public void handleRecord(SensorRecord sensorRecord) {
    int speed = 0;
    int time = 1000;
    if (sensorRecord.getCameraId() == 1) {
      recordstream.put(sensorRecord.getLicencePlate(), sensorRecord);
    }
    if (sensorRecord.getCameraId() == 2) {
      SensorRecord sensorRecord1 = recordstream.get(sensorRecord.getLicencePlate());
      if (sensorRecord1 != null) {
        if (sensorRecord1.getMinute() == sensorRecord.getMinute()) {
          time = sensorRecord.getSecond() - sensorRecord1.getSecond();
        } else if ((sensorRecord.getMinute() - sensorRecord1.getMinute()) == 1) {
          time = (sensorRecord.getSecond() + 60) - sensorRecord1.getSecond();
        }
        speed = (int) ((0.5 / time) * 3600);
        logger.info("Measured speed of " + sensorRecord.getLicencePlate() + " = " + speed);
        recordstream.remove(sensorRecord.getLicencePlate());
        if (speed > speedlimit) {
          logger.info("speeding ******** " + sensorRecord.getLicencePlate() + " = " + speed + " --> tofasttopic");
          sender.send(new SpeedRecord(sensorRecord.getLicencePlate(), speed));
        }
      }
    }
  }

}
