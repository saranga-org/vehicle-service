package FuelPass.Dev.FuelPass.Service;

import FuelPass.Dev.FuelPass.DTO.VehicleDTO;
import FuelPass.Dev.FuelPass.DTO.VehicleHeaderDTO;
import FuelPass.Dev.FuelPass.Entity.Vehicle;
import FuelPass.Dev.FuelPass.Entity.VehicleHeader;
import FuelPass.Dev.FuelPass.Repo.VehicleHeaderRepo;
import FuelPass.Dev.FuelPass.Repo.VehicleRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleHeaderService {
    @Autowired
    private VehicleHeaderRepo vehicleHeaderRepo;
    @Autowired
    private ModelMapper modelMapper;

    public boolean verifyVehicle(VehicleDTO vehicleDTO) {
        Optional<VehicleHeader> currentVehicle = vehicleHeaderRepo.findByVehicleNumber(vehicleDTO.getVehicleNumber());

        if (currentVehicle.isPresent()) {
            VehicleHeader vehicleHeader = currentVehicle.get();

            if (vehicleDTO.getVehicleNumber().equals(vehicleHeader.getVehicleNumber()) &&
                    vehicleDTO.getChassisNo().equals(vehicleHeader.getChassisNo()) &&
                    vehicleDTO.getFuelType() == vehicleHeader.getFuelType() &&
                    vehicleDTO.getVehicleType() == vehicleHeader.getVehicleType()) {
                return true;
            } else {
                throw new IllegalArgumentException("Vehicle data mismatch.");
            }
        }
        throw new IllegalArgumentException("Vehicle not found in header database.");
    }

}
