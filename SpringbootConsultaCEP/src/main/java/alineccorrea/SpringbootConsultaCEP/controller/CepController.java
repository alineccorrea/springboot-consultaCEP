package alineccorrea.SpringbootConsultaCEP.controller;

import alineccorrea.SpringbootConsultaCEP.Cep;
import alineccorrea.SpringbootConsultaCEP.exceptions.BadRequestException;
import alineccorrea.SpringbootConsultaCEP.service.CepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consulta-cep")
public class CepController {

    @GetMapping("{cep}")
    public Cep consultaCep(@PathVariable("cep") String cep) throws Exception {
        if(Cep.isValid(cep)){
            Cep cepSearch = null;
            if (Cep.searchCepInCache(cep) != null){
                cepSearch = Cep.searchCepInCache(cep);
            }else {
                cepSearch = new CepService().getFromViaCepApi(cep);
            }
            return cepSearch;
        } else {
            throw new BadRequestException("Formato de CEP invalido");
        }
    }
}
