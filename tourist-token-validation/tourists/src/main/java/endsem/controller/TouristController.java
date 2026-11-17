package insem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import insem.model.Tourist;
import insem.security.JwtUtil;
import insem.service.TouristService;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {

    private final TouristService service;
    private final JwtUtil jwtUtil;

    public TouristController(
            TouristService service,
            JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTourist(
            @PathVariable Long id,
            @RequestHeader(
                    value = "Authorization",
                    required = false) String authHeader,
            @RequestBody Tourist request) {

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")
                || !jwtUtil.isValidToken(
                        authHeader.substring(7))) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or missing JWT token");
        }

        Tourist updated = service.updateTourist(id, request);

        if (updated == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Tourist not found");
        }

        return ResponseEntity.ok(
                "Tourist Updated Successfully");
    }
    @GetMapping("/token")
    public String getToken() {
        return jwtUtil.generateToken();
    }
}