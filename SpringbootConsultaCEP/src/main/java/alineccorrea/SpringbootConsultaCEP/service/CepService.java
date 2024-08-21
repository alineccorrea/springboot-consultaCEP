package alineccorrea.SpringbootConsultaCEP.service;

import alineccorrea.SpringbootConsultaCEP.dto.CepDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    public CepDTO getFromViaCepApi(String cepInput) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CepDTO> resp =
                restTemplate
                        .getForEntity(
                                String.format("https://viacep.com.br/ws/%s/json/", cepInput),
                                CepDTO.class);
        CepDTO.saveCepInCache((CepDTO) resp.getBody());
        return resp.getBody();
    }

    public boolean deleteCepFromCache(String cep) {
        return CepDTO.deleteCepFromCache(cep);
    }
}
