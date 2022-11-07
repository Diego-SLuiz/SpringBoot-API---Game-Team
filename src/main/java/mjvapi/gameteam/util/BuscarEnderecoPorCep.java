package mjvapi.gameteam.util;

import com.google.gson.Gson;
import mjvapi.gameteam.model.EnderecoModel;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscarEnderecoPorCep {
    public static EnderecoModel buscar(String cep) {
        Gson gson = new Gson();

        URI uri;
        try {
            uri = new URI(String.format("viacep.com.br/ws/%s/json/", cep));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        EnderecoModel endereco = gson.fromJson(response.body(), EnderecoModel.class);
        return endereco;
    }

}
