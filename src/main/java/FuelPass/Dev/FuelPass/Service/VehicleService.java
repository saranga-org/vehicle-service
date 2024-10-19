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

@Service
@Transactional
@RequiredArgsConstructor
public class VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private ModelMapper modelMapper;

    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO){
        if (vehicleDTO.getRegNo() == null || vehicleDTO.getChassisNo() == null ||
                vehicleDTO.getVehicleType() == null || vehicleDTO.getFuelType() == null) {
            throw new IllegalArgumentException("Fields cannot be null");
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setRegNo(vehicleDTO.getRegNo());
        vehicle.setChassisNo(vehicleDTO.getChassisNo());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setFuelType(vehicleDTO.getFuelType());

        Vehicle dto = vehicleRepo.save(vehicle);


        return modelMapper.map(dto, VehicleDTO.class);
    }

    public List<VehicleDTO> getAllVehicles(){

        List<Vehicle>vehicleList = vehicleRepo.findAll();
        return modelMapper.map(vehicleList, new TypeToken<List<VehicleDTO>>(){}.getType());
    }


}
