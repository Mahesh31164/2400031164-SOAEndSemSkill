package insem.service;

import org.springframework.stereotype.Service;

import insem.model.Tourist;
import insem.repository.TouristRepository;

@Service
public class TouristService {

    private final TouristRepository repository;

    public TouristService(TouristRepository repository) {
        this.repository = repository;
    }

    public Tourist updateTourist(Long id, Tourist request) {

        Tourist tourist = repository.findById(id).orElse(null);

        if (tourist == null) {
            return null;
        }

        tourist.setName(request.getName());
        tourist.setVisitedDestination(
                request.getVisitedDestination());
        tourist.setContactDetails(
                request.getContactDetails());

        return repository.save(tourist);
    }
}