package FuelPass.Dev.FuelPass.Controller;

import FuelPass.Dev.FuelPass.Service.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/vehicle")
@CrossOrigin
@AllArgsConstructor
public class QRCodeController {

    private VehicleService vehicleService;

    @PutMapping("/reset-qrcode")
    public ResponseEntity<?> resetQrCode(@RequestParam String vehicleNo) {
        try {
            String newQrCode = vehicleService.resetQrCode(vehicleNo);
            return ResponseEntity.ok("QR Code reset successfully. New QR Code: " + newQrCode);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error resetting QR code: " + e.getMessage());
        }
    }
}
