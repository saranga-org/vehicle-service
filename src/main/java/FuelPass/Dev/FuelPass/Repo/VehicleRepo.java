package FuelPass.Dev.FuelPass.Repo;

import FuelPass.Dev.FuelPass.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findByVehicleNumber(String vehicleNumber);

    List<Vehicle> findByUserName(String userName);
}
