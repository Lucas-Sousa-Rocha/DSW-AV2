package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import entidade.Jogo;

@ManagedBean
@SessionScoped
public class JogoBean {

	private List<Jogo> jogos = new ArrayList<>();
	private Jogo jogo = new Jogo();
	private Jogo jogoSelecionado;
	private int ultimoId = 0;

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public Jogo getJogoSelecionado() {
		return jogoSelecionado;
	}

	public String salvarJogo() {
		jogo.setId(++ultimoId);
		jogo.setData(new Date());
		jogo.setNumeroSecreto(gerarNumeroSecreto());
		jogo.setResultado(calcularResultado());
		jogos.add(jogo);

		// Mensagem no console indicando que o jogo foi salvo
		System.out.println("Jogo salvo: Nome do Jogador - " + jogo.getNomeJogador());

		// Adiciona uma mensagem ao contexto do Faces
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Jogo salvo com sucesso!", null));

		// Reseta o objeto jogo para uma nova entrada
		jogo = new Jogo();
		
		// Retorna null para permanecer na mesma página
		return null;
	}

	private int gerarNumeroSecreto() {
		Random random = new Random();
		return random.nextInt(5) + 1;
	}

	private String calcularResultado() {
		if (jogo.getNumeroAposta() == jogo.getNumeroSecreto()) {
			return "acertou";
		} else {
			return "não acertou";
		}
	}

	public void selecionarJogo(Jogo jogo) {
		jogoSelecionado = jogo;
	}

	public void excluirJogo(Jogo jogoSelecionado) {
		jogos.remove(jogoSelecionado);
		System.out.println("Jogo excluído: Nome do Jogador - " + jogoSelecionado.getNomeJogador());
	}

	public int getUltimoId() {
		return ultimoId;
	}

	public void setUltimoId(int ultimoId) {
		this.ultimoId = ultimoId;
	}

	public void setJogoSelecionado(Jogo jogoSelecionado) {
		this.jogoSelecionado = jogoSelecionado;
	}

	public int getQuantidadeJogos() {
		return jogos.size();
	}

	public void contarJogos() {
		int quantidade = jogos.size();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Quantidade de jogos cadastrados: " + quantidade));
	}
}
