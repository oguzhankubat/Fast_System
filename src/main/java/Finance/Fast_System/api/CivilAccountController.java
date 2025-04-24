package Finance.Fast_System.api;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Finance.Fast_System.business.abstracts.CivilAccountService;
import Finance.Fast_System.business.requests.CreateCivilAccountRequests;
import Finance.Fast_System.business.requests.EnableAndDisableCivilAccountRequests;
import Finance.Fast_System.business.responses.AfterCreateCivilAccountResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/civilAccount")
@AllArgsConstructor
public class CivilAccountController {
	private final CivilAccountService civilAccountService;
	
    @PostMapping("/createAccount")
    public AfterCreateCivilAccountResponses createCivilAccount(@Valid @RequestBody() CreateCivilAccountRequests createCivilAccountRequests) {
        return civilAccountService.createCivilAccount(createCivilAccountRequests);
    }
    
    @PatchMapping("/disableAccount")
    public String disableCivilAccount(@Valid @RequestBody() EnableAndDisableCivilAccountRequests disableCivilAccountRequests) {
        return civilAccountService.disableCivilAccount(disableCivilAccountRequests);
    }

    @PatchMapping("/enableAccount")
    public String enableCivilAccount(@Valid @RequestBody() EnableAndDisableCivilAccountRequests enableAndDisableCivilAccountRequests) {
        return civilAccountService.enableCivilAccount(enableAndDisableCivilAccountRequests);
    }
}
