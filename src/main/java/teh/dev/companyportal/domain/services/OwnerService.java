package teh.dev.companyportal.domain.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teh.dev.companyportal.domain.models.Owner;
import teh.dev.companyportal.domain.models.OwnerData;
import teh.dev.companyportal.external.database.MemoryDataStore;
import teh.dev.companyportal.external.socialSecurityNumber.SocialSecurityRestService;
import teh.dev.companyportal.external.socialSecurityNumber.api.models.Person;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final SocialSecurityRestService socialSecurityRestService;
    private final MemoryDataStore memoryDataStore;

    public Owner getOwner(String socialSecurityNumber) {

        Owner owner = memoryDataStore.getOwnerBySocialSecurityNumber(socialSecurityNumber);

        if(!Objects.isNull(owner)) {
            return owner;
        }

        Person person = socialSecurityRestService.getPerson(socialSecurityNumber);

        if(Objects.isNull(person)) {
            return null;
        }

        OwnerData ownerData = OwnerData.builder()
                .name(person.getName())
                .socialSecurityNumber(person.getSocialSecurityNumber())
                .build();

        return memoryDataStore.registerOwner(ownerData);
    }
}
