# springboot-consultaCEP
API REST de consulta ao webservice ViaCEP e salvando em cache os CEPs já consultados.

**Como consultar?**
- Exemplo de solicitação: http://localhost:8080/cep/{cep}. Basta substituir o <code>{cep}</code> pelo Cep desejado.
  - O formato aceito é <code>00000-000</code>

**Chamada com CEP em formato inválido**
<code>BadRequestException</code>
<code>"status": 400</code>
<code>"error": "Bad Request"</code>
<code>"message": "Formato de CEP invalido"</code>

**Stacks:**
- JDK 17
- Spring Boot 3
- Maven

**Bibliotecas externas utilizadas:**
- Spring Web 
- Lombok 
- Spring Boot DevTools Developer Tools
