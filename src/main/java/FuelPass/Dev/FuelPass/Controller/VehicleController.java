package FuelPass.Dev.FuelPass.Controller;


import FuelPass.Dev.FuelPass.DTO.VehicleDTO;
import FuelPass.Dev.FuelPass.Entity.Vehicle;
import FuelPass.Dev.FuelPass.Repo.VehicleRepo;
import FuelPass.Dev.FuelPass.Service.JwtTokenService;
import FuelPass.Dev.FuelPass.Service.VehicleHeaderService;
import FuelPass.Dev.FuelPass.Service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/vehicle")
@CrossOrigin
@AllArgsConstructor
public class VehicleController {

    private VehicleService vehicleService;
    private VehicleHeaderService vehicleHeaderService;
    private VehicleRepo vehicleRepo;
    private JwtTokenService jwtTokenService;


    @PostMapping("/create")
    public ResponseEntity<?> saveVehicle(@RequestBody VehicleDTO vehicleDTO, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        String userName = jwtTokenService.getUsernameFromToken(token);
        vehicleDTO.setUserName(userName);

        try {
            boolean isValidate = vehicleHeaderService.verifyVehicle(vehicleDTO);

            if (isValidate) {
                System.out.println("Vehicle validated");

                Optional<Vehicle> existingVehicle = vehicleRepo.findByVehicleNumber(vehicleDTO.getVehicleNumber());
                if (existingVehicle.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body("Error: Vehicle already exists in the database.");
                }

                try {
                    VehicleDTO savedVehicle = vehicleService.saveVehicle(vehicleDTO);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedVehicle);
                } catch (IllegalArgumentException e) {
                    System.out.println("Validation error in controller: " + e.getMessage());
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation Error: " + e.getMessage());
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Vehicle validation failed.");
            }

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred.");
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getVehiclesByUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        String userName = jwtTokenService.getUsernameFromToken(token);
        List<VehicleDTO> vehicleDTOs = vehicleService.getVehiclesByUser(userName);
        return ResponseEntity.ok(vehicleDTOs);
    }
}
