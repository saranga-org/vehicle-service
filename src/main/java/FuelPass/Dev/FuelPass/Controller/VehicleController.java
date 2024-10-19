package FuelPass.Dev.FuelPass.Controller;


import FuelPass.Dev.FuelPass.DTO.VehicleDTO;
import FuelPass.Dev.FuelPass.Service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/vehicle")
@CrossOrigin
@AllArgsConstructor
public class VehicleController {

    private VehicleService vehicleService;


    @PostMapping("/saveVehicle")
    public VehicleDTO saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        return vehicleService.saveVehicle(vehicleDTO);
    }

    @GetMapping("/getVehicles")
    public List<VehicleDTO> getVehicles(){
        return vehicleService.getAllVehicles();
    }
}
