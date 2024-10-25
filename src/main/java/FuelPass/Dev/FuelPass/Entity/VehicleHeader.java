package FuelPass.Dev.FuelPass.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicles_validate")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicleNumber", nullable = false)
    private String vehicleNumber;

    @Column(name = "chassisNo", nullable = false)
    private String chassisNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicleType", nullable = false)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuelType", nullable = false)
    private FuelType fuelType;
}
