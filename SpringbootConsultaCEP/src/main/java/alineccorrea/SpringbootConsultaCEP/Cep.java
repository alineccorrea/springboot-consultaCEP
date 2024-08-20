package alineccorrea.SpringbootConsultaCEP;

import lombok.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor (access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class Cep {
    private static Map<String, Cep> cepsCache = new HashMap<String, Cep>();
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

    @Override
    public Object clone() {
        return new Cep(cep, logradouro, complemento, unidade, bairro, localidade, uf, ibge, gia, ddd, siafi);
    }

    @Override
    public boolean equals(@NonNull Object obj) {
        if (!(obj instanceof Cep))
            return false;

        Cep cep = ((Cep) obj);
        return this.cep.equals(cep.cep);
    }

    @Override
    public int hashCode() {
        return this.cep.hashCode();
    }

    public static boolean saveCepInCache(@NonNull Cep cep) {
        return (Boolean) Cep.cepsCacheOperations("save", cep);
    }

    public static Cep searchCepInCache(@NonNull String cep) {
        return (Cep) Cep.cepsCacheOperations("search", cep);
    }

    public static boolean deleteCepFromCache(@NonNull String cep) {
        return (Boolean) Cep.cepsCacheOperations("delete", cep);
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Cep> getCepsCache() {
        return (Map<String, Cep>) Cep.cepsCacheOperations("get", null);
    }

    private static synchronized Object cepsCacheOperations(@NonNull String operation, Object parameter) {
        switch (operation) {
            case ("save"): {
                Cep cep = (Cep) parameter;
                return Cep.cepsCache.put(cep.getCep(), cep) == null;
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
                return new HashMap<String, Cep>(Cep.cepsCache);
            }
            default:
                throw new RuntimeException(
                        "Operacao invalida ao manipular cache de Cep. Operacao solicitada: " + operation);
        }
    }

    public static boolean isValid(String cep) {
        Pattern cepPattern = Pattern.compile("^[0-9]{5}-[0-9]{3}$");
        return cepPattern.matcher(cep).matches();
    }

}
