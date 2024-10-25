package FuelPass.Dev.FuelPass.FeignClient;


import FuelPass.Dev.FuelPass.DTO.FuelQuotaRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "fuelQuotaServiceClient", url = "http://localhost:8083/api/fuel-quota")
public interface FuelQuotaServiceClient {
    @PostMapping("/fuel-quota")
    ResponseEntity<String> addFuelQuota(@RequestBody FuelQuotaRequest fuelQuotaRequest);

    @PutMapping("/fuel-quota/reset-qrcode")
    ResponseEntity<String> resetQrCode(@RequestParam("vehicleNo") String vehicleNo);
}
