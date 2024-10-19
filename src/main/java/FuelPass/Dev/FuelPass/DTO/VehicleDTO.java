package FuelPass.Dev.FuelPass.DTO;

import FuelPass.Dev.FuelPass.Entity.FuelType;
import FuelPass.Dev.FuelPass.Entity.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleDTO {
    private String regNo;
    private String chassisNo;
    private VehicleType vehicleType;
    private FuelType fuelType;
}
