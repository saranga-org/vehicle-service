package FuelPass.Dev.FuelPass.Service;

import FuelPass.Dev.FuelPass.DTO.FuelQuotaRequest;
import FuelPass.Dev.FuelPass.DTO.VehicleDTO;
import FuelPass.Dev.FuelPass.Entity.FuelType;
import FuelPass.Dev.FuelPass.Entity.Vehicle;
import FuelPass.Dev.FuelPass.FeignClient.FuelQuotaServiceClient;
import FuelPass.Dev.FuelPass.Repo.VehicleRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Autowired
    private final FuelQuotaServiceClient fuelQuotaServiceClient;

    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {
        System.out.println(vehicleDTO);
        try {
            // Validate FuelType from the DTO before mapping
            if (vehicleDTO.getFuelType() == null || !isValidFuelType(vehicleDTO.getFuelType())) {
                throw new IllegalArgumentException("Invalid fuel type: " + vehicleDTO.getFuelType());
            }

            Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);

            Vehicle savedVehicle = vehicleRepo.save(vehicle);

            addFuelQuota(vehicleDTO);

            return modelMapper.map(savedVehicle, VehicleDTO.class);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Validation Error: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while saving the vehicle", e);
        }
    }

    private boolean isValidFuelType(FuelType fuelType) {
        return Arrays.stream(FuelType.values())
                .anyMatch(validType -> validType.equals(fuelType));
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

    public void addFuelQuota(VehicleDTO vehicleDTO) {
        FuelQuotaRequest fuelQuotaRequest =new FuelQuotaRequest(vehicleDTO.getVehicleNumber(),vehicleDTO.getVehicleType().name(),vehicleDTO.getFuelType().name(),30,vehicleDTO.getContactNo());
        ResponseEntity<String> response = fuelQuotaServiceClient.addFuelQuota(fuelQuotaRequest);
        response.getBody();
    }

    public String resetQrCode(String vehicleNo) {
        ResponseEntity<String> response = fuelQuotaServiceClient.resetQrCode(vehicleNo);
        return response.getBody();
    }


}
