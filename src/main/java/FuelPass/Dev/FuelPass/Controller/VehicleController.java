package FuelPass.Dev.FuelPass.Controller;


import FuelPass.Dev.FuelPass.DTO.VehicleDTO;
import FuelPass.Dev.FuelPass.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/vehicle")
@CrossOrigin
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;


    @PostMapping("/saveVehicle")
    public VehicleDTO saveVehicle(@RequestBody VehicleDTO vehicleDTO){
        return vehicleService.saveVehicle(vehicleDTO);
    }

//    @GetMapping("/getVehicles")
//    public List<VehicleDTO> getVehicles(){
//        return vehicleService.getAllVehicles();
//    }
}
