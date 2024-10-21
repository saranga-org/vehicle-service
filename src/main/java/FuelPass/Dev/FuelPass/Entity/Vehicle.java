package FuelPass.Dev.FuelPass.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "regNO", nullable = false)
    private String regNo;

    @Column(name = "chassisNo", nullable = false)
    private String chassisNo;

    @Column(name = "userId", nullable = false)
    private String userId;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "vehicleType", nullable = false)
    private VehicleType vehicleType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fuelType", nullable = false)
    private FuelType fuelType;

}
