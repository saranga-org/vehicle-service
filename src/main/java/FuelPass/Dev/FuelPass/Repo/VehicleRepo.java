package FuelPass.Dev.FuelPass.Repo;

import FuelPass.Dev.FuelPass.Entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {
}
