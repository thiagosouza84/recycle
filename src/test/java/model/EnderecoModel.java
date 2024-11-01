package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class EnderecoModel {
    @Expose
    private String rua;
    @Expose
    private String cidade;
    @Expose
    private String estado;
    @Expose
    private String cep;
}

