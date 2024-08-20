package alineccorrea.SpringbootConsultaCEP.service;

import alineccorrea.SpringbootConsultaCEP.Cep;
import alineccorrea.SpringbootConsultaCEP.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    public boolean deleteCepFromCache(String cep) {
        return Cep.deleteCepFromCache(cep);
    }

    public Cep getFromViaCepApi(String cepInput) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Cep> resp =
                restTemplate
                        .getForEntity(
                                String.format("https://viacep.com.br/ws/%s/json/", cepInput),
                                Cep.class);
        Cep.saveCepInCache((Cep) resp.getBody());
        return resp.getBody();
    }
}
