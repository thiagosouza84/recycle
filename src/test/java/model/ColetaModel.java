package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ColetaModel {
    @Expose(serialize = false)
    private int numeroColeta;
    @Expose
    private String descricao;
    @Expose
    private EnderecoModel endereco;
    @Expose
    private String dataColeta;
}
