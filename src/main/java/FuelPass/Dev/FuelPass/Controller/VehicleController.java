package FuelPass.Dev.FuelPass.Controller;


import FuelPass.Dev.FuelPass.DTO.VehicleDTO;
import FuelPass.Dev.FuelPass.Service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/vehicle")
@CrossOrigin
@AllArgsConstructor
public class VehicleController {

    private VehicleService vehicleService;


    @PostMapping("/saveVehicle")
    public ResponseEntity<VehicleDTO> saveVehicle(@RequestBody VehicleDTO vehicleDTO) {
        try {
            VehicleDTO savedVehicle = vehicleService.saveVehicle(vehicleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getVehicles")
    public ResponseEntity<List<VehicleDTO>> getVehicles() {
        try {
            List<VehicleDTO> vehicles = vehicleService.getAllVehicles();

            if (vehicles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(vehicles);
            }

            return ResponseEntity.status(HttpStatus.OK).body(vehicles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
