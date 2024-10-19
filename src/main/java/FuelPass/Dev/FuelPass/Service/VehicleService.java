package FuelPass.Dev.FuelPass.Service;

import FuelPass.Dev.FuelPass.DTO.VehicleDTO;
import FuelPass.Dev.FuelPass.Entity.Vehicle;
import FuelPass.Dev.FuelPass.Repo.VehicleRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleService {
    @Autowired
    private VehicleRepo vehicleRepo;
    @Autowired
    private ModelMapper modelMapper;

    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO){
        Vehicle vehicle = new Vehicle();

        vehicle.setRegNo(vehicleDTO.getRegNo());
        vehicle.setChassisNo(vehicleDTO.getChassisNo());
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setFuelType(vehicleDTO.getFuelType());

        Vehicle dto = vehicleRepo.save(vehicle);


        return modelMapper.map(dto, VehicleDTO.class);

    }

}
