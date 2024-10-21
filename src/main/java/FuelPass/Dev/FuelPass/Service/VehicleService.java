package FuelPass.Dev.FuelPass.Service;

import FuelPass.Dev.FuelPass.DTO.VehicleDTO;
import FuelPass.Dev.FuelPass.Entity.Vehicle;
import FuelPass.Dev.FuelPass.Repo.VehicleRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private ModelMapper modelMapper;

    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        try {
            // Validate mandatory fields
            if (vehicleDTO.getRegNo() == null || vehicleDTO.getRegNo().isEmpty() ||
                    vehicleDTO.getChassisNo() == null || vehicleDTO.getChassisNo().isEmpty() ||
                    vehicleDTO.getVehicleType() == null || vehicleDTO.getFuelType() == null) {
                throw new IllegalArgumentException("Required fields cannot be null or empty");
            }

            // Map VehicleDTO to Vehicle entity
            Vehicle vehicle = new Vehicle();
            vehicle.setRegNo(vehicleDTO.getRegNo());
            vehicle.setChassisNo(vehicleDTO.getChassisNo());
            vehicle.setVehicleType(vehicleDTO.getVehicleType());
            vehicle.setFuelType(vehicleDTO.getFuelType());

            // Save the vehicle
            Vehicle savedVehicle = vehicleRepo.save(vehicle);

            // Map back to VehicleDTO and return
            return modelMapper.map(savedVehicle, VehicleDTO.class);

        } catch (IllegalArgumentException e) {
            // Handle validation error
            throw new IllegalArgumentException("Validation Error: " + e.getMessage());
        } catch (Exception e) {
            // Handle general exceptions
            throw new RuntimeException("An error occurred while saving the vehicle", e);
        }
    }


    public List<VehicleDTO> getAllVehicles() {
        try {
            // Retrieve all vehicles from the repository
            List<Vehicle> vehicleList = vehicleRepo.findAll();

            // Check if the list is empty and handle it if needed
            if (vehicleList.isEmpty()) {
                throw new NoSuchElementException("No vehicles found");
            }

            // Map the list of Vehicle entities to a list of VehicleDTOs
            return modelMapper.map(vehicleList, new TypeToken<List<VehicleDTO>>(){}.getType());
        } catch (NoSuchElementException e) {
            // Handle case when no vehicles are found
            throw new NoSuchElementException(e.getMessage());
        } catch (Exception e) {
            // Handle unexpected errors
            throw new RuntimeException("An error occurred while retrieving vehicles", e);
        }
    }


}
