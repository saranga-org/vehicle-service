package FuelPass.Dev.FuelPass.Repo;

import FuelPass.Dev.FuelPass.Entity.Vehicle;
import FuelPass.Dev.FuelPass.Entity.VehicleHeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleHeaderRepo extends JpaRepository<VehicleHeader, Integer> {
    Optional<VehicleHeader> findByVehicleNumber(String vehicleNumber);
}
