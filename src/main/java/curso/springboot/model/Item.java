package curso.springboot.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "nome veio vazio")
	@NotNull(message = "nome veio null")
	private String nome;

	@NotEmpty(message = "sobrenome veio vazio")
	@NotNull(message = "sobrenome veio null")
	private String historia;
	
	@OneToMany(mappedBy = "item", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Estimativa> estimativas;
	
	public List<Estimativa> getEstimativas() {
		return estimativas;
	}

	public void setEstimativas(List<Estimativa> estimativas) {
		this.estimativas = estimativas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getHistoria() {
		return historia;
	}

	public void setHistoria(String historia) {
		this.historia = historia;
	}

}
