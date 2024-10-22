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
        VehicleHeaderDTO vehicleHeaderDTO = modelMapper.map(vehicleDTO, VehicleHeaderDTO.class);

        System.out.println("hhh");
        System.out.println(vehicleHeaderDTO.getVehicleNumber());
        Optional<VehicleHeader> currentVehicle = vehicleHeaderRepo.findByVehicleNumber(vehicleHeaderDTO.getVehicleNumber());
        System.out.println(currentVehicle.isPresent());
        if(currentVehicle.isPresent()){
            if (vehicleHeaderDTO.getVehicleNumber() == currentVehicle.get().getVehicleNumber() && vehicleHeaderDTO.getChassisNo() == currentVehicle.get().getChassisNo() &&
                    vehicleHeaderDTO.getFuelType() == currentVehicle.get().getFuelType() && vehicleHeaderDTO.getVehicleType() == currentVehicle.get().getVehicleType() &&
                    vehicleHeaderDTO.getUserName() == currentVehicle.get().getUserName()) {
                System.out.println("ggg");
                return true;
            }
        }
        else{
            return false;
        }
        return false;
    }
}
