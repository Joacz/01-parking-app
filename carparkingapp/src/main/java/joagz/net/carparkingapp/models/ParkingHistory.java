package joagz.net.carparkingapp.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Parking_history")
@Getter
@Setter
@ToString
public class ParkingHistory {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Integer id;

  private Date start_time = null;
  private Date end_time = null;

  public ParkingHistory() {}

  public ParkingHistory(
    Date start_time,
    Date end_time,
    ParkingSpots parking_spot
  ) {
    this.start_time = start_time;
    this.end_time = end_time;
  }

  public ParkingHistory(Date start_time, ParkingSpots parking_spot) {
    this.start_time = start_time;
  }
}
