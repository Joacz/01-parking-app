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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Parking_spots")
@Getter
@Setter
@ToString
public class ParkingSpots {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Integer id;

  private Boolean occuped;

  public ParkingSpots() {}

  public ParkingSpots(
    ParkingLots lot,
    Boolean occuped,
    ParkingHistory parking_history
  ) {
    this.lot = lot;
    this.occuped = occuped;
  }

  public ParkingSpots(ParkingLots lot, Boolean occuped) {
    this.lot = lot;
    this.occuped = occuped;
  }

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(referencedColumnName = "id", name = "lot")
  private ParkingLots lot;
}
