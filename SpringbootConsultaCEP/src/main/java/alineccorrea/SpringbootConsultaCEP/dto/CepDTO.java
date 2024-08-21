package alineccorrea.SpringbootConsultaCEP.dto;

import lombok.*;

import java.util.*;
import java.util.regex.Pattern;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class CepDTO {
    private static Map<String, CepDTO> cepsCache = new HashMap<String, CepDTO>();
    @Getter
    @NonNull
    private String cep;
    @Getter
    private String logradouro;
    @Getter
    private String complemento;
    @Getter
    private String unidade;
    @Getter
    private String bairro;
    @Getter
    private String localidade;
    @Getter
    private String uf;
    @Getter
    private String ibge;
    @Getter
    private String gia;
    @Getter
    private String ddd;
    @Getter
    private String siafi;

    public static boolean isValid(String cep) {
        Pattern cepPattern = Pattern.compile("^[0-9]{5}-[0-9]{3}$");
        return cepPattern.matcher(cep).matches();
    }

    public static boolean saveCepInCache(@NonNull CepDTO cep) {
        return (Boolean) CepDTO.cepsCacheOperations("save", cep);
    }

    public static CepDTO searchCepInCache(@NonNull String cep) {
        return (CepDTO) CepDTO.cepsCacheOperations("search", cep);
    }

    public static boolean deleteCepFromCache(@NonNull String cep) {
        return (Boolean) CepDTO.cepsCacheOperations("delete", cep);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, CepDTO> getCepsCache() {
        return (Map<String, CepDTO>) CepDTO.cepsCacheOperations("get", null);
    }

    private static synchronized Object cepsCacheOperations(@NonNull String operation, Object parameter) {
        switch (operation) {
            case ("save"): {
                CepDTO cep = (CepDTO) parameter;
                return CepDTO.cepsCache.put(cep.getCep(), cep) == null;
            }
            case ("search"): {
                String key = (String) parameter;
                return cepsCache.get(key);
            }
            case ("delete"): {
                String key = (String) parameter;
                return cepsCache.remove(key) != null;
            }
            case ("get"): {
                return new HashMap<String, CepDTO>(CepDTO.cepsCache);
            }
            default:
                throw new RuntimeException(
                        "Operacao invalida ao manipular cache de Cep. Operacao solicitada: " + operation);
        }
    }

    @Override
    public Object clone() {
        return new CepDTO(cep, logradouro, complemento, unidade, bairro, localidade, uf, ibge, gia, ddd, siafi);
    }

    @Override
    public boolean equals(@NonNull Object obj) {
        if (!(obj instanceof CepDTO))
            return false;

        CepDTO cep = ((CepDTO) obj);
        return this.cep.equals(cep.cep);
    }

    @Override
    public int hashCode() {
        return this.cep.hashCode();
    }

}
