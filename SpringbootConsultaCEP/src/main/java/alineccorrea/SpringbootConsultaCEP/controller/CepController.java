package alineccorrea.SpringbootConsultaCEP.controller;

import alineccorrea.SpringbootConsultaCEP.controller.exceptions.BadRequestException;
import alineccorrea.SpringbootConsultaCEP.dto.CepDTO;
import alineccorrea.SpringbootConsultaCEP.service.CepService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consulta-cep")
public class CepController {

    @GetMapping("{cep}")
    public CepDTO consultaCep(@PathVariable("cep") String cep) throws Exception {
        if(CepDTO.isValid(cep)){
            CepDTO cepSearch = null;
            if (CepDTO.searchCepInCache(cep) != null){
                cepSearch = CepDTO.searchCepInCache(cep);
            }else {
                cepSearch = new CepService().getFromViaCepApi(cep);
            }
            return cepSearch;
        } else {
            throw new BadRequestException("Formato de CEP invalido");
        }
    }
}
