package Finance.Fast_System.Rules;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import Finance.Fast_System.Core.ModelMapperServices;
import Finance.Fast_System.Exceptions.TcException;
import Finance.Fast_System.business.requests.CreateCivilAccountRequests;
import Finance.Fast_System.business.responses.CivilSystemAPIResponse;
import Finance.Fast_System.dataRepository.CivilRepository;
import Finance.Fast_System.entities.Civil;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CheckTcKimlikNumberRule {

    private final CivilRepository civilRepository;
    private final RestTemplate restTemplate;
    private final ModelMapperServices modelMapperServices;

    public Civil checkTcKimlikNumber(CreateCivilAccountRequests createCivilAccountRequests) {

        Optional<Civil> existingCivil = civilRepository.findByTcKimlikNumber(createCivilAccountRequests.getTcKimlikNumber());

        if (existingCivil.isPresent()) {

            return existingCivil.get();
        } else {

            UriComponentsBuilder uriBuilder = UriComponentsBuilder
                    .newInstance()
                    .scheme("http")
                    .host("localhost")
                    .port(8086)
                    .path("/api/person/check")
                    .queryParam("tcKimlikNumber", createCivilAccountRequests.getTcKimlikNumber())
                    .queryParam("corporationVkn", createCivilAccountRequests.getHolderbankVknNumber());

            CivilSystemAPIResponse apiResponse = null;
            
            try {
                apiResponse = restTemplate.getForObject(uriBuilder.toUriString(), CivilSystemAPIResponse.class);
                
                if (apiResponse.getTcKimlikNumber()==null || 
                        apiResponse.getBirthDate() == null ||
                        apiResponse.getGender()==null ||
                        apiResponse.getPersonName()==null ||
                        apiResponse.getPersonLastName()==null
                        ) 
                {
                	throw new TcException("Geçersiz TC kimlik numarası veya eksik veri: " + createCivilAccountRequests.getTcKimlikNumber());
                    }
               
            } 
            catch (TcException e) {
         
                throw e;
			}
            catch (Exception e) {
                throw new RuntimeException("Dış API ile bağlantı hatası", e);
            }

            Civil civil = modelMapperServices.forRequest()
            		.map(apiResponse, Civil.class);
            
            civil.setCreatedTime(LocalDateTime.now());

            civilRepository.save(civil);

            return civil;
        }
    }
}
